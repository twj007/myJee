package com.modules.system.controller;

import com.common.model.ActivityModelDetail;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.modules.system.service.impl.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
        modelAndView.setViewName("table");
        return modelAndView;
    }


    @Autowired
    private TestService testService;

    @RequestMapping(value = "/cutTable")
    public void cutTable(HttpServletRequest request){
        testService.cutMoney();
    }

    @GetMapping("/chart")
    public ModelAndView chart(ModelAndView modelAndView, Long planId){

        modelAndView.setViewName("chart");
        modelAndView.addObject("planId", planId);
        return modelAndView;
    }

    @PostMapping("/getCutData")
    public ResultBody getCutData(Long planId){
        ActivityModelDetail d = new ActivityModelDetail();
        d.setPlanId(planId);
        List<ActivityModelDetail> details = testService.getActivityModelDetail(d);
        return Results.SUCCESS.result(details);
    }




}
