package com.component;

import com.common.annotation.RepeatToken;
import com.common.constant.CommonConstants;
import com.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/30
 * 前端用法，默认只在ajax请求中使用， 在前端设置cookie - submit_cookie=0,
 * 在ajax的beforeSend() 方法中发现该cookie如果存在，就设置cookie值为 1，
 * 此时该请求便会被拦截并提示不要重复提交。
 **/
@Component
@Slf4j
public class RepeatScanInterceptor extends HandlerInterceptorAdapter {

    private static final String IS_SUBMIT = "1";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Annotation annotation = method.getAnnotation(RepeatToken.class);
            if(annotation != null && ((RepeatToken) annotation).scan()){
                String submit_cookie = RequestUtil.getCookie(request, CommonConstants.SUBMIT_COOKIE);
                if(IS_SUBMIT.equals(submit_cookie)){
                    log.warn("[repeat scan] [{}] repeat scan", request.getRequestURI());
                    response.reset();
                    response.setCharacterEncoding("UTF-8");
                    response.setStatus(400);
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.write("请勿重复提交");
                    writer.flush();
                    writer.close();
                    return false;
                }
            }
        }
        return super.preHandle(request, response, handler);
    }


}
