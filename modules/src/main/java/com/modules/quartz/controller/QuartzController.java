package com.modules.quartz.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.quartz.QuartzErrorTasks;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.model.vo.quartz.QuartzTaskRecordVo;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.modules.quartz.service.impl.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@RestController
@RequestMapping("/quartz")
@Slf4j
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @RequestMapping("/test")
    public void test(){
        log.info("[{}] [{}] test start", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listTasks(ModelAndView model, @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
                            @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        try {
            Page<QuartzTaskInformations> page = PageHelper.startPage(pageNum, pageSize);
            List<QuartzTaskInformations> taskList = quartzService.getTaskList();

            model.addObject("taskList", taskList);
            model.addObject("size", taskList.size());
            model.addObject("currentPage", pageNum + 1);
            model.addObject("totalPage", page.getTotal());
            model.addObject("taskNo", pageSize);
            model.setViewName("/quartz/index");
        } catch (Exception e) {
            log.error("首页跳转发生异常exceptions-->" + e.toString());
        }
        return model;
    }

    @RequestMapping(value = "/add/taskpage", method = RequestMethod.GET)
    public ModelAndView addTaskpage() {
        return new ModelAndView("/quartz/addtask");
    }

    @RequestMapping(value = "/add/task", method = RequestMethod.POST)
    public ResultBody addTask(QuartzTaskInformations taskInformations) {
        try {
             return quartzService.addTask(taskInformations);
        } catch (Exception e) {
            log.error("/add/task exception={}", e);
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }

    @RequestMapping(value = "/edit/taskpage", method = RequestMethod.GET)
    public ModelAndView editTaskpage(ModelAndView model, String id) {
        QuartzTaskInformations taskInformation = quartzService.getTaskById(id);
        model.addObject("taskInformation", taskInformation);
        model.setViewName("/quartz/updatetask");
        return model;
    }

    @RequestMapping(value = "/edit/task", method = RequestMethod.POST)
    public ResultBody editTask(QuartzTaskInformations taskInformations) {
        try {
            return quartzService.updateTask(taskInformations);

        } catch (Exception e) {
            log.error("/edit/task exception={}", e);
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }

    /**
     * 启动 或者 暂定定时任务
     *
     * @param taskNo
     * @return
     */
    @RequestMapping(value = "/list/optionjob", method = RequestMethod.GET)
    public ResultBody optionJob(String taskNo) {
        if (StringUtils.isEmpty(taskNo)) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, "taskNo is required");
        }
        try {
            return quartzService.startJob(taskNo);
        } catch (Exception e) {
            log.error("/list/optionjob exception={}", e);
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }

    /**
     * 定时任务执行情况
     *
     * @param taskNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/taskrecords", method = RequestMethod.GET)
    public ModelAndView taskRecordsPage(@RequestParam(value = "taskno", required = false) String taskNo, ModelAndView model) {
        try {
            if (StringUtils.isEmpty(taskNo)) {
                return new ModelAndView("/quartz/list");
            }
            List<QuartzTaskRecordVo> quartzTaskRecords = quartzService.taskRecords(taskNo);
            model.addObject("quartzTaskRecords", quartzTaskRecords);
        } catch (Exception e) {
            log.error("");
            return new ModelAndView("/quartz/list");
        }
        model.setViewName("/quartz/taskrecords");
        return model;
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskNo
     * @param model
     * @return
     */
    @RequestMapping(value = "/runtask/rightnow", method = RequestMethod.GET)
    public ModelAndView runTaskRightNow(@RequestParam(value = "taskno", required = false) String taskNo, ModelAndView model) {
        try {
            if (StringUtils.isEmpty(taskNo)) {
                return new ModelAndView("/quartz/list");
            }
            ResultBody resultBody = quartzService.runTaskRightNow(taskNo);
            model.addObject("result", resultBody);
            model.setViewName("/quartz/list");
            return model;
        } catch (Exception e) {
            log.error("[exception] {}", e.fillInStackTrace());
            return new ModelAndView("/quartz/list");
        }
    }

    /**
     * 定时任务失败详情
     *
     * @param recordId
     * @param model
     * @return
     */
    @RequestMapping(value = "/task/errors", method = RequestMethod.GET)
    public ModelAndView detailTaskErrors(@RequestParam(value = "recordid", required = false) String recordId, ModelAndView model) {
        try {
            if (StringUtils.isEmpty(recordId)) {
                return new ModelAndView("/quartz/list");
            }
            QuartzErrorTasks taskErrors = quartzService.detailTaskErrors(recordId);
            model.addObject("taskErrors", taskErrors);
            model.setViewName("/quartz/taskerrors");
            return model;
        } catch (Exception e) {
            log.error("");
            return new ModelAndView("/quartz/list");
        }
    }

}
