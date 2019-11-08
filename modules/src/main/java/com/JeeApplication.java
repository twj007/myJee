package com;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableSwagger2
@MapperScan(basePackages = "com.modules.**.dao")
public class JeeApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JeeApplication.class, args);
    }
    /***
     * 打包用
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
