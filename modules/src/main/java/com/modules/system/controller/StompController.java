package com.modules.system.controller;

import com.common.utils.ResultBody;
import com.common.utils.Results;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/31
 **/
@RestController
@Slf4j
public class StompController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("/socket/stomp")
    public ModelAndView stomp(ModelAndView modelAndView){
        modelAndView.setViewName("/sys/announce/v2/stomp");
        return modelAndView;
    }


    @RequestMapping("/broadcast")
    public ResultBody broadcast(){
        String message = "通知.......";
        log.info("[web socket] send broadcast info: {}", message);
        messagingTemplate.convertAndSend("/topic", message);
        return Results.SUCCESS.result("消息发送中", null);
    }

    @RequestMapping("/sendTo")
    public ResultBody sendTo(){
        String message = "通知给51255.......";
        log.info("[web socket] send broadcast info: {}", message);
        messagingTemplate.convertAndSendToUser("51255", "/queue/getResponse", message);
        return Results.SUCCESS.result("消息发送中", null);
    }
}
