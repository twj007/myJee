package com.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
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
 **@Date: 2019/10/30
 **/
@Configuration
public class MybatisConfig {

    @Value("${spring.datasource.druid.workflow.url}")
    private String sUrl;

    @Value("${spring.datasource.druid.workflow.username}")
    private String sUsername;

    @Value("${spring.datasource.druid.workflow.password}")
    private String sPassword;

    @Value("${spring.datasource.druid.workflow.driver-class-name}")
    private String sDriver;

    @Value("${spring.datasource.druid.system.url}")
    private String mUrl;

    @Value("${spring.datasource.druid.system.username}")
    private String mUsername;

    @Value("${spring.datasource.druid.system.password}")
    private String mPassword;

    @Value("${spring.datasource.druid.system.driver-class-name}")
    private String mDriver;

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("dialect", "Mysql");
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }

    @Bean("systemDatasource")
    @Primary
    public DataSource systemDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(sUrl);
        dataSource.setDriverClassName(sDriver);
        dataSource.setUsername(sUsername);
        dataSource.setPassword(sPassword);
        return dataSource;
    }

    @Bean("workflowDatasource")
    public DataSource workflowDatasource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(mUsername);
        dataSource.setPassword(mPassword);
        dataSource.setUrl(mUrl);
        dataSource.setDriverClassName(mDriver);
        return dataSource;
    }

//    @Bean("dynamicDatasource")
//    public DataSource dynamicDatasource(@Qualifier("systemDatasource")DataSource systemDatasource, @Qualifier("workflowDatasource")DataSource workflowDatasource){
//
//        DynamicDatasource dynamicDatasource = new DynamicDatasource();
//        //DataSource masterDatasource = systemDataSource();
//        //DataSource slaverDatasource = workflowDatasource();
//        Map<Object, Object> dataSources = new HashMap<>();
//        dataSources.put("system", systemDatasource);
//        dataSources.put("workflow", workflowDatasource);
//        dynamicDatasource.setTargetDataSources(dataSources);
//        return dynamicDatasource;
//    }

    @Bean("systemSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemDatasource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml")); // mapper文件路径
        // 别名包
        bean.setTypeAliasesPackage("com.common.model");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 打印sql
        configuration.setLogImpl(StdOutImpl.class);
        // 驼峰转换
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        // 分页插件拦截器配置
        bean.setPlugins(new Interceptor[]{new PageInterceptor()});
        bean.setDataSource(dataSource);
        return bean.getObject();
    }


    @Bean("workflowSqlSessionFactory")
    public SqlSessionFactory workflowSqlSessionFactory(@Qualifier("workflowDatasource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*/*.xml")); // mapper文件路径
        // 别名包
        bean.setTypeAliasesPackage("com.common.model.workflow");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 打印sql
        configuration.setLogImpl(StdOutImpl.class);
        // 驼峰转换
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        // 分页插件拦截器配置
        bean.setPlugins(new Interceptor[]{new PageInterceptor()});
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /****
     * 默认返回的事务管理器
     * @param dataSource
     * @return
     */
    @Bean("transactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("systemDatasource")DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return  manager;
    }

    @Bean("workflowTransactionManager")
    public DataSourceTransactionManager workflowDataSourceTransactionManager(@Qualifier("workflowDatasource") DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return  manager;
    }
}
