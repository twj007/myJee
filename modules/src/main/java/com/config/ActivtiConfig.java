package com.config;


import com.component.NonSecurityGroupManager;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.activiti.spring.boot.actuate.endpoint.ProcessEngineEndpoint;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/01
 **/

@Slf4j
@AutoConfigureAfter(MybatisConfig.class)
public class ActivtiConfig implements ProcessEngineConfigurationConfigurer {

    @Qualifier("workflowDatasource") DataSource dataSource;

    @Qualifier("workflowTransactionManager") DataSourceTransactionManager transactionManager;

    @Override
    public void configure(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
        springProcessEngineConfiguration.setDataSource(dataSource);
        springProcessEngineConfiguration.setTransactionManager(transactionManager);
    }

    @Bean
    @Primary
    SpringProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, NonSecurityGroupManager groupManager){
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setUserGroupManager(groupManager);
        configuration.setAsyncExecutorActivate(false);
        return configuration;
    }

    @Bean("processEngine")
    ProcessEngine processEngine(@Qualifier("workflowDatasource") DataSource dataSource,
                                @Qualifier("workflowTransactionManager") DataSourceTransactionManager transactionManager,
                                NonSecurityGroupManager g){
        return processEngineConfiguration(dataSource, g).buildProcessEngine();
    }

    @Bean
    ProcessEngineEndpoint processEngineEndpoint(@Qualifier("processEngine")ProcessEngine processEngine){
        return new ProcessEngineEndpoint(processEngine);
    }



}
