package com.modules.system.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.BootTablePagEntity;
import com.common.model.vo.system.AnnouncementVo;
import com.common.model.vo.system.SysUser;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.component.RabbitProducer;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/15
 * 推的形式和拉的形式
 **/
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private static final String PUBLIC = "1";

    private static final String PRIVATE = "2";


//    @Autowired
//    private IAnnouncementService announcementService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitProducer producer;

    @RequestMapping("/getUnRead")
    @ApiOperation("获取未读的推送消息")
    public ResultBody getUnRead(String userId){
        if(redisTemplate.opsForList().size(CommonConstants.UN_READ_MESSAGE_PREFIX + userId) == 0L){
            return Results.SUCCESS.result(CommonConstants.EMPTY, null);
        }else{
            // 获取未读消息
            List<String> message = redisTemplate.opsForList().range(CommonConstants.UN_READ_MESSAGE_PREFIX + userId, 0, -1);
            // 将消息放入已读队列
            Map map = new HashMap(16);
            map.put("userId", userId);
            map.put("msg", message);
            producer.sendReadMessage(map);
            return Results.SUCCESS.result(CommonConstants.SUCCESS, message);
        }
    }

    @RequestMapping("/readMessage")
    @ApiOperation("获取读取的历史消息")
    public ResultBody readMessage(String userId,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "0") int pageNum){
        List<String> messages;
        if(pageNum > CommonConstants.MAX_CACHE_SIZE){
            // 查询数据库
            messages = Arrays.asList("2", "3");
        }else if(pageSize + pageNum > CommonConstants.MAX_CACHE_SIZE){
            // 查询数据库 和 缓存
            List<String> databaseMsg = Arrays.asList("2", "3");
            messages = redisTemplate.opsForList().range(CommonConstants.READ_MESSAGE_PREFIX + userId, pageNum, CommonConstants.MAX_CACHE_SIZE);
            messages.addAll(databaseMsg);
        }else{
            // 查询缓存
            messages = redisTemplate.opsForList().range(CommonConstants.READ_MESSAGE_PREFIX + userId, pageNum, (pageNum + pageSize));
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, messages);
    }



    /***
     * 保存草稿
     * @param announcementVo
     * @return
     */
    @RequestMapping("/save")
    public ResultBody save(AnnouncementVo announcementVo){
        //long num = announcementService.saveAnnouncement(announcementVo);
        long num = 1L;
        if(num == 1L){
            return Results.SUCCESS.result("保存草稿成功", null);
        }else{
            return Results.BAD__REQUEST.result("保存草稿失败", null);
        }
    }


    @RequestMapping("/add")
    public ResultBody add(AnnouncementVo announcementVo){

        //1， 查看是否存在草稿，存在就更新
        //long num = announcementService.addAnnouncement(announcementVo);
        Long num = 1L;
        if(num == 1L){
            //2. 存入缓存
            if(PUBLIC.equals(announcementVo.getType())){
                redisTemplate.opsForValue().set(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX + announcementVo.getId(), announcementVo);
            }else if(PRIVATE.equals(announcementVo.getType())){
                redisTemplate.opsForValue().set(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + announcementVo.getReceiver(), announcementVo);
            }else{
                return Results.BAD__REQUEST.result("类型必选", null);
            }
            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
        }else{
            return Results.BAD__REQUEST.result("数据保存失败", null);
        }
    }

    @RequestMapping("/delete")
    public ResultBody delete(AnnouncementVo announcementVo){
        if(StringUtils.isEmpty(announcementVo.getId())){
            return Results.BAD__REQUEST.result("必选", null);
        }
        //1. 删除缓存
        if(redisTemplate.opsForValue().get(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX + announcementVo.getId()) != null){
            redisTemplate.expire(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX + announcementVo.getId(), 1000, TimeUnit.MILLISECONDS);
        }else if(redisTemplate.opsForValue().get(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + announcementVo.getId()) != null){
            redisTemplate.expire(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + announcementVo.getId(), 1000, TimeUnit.MILLISECONDS);
        }
        //2. 设置状态禁用
        announcementVo.setStatus("2");
        //long num = announcementService.updateAnnouncement(announcementVo);
        Long num = 1L;
        if(num == 1L){
            return Results.SUCCESS.result("删除成功", null);
        }else{
            return Results.BAD__REQUEST.result("删除失败", null);
        }
    }

    @RequestMapping("/list")
    public ResultBody query(AnnouncementVo announcementVo,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "0")int pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "5")int pageSize){
        Page page = PageHelper.startPage(pageNum, pageSize);
        //announcementService.list(announcementVo);
        BootTablePagEntity entity = new BootTablePagEntity();
        entity.setRows(page.getResult());
        entity.setTotal(page.getTotal());
        //直接查询数据库
        return Results.SUCCESS.result(CommonConstants.SUCCESS, entity);
    }

    @RequestMapping("/detail")
    public ResultBody detail(AnnouncementVo announcementVo){
        AnnouncementVo vo = null;
        //AnnouncementVo vo = announcementService.getDetail(announcementVo);
        //直接查询数据库
        return Results.SUCCESS.result(CommonConstants.SUCCESS, vo);
    }

    @RequestMapping("/modify")
    public ResultBody modify(AnnouncementVo announcementVo){
        if(StringUtils.isEmpty(announcementVo.getId())){
            return Results.BAD__REQUEST.result("必选", null);
        }
        //1. 修改缓存
        if(redisTemplate.opsForValue().get(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX + announcementVo.getId()) != null){
            redisTemplate.opsForValue().set(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX + announcementVo.getId(), announcementVo);
        }else if(redisTemplate.opsForValue().get(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + announcementVo.getId()) != null){
            redisTemplate.opsForValue().set(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + announcementVo.getId(), announcementVo);
        }
        //2. 更新数据库
        Long num = 1L;
        //long num = announcementService.updateAnnouncement(announcementVo);
        if(num == 1L){
            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
        }else{
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }

    /***
     * 隔一段时间去拉取redis中该用户的推送消息
     * 消息应该分两种： 1. 公共推送  2. 个人推送
     * 个人推送的话在查看完后过期缓存即可，公共推送记录缓存信息，如果存在信息就不推送
     * @return
     */
    @RequestMapping("/poll")
    public ResultBody poll(SysUser user){
        List<AnnouncementVo> announcementVoList = new ArrayList<>();
        Set<byte[]> redisCallback;
        //1， 拉取公共部分，过滤已读部分
        redisCallback = (Set<byte[]>) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<byte[]> announceList = redisConnection.keys(CommonConstants.ANNOUNCEMENT_PUBLIC_PREFIX.getBytes());
                Set<byte[]> readList = redisConnection.keys((CommonConstants.ANNOUNCEMENT_READ_PREFIX+user.getId()).getBytes());
                // 过滤已读消息
                announceList = announceList.stream().filter(p -> !readList.contains(p)).collect(Collectors.toSet());
                Set<byte[]> privateList = redisConnection.keys(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX.getBytes());
                announceList.addAll(privateList);
                return announceList;
            }
        });
        // 过滤
        if(redisCallback == null){
            return Results.SUCCESS.result(CommonConstants.EMPTY, null);
        }
        redisCallback.forEach(p ->{
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(p));
                AnnouncementVo vo = (AnnouncementVo) ois.readObject();
                announcementVoList.add(vo);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        });
        return Results.SUCCESS.result(CommonConstants.SUCCESS, announcementVoList);

    }

    /***
     * 记录消息的查看情况 type 1 公共， 则记录已查看信息， 2 个人， 则移除信息
     *
     * @return
     */
    @RequestMapping("/check")
    public ResultBody check(AnnouncementVo vo){
        if(StringUtils.isEmpty(vo.getType())){
            return Results.BAD__REQUEST.result(CommonConstants.EMPTY, null);
        }

        if(PUBLIC.equals(vo.getType())){
            //2. 公共部分增加记录
        } else if (PRIVATE.equals(vo.getType())){
            //1. 个人部分直接过期
            redisTemplate.expire(CommonConstants.ANNOUNCEMENT_PRIVATE_PREFIX + vo.getId(), 1000, TimeUnit.MILLISECONDS);
        }


        //3. 保存记录
        return null;
    }

    /***
     * 用于清理公共消息任务job， 在所有人都查看完毕后， 移除缓存的消息， 及个人的消息记录
     * @return
     */
    @RequestMapping("/clear")
    public ResultBody clear(){
        //定时清除缓存

        return null;
    }

}
