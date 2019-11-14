package com.modules.message.controller;

import com.component.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RabbitProducer rabbitProducer;

    @RequestMapping("/test")
    public void test(){
        rabbitProducer.send("test msg");
    }
}
