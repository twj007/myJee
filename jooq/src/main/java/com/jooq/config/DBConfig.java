package com.jooq.config;

import com.zaxxer.hikari.util.DriverDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/16
 **/
@Configuration
public class DBConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    @Bean
    @Primary
    DataSource dataSource(){
        DataSource dataSource = new DriverDataSource(url, driver, new Properties(), username, password);
        return dataSource;
    }


    @Bean
    @Primary
    DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        manager.afterPropertiesSet();
        return manager;
    }
}
