package com.modules.system.controller;

import com.common.model.dto.announce.AnnounceDTO;
import com.common.model.dto.announce.AnnounceDetailDTO;
import com.common.model.vo.PageVo;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jee.service.system.IAnnouncementService;
import com.modules.message.component.MessageWebSocket;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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

    @Autowired
    private IAnnouncementService announcementService;

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
                           @RequestParam(name = "pageNum", defaultValue = "0")int pageNum,
                           @RequestParam(name = "pageSize", defaultValue = "5")int pageSize,
                           AnnounceDTO announce,
                           AnnounceDetailDTO announceDetailDTO){
        Page page = PageHelper.startPage(pageNum, pageSize);
        PageVo pageVo = new PageVo();
        if(ANNOUNCE_ALL.equals(type)){
            // 1. 鉴权

            List<AnnounceDTO> announceDTOList = announcementService.getAnnounceList(announce);
            pageVo.setRows(announceDTOList);
        }
        if(RECORD_ALL.equals(type)){
            // 1. 鉴权

            List<AnnounceDetailDTO> announceDetailDTOList = announcementService.getAnnounceDetailList(announceDetailDTO);
            pageVo.setRows(announceDetailDTOList);
        }
        if(RECORD_USER.equals(type)){
            // 1. 根据用户获取公告

            List<AnnounceDTO> announceDTOList = announcementService.getAnnounceListByUser(announce);
            pageVo.setRows(announceDTOList);
        }
        pageVo.setTotal(page.getTotal());
        return Results.BAD__REQUEST.result("success", pageVo);
    }

    @RequestMapping("/announce/{id}")
    @ApiOperation("查询单条公告")
    public ResultBody findOne(@PathVariable("id")String id){
        AnnounceDTO a = new AnnounceDTO();
        a.setId(Long.valueOf(id));
        AnnounceDTO announceDTO = announcementService.getAnnounceById(a);
        return Results.SUCCESS.result("success", announceDTO);
    }

    @RequestMapping("/update")
    @ApiOperation("变更单条公告")
    public ResultBody updateOne(AnnounceDTO announce){
        long num = announcementService.updateAnnounce(announce);
        if(num == 1L){
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("更新失败", null);
    }

    @RequestMapping("/delete")
    @ApiOperation("批量删除公告")
    public ResultBody delete(List<Long> ids){

        long num = announcementService.deleteAnnounceByIds(ids);
        if(num > 0L){
            return Results.SUCCESS.result("删除成功，共删除"+num+"条", null);
        }
        return Results.BAD__REQUEST.result("删除失败", null);
    }

    @RequestMapping("/save")
    @ApiOperation("保存为草稿")
    public ResultBody save(AnnounceDTO announce){
        long num = announcementService.saveOrUpdate(announce);
        if(num > 0L){
            return Results.SUCCESS.result("保存成功", null);
        }
        return Results.BAD__REQUEST.result("保存失败", null);
    }

    @RequestMapping("/send")
    @ApiOperation("立即发送")
    public ResultBody send(List<Long> ids){
        long num = announcementService.send(ids);
        if(num > 0L){
            return Results.SUCCESS.result("发送成功"+num+"条", null);
        }
        return Results.BAD__REQUEST.result("发送失败", null);
    }


    @RequestMapping("/saveAndSend")
    @ApiOperation("立即保存并发送, 为登陆的登陆时拉取数据库，登陆了的webSocket发送")
    public ResultBody saveAndSend(AnnounceDTO announce){
        long num = announcementService.saveAndSend(announce);
        if(num > 0L){
            return Results.SUCCESS.result("发送成功"+num+"条", null);
        }
        return Results.BAD__REQUEST.result("发送失败", null);
    }

    @RequestMapping("/scheduleAndSend")
    @ApiOperation("定时调用并推送消息, webSocket推送")
    public ResultBody scheduleAndSend(String param){
        if(param != null){
            long num = announcementService.sendUnRead(param);
            return Results.SUCCESS.result("发送成功: "+num+"条", null);
        }else{
            return Results.BAD__REQUEST.result("请求非法", null);
        }
    }




}
