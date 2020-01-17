package com.jooq.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yandex.clickhouse.ClickHouseDataSource;

import java.util.Properties;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/17
 **/
@Configuration
public class ClickConfig {

    @Value("${clickhouse.url}")
    private String url;

    @Value("${clickhouse.password}")
    private String password;

    @Value("${clickhouse.username}")
    private String username;

    @Bean("clickHouseDatasource")
    ClickHouseDataSource dataSource(){
        Properties properties = new Properties();
        properties.setProperty("password", password);
        properties.setProperty("username", username);
        ClickHouseDataSource dataSource = new ClickHouseDataSource(url , properties);
        return dataSource;
    }

}
