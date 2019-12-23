package com.modules.system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@RestController
@Slf4j
public class TestController {

    @RequestMapping("/")
    public String test(){
      log.info("test log");
      log.warn("test log");
      log.error("test log");
      return "ok";
    }

    @RequestMapping("/table")
    public ModelAndView table(ModelAndView modelAndView){
        modelAndView.setViewName("/table");
        return modelAndView;
    }


}
