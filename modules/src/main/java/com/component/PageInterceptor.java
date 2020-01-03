package com.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Set;

/***
 * 拦截/page请求并分析
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Component
@Slf4j
public class PageInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Set<String> templates;

    private static final String MATCHED_URI = "/page/";

    @SuppressWarnings("Duplicates")
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String URI = request.getRequestURI();
        if(MATCHED_URI.equals(URI)){
            String pathUrl = URI.substring(URI.lastIndexOf(MATCHED_URI), URI.length() - 1);
            if(pathUrl == null ||  !templates.contains(pathUrl)){
                log.warn("[repeat scan] [{}] repeat scan", request.getRequestURI());
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setStatus(400);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter writer = response.getWriter();
                writer.write("您访问的页面不存在");
                writer.flush();
                writer.close();
                return false;
            }
        }
        return super.preHandle(request, response, handler);
    }
}
