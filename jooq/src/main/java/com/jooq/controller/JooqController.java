package com.jooq.controller;

import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.vo.system.SysUser;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/16
 **/
@RestController
@RequestMapping("/jooq")
public class JooqController {

    @Autowired
    DSLContext dslContext;

    @GetMapping("/test1")
    public ResultBody search(SysUser user){
        Result<Record> results = dslContext.select().from("sys_user").fetch();
        List<SysUser> users = new ArrayList<>();
        for(Record record : results){
            SysUser u = new SysUser();
            u.setUsername((String) record.getValue("username"));
            u.setPassword((String) record.getValue("password"));
            users.add(u);
        }
        return Results.SUCCESS.result(users);
    }

    @GetMapping("/test2")
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBody insert(){
        AnnounceDTO announceDTO = new AnnounceDTO();
        announceDTO.setTitle("test1111");
        announceDTO.setContent("xxxxxx");
        announceDTO.setStatus("Y");
        announceDTO.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        announceDTO.setCount(0L);
        announceDTO.setAuthorId(1L);
        announceDTO.setType("1");
        announceDTO.setSendType("1");
        Table table = DSL.table("sys_message");
        int num = dslContext
                .insertInto(table)
                .columns(DSL.field("title"), DSL.field("content"), DSL.field("status"), DSL.field("create_date"), DSL.field("_count"), DSL.field("author_id"), DSL.field("_type"), DSL.field("send_type"))
                .values(announceDTO.getTitle(), announceDTO.getContent(), announceDTO.getStatus(), announceDTO.getCreateDate(), announceDTO.getCount(), announceDTO.getAuthorId(), announceDTO.getType(), announceDTO.getSendType())
                .execute();
        return Results.SUCCESS.result(num);
    }

    @GetMapping("/test3")
    public ResultBody update(Long id){
        if(id == null){
            return Results.INVALIDATE.result("参数必须");
        }
        Result<Record1<Object>> records = dslContext
                .select(DSL.field("id"))
                .from("sys_message")
                .where(DSL.field("id").eq(id))
                .fetch();
        if(records == null || records.size() == 0){
            return Results.BAD__REQUEST.result("该id下不存在记录");
        }
        AnnounceDTO announceDTO = new AnnounceDTO();
        announceDTO.setTitle("updatetest");
        announceDTO.setContent("updatetest");
        announceDTO.setStatus("Y");
        announceDTO.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
        announceDTO.setCount(0L);
        announceDTO.setAuthorId(1L);
        announceDTO.setType("1");
        announceDTO.setSendType("1");
        announceDTO.setId(id);
        Table table = DSL.table("sys_message");
        Condition condition = DSL.field("id").eq(id);
        int num = dslContext.update(table)
                .set(DSL.field("title"), announceDTO.getTitle())
                .set(DSL.field("content"), announceDTO.getContent())
                .set(DSL.field("status"), announceDTO.getStatus())
                .set(DSL.field("create_date"), announceDTO.getCreateDate())
                .set(DSL.field("_count"), announceDTO.getCount())
                .set(DSL.field("author_id"), announceDTO.getAuthorId())
                .set(DSL.field("_type"), announceDTO.getType())
                .set(DSL.field("send_type"), announceDTO.getSendType())
                .where(condition)
                .execute();
        return Results.SUCCESS.result(num);
    }

    @GetMapping("/test4")
    public ResultBody delete(@RequestParam("id") Long id){
        Table table = DSL.table("sys_message");
        Condition condition = DSL.field("id").eq(id);
        int num = dslContext.delete(table).where(condition).execute();
        return Results.SUCCESS.result(num);
    }

    @Autowired
    private ClickHouseDataSource dataSource;

    @GetMapping("/test5")
    public ResultBody test5(@RequestParam(name = "pageNum", defaultValue = "0", required = false) int pageNum,
                            @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize){

//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet result = statement.executeQuery("select * from hits_v1 limit " + pageNum + " ," + pageSize);
//            System.out.println(result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Results.SUCCESS.result(null);
        String sql = "select * from hits_v1 limit " + pageNum + " ," + pageSize;
        String address = "jdbc:clickhouse://106.14.216.218:8123/tutorial";
        Connection connection = null;
        Statement statement = null;
        ResultSet results = null;
        try {
            Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
            connection = DriverManager.getConnection(address);
            statement = connection.createStatement();
            long begin = System.currentTimeMillis();
            results = statement.executeQuery(sql);
            long end = System.currentTimeMillis();
            System.out.println("执行（"+sql+"）耗时："+(end-begin)+"ms");
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while(results.next()){
                Map map = new HashMap();
                for(int i = 1;i<=rsmd.getColumnCount();i++){
                    map.put(rsmd.getColumnName(i),results.getString(rsmd.getColumnName(i)));
                }
                list.add(map);
            }
            for(Map map : list){
                System.err.println(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {//关闭连接
            try {
                if(results!=null){
                    results.close();
                }
                if(statement!=null){
                    statement.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Results.SUCCESS.result(null);
    }

}
