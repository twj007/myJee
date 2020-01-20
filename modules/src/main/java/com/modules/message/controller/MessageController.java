package com.modules.message.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.message.EmailEntity;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.component.RabbitProducer;
import com.config.RabbitConfig;
import com.jee.service.message.IMessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/test")
    public ResultBody test(){
        EmailEntity entity = new EmailEntity();
        entity.setReceiver("530747628@qq.com");
        return messageService.sendPasswordEmail(entity);
    }

    @RequestMapping("/test2")
    public void test2(){
        rabbitProducer.send("ok");
    }



    @RequestMapping("/socket/push")
    public void push(String userId, String msg){
        Map map = new HashMap(16);
        map.put("userId", userId);
        map.put("msg", msg);
        rabbitProducer.sendMessageSocket(map);
    }


    @RequestMapping("/socket")
    public ModelAndView socket(ModelAndView modelAndView){
        modelAndView.setViewName("message/socket");
        return modelAndView;
    }

    @RequestMapping("/pay")
    public ResultBody pay(Object object){
        // 1. 保存订单信息，根据支付方式， 放入支付队列

        // 2. 第三方支付及回调，回调成功则订单创建成功，否则失败，等待

        // 3. 超时，进入延迟队列，查询订单状态， 如果未支付，则取消订单
        return null;
    }

    @ApiOperation("demo 拉取队列消息")
    @RequestMapping("/consume")
    public ResultBody consume(){
        String msg = (String) rabbitTemplate.receiveAndConvert(RabbitConfig.QUEUE_FLASH);
        return Results.SUCCESS.result(CommonConstants.SUCCESS , msg);
    }
}
