//package com.modules.system.controller;
//
//import com.common.utils.Results;
//import org.springframework.boot.autoconfigure.web.ErrorProperties;
//import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
//import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//import java.util.Map;
//
///***
// **@project: myJee
// **@description:
// **@Author: twj
// **@Date: 2019/10/31
// **/
//@RestController
//public class MyErrorController implements ErrorController {
//
//
//    @Override
//    public String getErrorPath() {
//        return null;
//    }
//
//    @RequestMapping("/error")
//    public ResponseEntity error(HttpServletRequest request){
//        String message = ((Exception) request.getAttribute("javax.servlet.error.exception")).getMessage().split(" ")[1];
//
//        return ResponseEntity.ok(Results.BAD__REQUEST.result(message, null));
//    }
//}
