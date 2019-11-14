package com.modules.message.controller;

import com.common.model.vo.message.EmailEntity;
import com.common.utils.ResultBody;
import com.modules.message.service.IMessageService;
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
    private IMessageService messageService;


    @RequestMapping("/test")
    public ResultBody test(){
        EmailEntity entity = new EmailEntity();
        entity.setReceiver("530747628@qq.com");
        return messageService.sendPasswordEmail(entity);
    }
}
