package com.jee.workflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 **@project: myJee
 **@description: it's the workflow engine
 **@Author: twj
 **@Date: 2019/12/31
 **/
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableSwagger2
//@MapperScan(basePackages = {"com.jee.workflow.dao"})
public class WorkflowApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
