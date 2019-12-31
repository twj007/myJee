package com.modules.system.controller;

import com.common.model.vo.system.AnnouncementVo;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.modules.message.component.MessageWebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/31
 **/
@RestController
@RequestMapping("/api/announce/v2")
@Api("消息发布接口")
@Slf4j
public class AnnounceControllerV2 {

    private static final String PREFIX = "/sys/announce/v2/";

    private static final String ANNOUNCE_ALL = "announce_all";

    private static final String RECORD_USER = "announce_user";

    private static final String RECORD_ALL = "record_all";

    @Resource
    private MessageWebSocket socket;

    @RequestMapping("/test")
    public ResultBody test(){
        ResultBody resultBody = Results.SUCCESS.result("ok", null);
        socket.sendOneMessage("52155" , resultBody.toString());
        return resultBody;
    }


    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    @ApiOperation("页面跳转")
    public ModelAndView page(ModelAndView modelAndView, @PathVariable("page")String view){
        modelAndView.setViewName(PREFIX + view);
        return modelAndView;
    }

    @RequestMapping("/list/{type}")
    @ApiOperation("查询发布内容，查询推送记录")
    public ResultBody list(@PathVariable("type")String type,
                           @RequestParam(name = "pageNum", defaultValue = "0")int pageNUm,
                           @RequestParam(name = "pageSize", defaultValue = "5")int pageSize,
                           AnnouncementVo announcement){
        return null;
    }
}
