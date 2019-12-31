package com.jee.workflow.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@SuppressWarnings("Duplicates")
@Configuration
public class DBConfig {

    @Bean
    public DataSource systemDataSource() {
        return DruidDataSourceBuilder.create().build();
    }



    @Bean
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        filterRegistrationBean.addInitParameter("profileEnable", "true");

        return filterRegistrationBean;

    }


//    @Bean("sqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
//        bean.setTypeAliasesPackage("com.common.model");
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        // 打印sql
//        configuration.setLogImpl(StdOutImpl.class);
//        // 驼峰转换
//        configuration.setMapUnderscoreToCamelCase(true);
//        bean.setConfiguration(configuration);
//        // 分页插件拦截器配置
//        bean.setPlugins(new Interceptor[]{new PageInterceptor()});
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }

    @Bean
    public DataSourceTransactionManager workflowDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();

        manager.setDataSource(dataSource);
        return  manager;
    }
}
