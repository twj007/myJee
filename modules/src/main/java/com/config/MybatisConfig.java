package com.config;

import com.alibaba.druid.pool.DruidDataSource;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;
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

    @Bean("workflowDatasource")
    @Primary
    public DataSource systemDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(sUrl);
        dataSource.setDriverClassName(sDriver);
        dataSource.setUsername(sUsername);
        dataSource.setPassword(sPassword);
        dataSource.setResetStatEnable(properties.isRestEnable());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setUseGlobalDataSourceStat(properties.isUseGlobalDataSourceStat());
        dataSource.addFilters("stat,wall");
        return dataSource;
    }

    @Bean("systemDatasource")
    public DataSource workflowDatasource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(mUsername);
        dataSource.setPassword(mPassword);
        dataSource.setUrl(mUrl);
        dataSource.setDriverClassName(mDriver);
        dataSource.setResetStatEnable(properties.isRestEnable());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setUseGlobalDataSourceStat(properties.isUseGlobalDataSourceStat());
        dataSource.addFilters("stat,wall");
        return dataSource;
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


//    @Bean("dynamicDatasource")
//    @Primary
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

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("systemDatasource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
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
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("workflowDatasource")DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return  manager;
    }


    /***
     * 留出一份给workflow
     * @param dataSource
     * @return
     * @throws Exception
     */
//    @Bean("workflowSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory workflowSqlSessionFactory(@Qualifier("workflowDatasource")DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        // mapper文件路径
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/lib/org/activiti/db/mapping/entity/*.xml"));
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
//        // 打印sql
//        configuration.setLogImpl(StdOutImpl.class);
//        bean.setConfiguration(configuration);
//        bean.setTypeHandlers(new TypeHandler[]{new ByteArrayRefTypeHandler()});
//        // 分页插件拦截器配置
//        bean.setPlugins(new Interceptor[]{new PageInterceptor()});
//        bean.setDataSource(dataSource);
//        return bean.getObject();
//    }


    @Bean("workflowTransactionManager")
    @Primary
    public DataSourceTransactionManager workflowDataSourceTransactionManager(@Qualifier("workflowDatasource") DataSource dataSource){
        DataSourceTransactionManager manager = new DataSourceTransactionManager();

        manager.setDataSource(dataSource);
        return  manager;
    }
}
