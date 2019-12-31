package com.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.component.DruidConfigProperties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/30
 **/
@SuppressWarnings("Duplicates")
@Configuration
public class MybatisConfig {

    @Autowired
    private DruidConfigProperties properties;

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
    public DataSource workflowDatasource() {
       return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "druidServlet)")
    public ServletRegistrationBean druidServlet() {

        ServletRegistrationBean reg = new ServletRegistrationBean();

        reg.setServlet(new StatViewServlet());

        reg.addUrlMappings("/druid/*");

        reg.addInitParameter("loginUsername", properties.getLoginUsername());

        reg.addInitParameter("loginPassword", properties.getLoginPassword());

        reg.addInitParameter("logSlowSql", String.valueOf(properties.isLogSlowSql()));

        return reg;

    }

    @Bean(name = "filterRegistrationBean")
    public FilterRegistrationBean filterRegistrationBean() {

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        filterRegistrationBean.setFilter(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        filterRegistrationBean.addInitParameter("profileEnable", "true");

        return filterRegistrationBean;

    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemDatasource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));
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


    @Bean("transactionManager")
    @Primary
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return  manager;
    }

}
