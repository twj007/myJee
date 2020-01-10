package com.modules.online.controller;

import com.common.utils.ResultBody;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/06
 **/
@RestController
@RequestMapping("/sys/online/table")
@Api("在线数据库表生成器")
public class OnlineTableController {

    private static final String PREFIX = "/table/generator/";


    @RequestMapping("/page")
    public ModelAndView page(ModelAndView modelAndView){
        modelAndView.setViewName(PREFIX + "table");
        return modelAndView;
    }


    @RequestMapping("/generate")
    public ResultBody generate(){

        return null;
    }
}
