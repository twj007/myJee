package com.modules.quartz.controller;

import com.common.constant.CommonConstants;
import com.common.model.vo.BootTablePagEntity;
import com.common.model.vo.PageVo;
import com.common.model.vo.quartz.QuartzErrorTasks;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.modules.quartz.service.impl.QuartzService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronExpression;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@RestController
@RequestMapping("/quartz")
@Api("定时任务管理")
@Slf4j
public class QuartzController {

    @Autowired
    private QuartzService quartzService;

    @Autowired
    private Scheduler scheduler;

    private static final int CIRCLE_COUNT = 10;


    @RequestMapping("/test")
    @ApiOperation("测试方法")
    public void test(){
        log.info("[{}] [{}] test start", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    }

    @RequestMapping("/delete")
    @Deprecated
    public ResponseEntity delete(String jobKey) throws SchedulerException {
        scheduler.deleteJob(JobKey.jobKey(jobKey, Scheduler.DEFAULT_GROUP));
        return ResponseEntity.ok(null);
    }

    @RequestMapping(value = "/task/list/page")
    @Deprecated
    public ModelAndView listPage(ModelAndView modelAndView){
        modelAndView.setViewName("quartz/list");
        return modelAndView;
    }

    @RequestMapping(value = "/task/list", method = RequestMethod.POST)
    @Deprecated
    public ResultBody listTasks(@RequestParam(value = "searchStr", required = false)String searchStr,
                                @RequestParam(value = "pageNum", required = false, defaultValue = "0") int pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        try {
            Page<QuartzTaskInformations> page = PageHelper.startPage(pageNum, pageSize);
            quartzService.getTaskList(searchStr);
            PageVo pageVo = new PageVo();
            pageVo.setTotal(page.getTotal());
            pageVo.setPageNum(page.getTotal()/pageSize + 1);
            pageVo.setCurrentPage(pageNum + 1);
            pageVo.setRows(page.getResult());
            return Results.SUCCESS.result(CommonConstants.SUCCESS, pageVo);
        } catch (Exception e) {
            log.error("首页跳转发生异常exceptions-->" + e.toString());
        }
        return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
    }

    @RequestMapping(value = "/task/add/page", method = RequestMethod.GET)
    @Deprecated
    public ModelAndView addTaskPage() {
        return new ModelAndView("quartz/addtask");
    }

    @RequestMapping(value = "/task/add", method = RequestMethod.POST)
    @Deprecated
    public ResultBody addTask(QuartzTaskInformations taskInformations) throws SchedulerException {
        return quartzService.addTask(taskInformations);
    }

    @RequestMapping(value = "/task/edit/page", method = RequestMethod.GET)
    @Deprecated
    public ModelAndView editTaskPage(ModelAndView model, String id) {
        QuartzTaskInformations taskInformation = quartzService.getTaskById(id);
        model.addObject("taskInformation", taskInformation);
        model.setViewName("quartz/updateTask");
        return model;
    }

    @RequestMapping(value = "/task/edit", method = RequestMethod.POST)
    @Deprecated
    public ResultBody editTask(QuartzTaskInformations taskInformations) {
        try {
            return quartzService.updateTaskNew(taskInformations);

        } catch (Exception e) {
            log.error("/edit/task exception={}", e.fillInStackTrace());
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }

    /**
     * 启动 或者 暂定定时任务
     *
     * @param taskNo
     * @return
     */
    @RequestMapping(value = "/list/pauseOrResume", method = RequestMethod.GET)
    @Deprecated
    public ResultBody optionJob(String taskNo) {
        if (StringUtils.isEmpty(taskNo)) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, "taskNo is required");
        }
        try {
            return quartzService.startJob(taskNo);
        } catch (Exception e) {
            log.error("/list/optionJob exception={}", e);
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
    }


    @RequestMapping(value = "/task/record/page", method = RequestMethod.GET)
    @Deprecated
    public ModelAndView taskRecordPage(ModelAndView modelAndView, @RequestParam(value = "taskNo") String taskNo){
        modelAndView.setViewName("quartz/taskRecords");
        modelAndView.addObject("taskNo", taskNo);
        return modelAndView;
    }

    /**
     * 定时任务记录页面
     *
     * @param taskNo
     * @return
     */
    @RequestMapping(value = "/task/record", method = RequestMethod.GET)
    @Deprecated
    public ResponseEntity taskRecordsPage(@RequestParam(value = "taskNo") String taskNo,
                                      @RequestParam(value = "pageNum") int pageNum,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "5")int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize);
        quartzService.taskRecords(taskNo);
        BootTablePagEntity entity = new BootTablePagEntity();
        entity.setRows(page.getResult());
        entity.setTotal(page.getTotal());
        return ResponseEntity.ok(entity);
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskNo
     * @return
     */
    @RequestMapping(value = "/runTask/rightNow", method = RequestMethod.GET)
    @Deprecated
    public ResultBody runTaskRightNow(@RequestParam(value = "taskNo") String taskNo) {
        try {
            return  quartzService.runTaskRightNow(taskNo);
        } catch (Exception e) {
            log.error("[exception] {}", e.fillInStackTrace());
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
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
    @Deprecated
    public ModelAndView detailTaskErrors(@RequestParam(value = "recordId", required = false) String recordId, ModelAndView model) {
        try {
            if (StringUtils.isEmpty(recordId)) {
                return new ModelAndView("quartz/list");
            }
            QuartzErrorTasks taskErrors = quartzService.detailTaskErrors(recordId);
            model.addObject("taskErrors", taskErrors);
            model.setViewName("quartz/taskErrors");
            return model;
        } catch (Exception e) {
            log.error("");
            return new ModelAndView("quartz/list");
        }
    }


    @RequestMapping("/cron/check")
    @ApiOperation("获取表达式的近十次执行时间")
    @ApiImplicitParam(name = "cron 表达式")
    public ResponseEntity checkCron(@RequestParam("cron")String cron) throws ParseException {
        List<String> result = new ArrayList<String>();
        if (cron == null || cron.length() < 1) {
            return ResponseEntity.ok(result);
        } else {
            CronExpression expression = new CronExpression(cron);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").withLocale( Locale.CHINA ).withZone( ZoneId.systemDefault());
            Date nowaDate = new Date();
            for(int i = 0; i < CIRCLE_COUNT; i++){
                nowaDate = expression.getNextValidTimeAfter(nowaDate);
                result.add(formatter.format(nowaDate.toInstant()));
            }

            return ResponseEntity.ok(result);
        }

    }

    @RequestMapping("/cron/page")
    @ApiOperation("获取计算cron页面")
    public ModelAndView toCronPage(ModelAndView modelAndView){
        modelAndView.setViewName("quartz/cron");
        return modelAndView;
    }
}
