package com.modules.system.controller;

import com.common.utils.ResultBody;
import com.common.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestControllerAdvice
@Slf4j
public class ErrorResolver {

    @RequestMapping("/error")
    public ResponseEntity error(){
        return ResponseEntity.badRequest().body("unexpected error happened");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResultBody resolveUserAuthentication(AuthenticationException e){
      e.printStackTrace();
      log.error("{} [exception]: msg:{}, cause: {}", Thread.currentThread().getName(), e.getMessage(), e.fillInStackTrace());
      return Results.SUCCESS.result(e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public ResultBody resolveException(Exception e){
        e.printStackTrace();
        log.error("{} [exception]: msg:{}, cause: {}", Thread.currentThread().getName(), e.getMessage(), e.fillInStackTrace());
        return Results.SUCCESS.result(e.getMessage(), null);
    }
}
