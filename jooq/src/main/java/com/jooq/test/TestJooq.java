package com.jooq.test;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/16
 **/
public class TestJooq {

    public static void main(String[] args) throws SQLException{
        String username = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/mall";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
            Result<Record> records = context.select().from("sys_user").fetch();
            for (Record record : records) {
                record.toString();
                System.out.println("==========================result=========================");
                System.out.println(record);
            }
            context.close();
        } catch(SQLException e){
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
