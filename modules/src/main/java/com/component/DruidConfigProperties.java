package com.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/06
 **/
@Configuration
@Component
@Data
public class DruidConfigProperties {

    @Value("${spring.datasource.druid.initial-size}")
    private int initialSize;
    @Value("${spring.datasource.druid.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.druid.max-active}")
    private int maxActive;
    @Value("${spring.datasource.druid.max-wait}")
    private long maxWait;
    @Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
    private long runMills;
    @Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
    private long idleMillis;
    @Value("${spring.datasource.druid.validation-query}")
    private String validationQuery;
    @Value("${spring.datasource.druid.test-while-idle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.druid.test-on-borrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.druid.test-on-return}")
    private boolean testOnReturn;
    @Value("${spring.datasource.druid.pool-prepared-statements}")
    private boolean poolPreparedStatement;
    @Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
    private int perConnectionSize;
    @Value("${spring.datasource.druid.use-global-data-source-stat}")
    private boolean useGlobalDataSourceStat;
    @Value("${spring.datasource.druid.connect-properties}")
    private String connectProperties;
    @Value("${spring.datasource.druid.stat-view-servlet.login-username}")
    private String loginUsername;
    @Value("${spring.datasource.druid.stat-view-servlet.login-password}")
    private String loginPassword;
    @Value("${spring.datasource.druid.stat-view-servlet.reset-enable}")
    private boolean restEnable;
    @Value("${spring.datasource.druid.stat-view-servlet.url-pattern}")
    private String urlPattern;
    @Value("${spring.datasource.druid.stat-view-servlet.allow}")
    private String allowIp;
    @Value("${spring.datasource.druid.web-stat-filter.url-pattern}")
    private String statUrlPattern;
    @Value("${spring.datasource.druid.web-stat-filter.exclusions}")
    private String exclusion;
    @Value("${spring.datasource.druid.web-stat-filter.enabled}")
    private boolean filterEnable;
    @Value("${spring.datasource.druid.use-local-session-state}")
    private boolean useLocalSessionState;
    @Value("${spring.datasource.druid.fileter.stat.log-slow-sql}")
    private boolean logSlowSql;
    @Value("${spring.datasource.druid.fileter.stat.slow-sql-millis}")
    private long slowSqlMilis;
    @Value("${spring.datasource.druid.fileter.stat.merge-sql}")
    private boolean mergeSql;
    @Value("${spring.datasource.druid.fileter.wall.config.multi-statement-allow}")
    private boolean multiStatementAllow;

}
