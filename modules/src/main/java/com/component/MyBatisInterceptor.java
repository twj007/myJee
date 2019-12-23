package com.component;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;

/***
 *
 * @Signature(type = ParameterHandler.class, method = "getParameterObject", args = {}),
 * @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
 * 可以拦截四大接口的参数： type: Executor， StatementHandler， ParameterHandler， ResultSetHandler
 *                      method: 类型接口中的方法，用于动态代理
 *                      args： method方法中参数类型
 *
 * @project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/23
 **/
@Intercepts({@Signature(type= Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
            @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyBatisInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        logger.info("【mybatis plugin】 start intercept executor: {}");
        Object result = invocation.proceed();
        return result;
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("【mybatis plugin】 set plugin properties");
    }
}
