package com.modules.quartz.service.impl;

import com.common.constant.CommonConstants;
import com.common.model.vo.quartz.QuartzErrorTasks;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.model.vo.quartz.QuartzTaskRecordVo;
import com.common.model.vo.quartz.QuartzTaskRecords;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.component.QuartzMainJobFactory;
import com.modules.quartz.service.QuartzTaskErrorsService;
import com.modules.quartz.service.QuartzTaskInformationsService;
import com.modules.quartz.service.QuartzTaskRecordsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@Component
@Slf4j
public class QuartzService implements InitializingBean {

    @Autowired
    private SchedulerFactoryBean factoryBean;

    @Autowired
    private QuartzTaskInformationsService quartzTaskInformationsService;

    @Autowired
    private QuartzTaskErrorsService quartzTaskErrorsService;

    @Autowired
    private QuartzTaskRecordsService quartzTaskRecordsService;

    @Autowired
    private SchedulerFactoryBean schedulerBean;

    @Autowired
    private RestTemplate restTemplate;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 列表查询所有定时任务
     *
     * @return
     */
    
    public List<QuartzTaskInformations> getTaskList() {
        List<QuartzTaskInformations> quartzTaskInformations = quartzTaskInformationsService.selectList();
        return quartzTaskInformations;
    }

    /**
     * 新增定时任务
     *
     * @param quartzTaskInformations
     * @return
     */
    
    public ResultBody addTask(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsService.insert(quartzTaskInformations);
    }

    
    public QuartzTaskInformations getTaskById(String id) {
        return quartzTaskInformationsService.getTaskById(id);
    }

    
    public ResultBody updateTask(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsService.updateTask(quartzTaskInformations);
    }

    /**
     * 启动 or 暂停定时任务
     *
     * @param taskNo
     * @return
     * @throws SchedulerException
     */
    
    @Transactional
    public ResultBody startJob(String taskNo) throws SchedulerException {
        QuartzTaskInformations quartzTaskInformation = quartzTaskInformationsService.getTaskByTaskNo(taskNo);
        if (quartzTaskInformation == null) {
            return Results.BAD__REQUEST.result("failed", null);
        }
        String status = quartzTaskInformation.getFrozenstatus();
        Scheduler scheduler = schedulerBean.getScheduler();
        long currentTimeMillis = System.currentTimeMillis();
        QuartzTaskInformations task = new QuartzTaskInformations();
        task.setId(quartzTaskInformation.getId());
        task.setVersion(quartzTaskInformation.getVersion());
        //说明要暂停
        if (CommonConstants.UNFROZEN.equals(status)) {
            scheduler.deleteJob(new JobKey(taskNo));
            task.setFrozentime(currentTimeMillis);
            task.setFrozenstatus(CommonConstants.UNFROZEN);
            //说明要启动
        } else if (CommonConstants.FROZEN.equals(status)) {
            scheduler.deleteJob(new JobKey(taskNo));
            this.schedule(quartzTaskInformation, scheduler);
            task.setUnfrozentime(currentTimeMillis);
            task.setFrozenstatus(CommonConstants.FROZEN);
        }
        task.setLastmodifytime(currentTimeMillis);
        quartzTaskInformationsService.updateStatusById(task);
        log.info("taskNo={}, taskName={}, scheduleRule={},任务{}成功", quartzTaskInformation.getTaskno(), quartzTaskInformation.getTaskname(), quartzTaskInformation.getSchedulerrule(), CommonConstants.FROZEN.equals(status) ? "启动" : "暂停");
        return Results.SUCCESS.result("success", "任务执行成功");
    }

    /**
     * 初始化加载定时任务
     *
     * @throws Exception
     */
    
    public void initLoadOnlineTasks() {
        List<QuartzTaskInformations> unnfrozenTasks = quartzTaskInformationsService.getUnnfrozenTasks(CommonConstants.UNFROZEN);
        if (CollectionUtils.isEmpty(unnfrozenTasks)) {
            log.info("没有需要初始化加载的定时任务");
            return;
        }
        Scheduler scheduler = schedulerBean.getScheduler();
        for (QuartzTaskInformations unnfrozenTask : unnfrozenTasks) {
            try {
                this.schedule(unnfrozenTask, scheduler);
            } catch (Exception e) {
                log.error("系统初始化加载定时任务:task no={},task name={},失败原因:exception={}", unnfrozenTask.getTaskno(), unnfrozenTask.getTaskname(), e);
            }
        }
    }

    /**
     * 初始化加载定时任务
     *
     * @throws Exception
     */

    @Override
    public void afterPropertiesSet() throws Exception {
        this.initLoadOnlineTasks();
    }

    
    public QuartzTaskRecords addTaskRecords(String taskNo) {
        QuartzTaskRecords quartzTaskRecords = null;
        try {
            QuartzTaskInformations quartzTaskInformation = quartzTaskInformationsService.getTaskByTaskNo(taskNo);
            if (null == quartzTaskInformation || CommonConstants.FROZEN.equals(quartzTaskInformation.getFrozenstatus())) {
                log.info("taskNo={} not exist or status is frozen!");
                return null;
            }
            long currentTimeMillis = System.currentTimeMillis();
            QuartzTaskInformations task = new QuartzTaskInformations();
            task.setId(quartzTaskInformation.getId());
            task.setLastmodifytime(currentTimeMillis);
            quartzTaskInformationsService.updateTask(task);
            log.info("taskNo={},taskName={}更新最后修改时间成功", quartzTaskInformation.getTaskno(), quartzTaskInformation.getTaskname());

            quartzTaskRecords = new QuartzTaskRecords();
            quartzTaskRecords.setTaskno(taskNo);
            quartzTaskRecords.setTimekeyvalue(quartzTaskInformation.getTimekey());
            quartzTaskRecords.setExecutetime(currentTimeMillis);
            quartzTaskRecords.setTaskstatus(CommonConstants.INIT);
            quartzTaskRecords.setFailcount(0);
            quartzTaskRecords.setFailreason("");
            quartzTaskRecords.setCreatetime(currentTimeMillis);
            quartzTaskRecords.setLastmodifytime(currentTimeMillis);
            quartzTaskRecordsService.addTaskRecords(quartzTaskRecords);
            log.info("taskNo={},taskName={}添加执行记录表成功", quartzTaskInformation.getTaskno(), quartzTaskInformation.getTaskname());
        } catch (Exception ex) {
            log.error("添加执行记录表异常exceptio={}", ex);
            return null;
        }
        return quartzTaskRecords;
    }

    
    public Integer updateRecordById(Integer count, Long id) {
        QuartzTaskRecords records = new QuartzTaskRecords();
        records.setId(id);
        records.setFailcount(count);
        records.setLastmodifytime(System.currentTimeMillis());
        if (count > 0) {
            records.setTaskstatus(CommonConstants.FAIL);
        } else {
            records.setTaskstatus(CommonConstants.SUCCESS);
        }
        return quartzTaskRecordsService.updateTaskRecords(records);
    }

    
    public Integer updateModifyTimeById(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsService.updateModifyTimeById(quartzTaskInformations);
    }

    
    public Integer addTaskErrorRecord(String id, String errorKey, String errorValue) {
        QuartzErrorTasks taskErrors = new QuartzErrorTasks();
        taskErrors.setTaskexecuterecordid(String.valueOf(id));
        taskErrors.setErrorkey(errorKey);
        taskErrors.setCreatetime(System.currentTimeMillis());
        taskErrors.setLastmodifytime(System.currentTimeMillis());
        taskErrors.setErrorvalue(errorValue);
        return quartzTaskErrorsService.addTaskErrorRecord(taskErrors);
    }

    public void schedule(QuartzTaskInformations quartzTaskInfo, Scheduler scheduler) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzTaskInfo.getTaskno(), Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(QuartzMainJobFactory.class).withDescription(quartzTaskInfo.getTaskname()).withIdentity(quartzTaskInfo.getTaskno(), Scheduler.DEFAULT_GROUP).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("id", quartzTaskInfo.getId().toString());
        jobDataMap.put("taskNo", quartzTaskInfo.getTaskno());
        jobDataMap.put("executorNo", quartzTaskInfo.getExecutorno());
        jobDataMap.put("sendType", quartzTaskInfo.getSendtype());
        jobDataMap.put("url", quartzTaskInfo.getUrl());
        jobDataMap.put("executeParameter", quartzTaskInfo.getExecuteparamter());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzTaskInfo.getSchedulerrule());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withDescription(quartzTaskInfo.getTaskname()).withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
        log.info("taskNo={},taskName={},scheduleRule={} load to quartz success!", quartzTaskInfo.getTaskno(), quartzTaskInfo.getTaskname(), quartzTaskInfo.getSchedulerrule());
    }

    /**
     * kafka推送消息
     * // kafkaTemplate.send(QUARTZ_TOPIC, message)
     * @param message
     */
    
    public void sendMessage(String message) {

        log.info("给kafka推送消息message={}成功", message);
    }

    
    public List<QuartzTaskRecordVo> taskRecords(String taskNo) {
        List<QuartzTaskRecords> quartzTaskRecords = quartzTaskRecordsService.listTaskRecordsByTaskNo(taskNo);
        QuartzTaskRecordVo recordsVo = null;
        List<QuartzTaskRecordVo> voList = new ArrayList<>();
        for (QuartzTaskRecords quartzTaskRecord : quartzTaskRecords) {
            recordsVo = new QuartzTaskRecordVo();
            BeanUtils.copyProperties(quartzTaskRecord, recordsVo);
            recordsVo.setTime(quartzTaskRecord.getLastmodifytime() - quartzTaskRecord.getCreatetime());
            voList.add(recordsVo);
        }
        return voList;
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskNo
     * @return
     */
    
    public ResultBody runTaskRightNow(String taskNo) {
        atomicInteger = new AtomicInteger(0);
        QuartzTaskInformations quartzTaskInformations = quartzTaskInformationsService.getTaskByTaskNo(taskNo);
        if (quartzTaskInformations == null) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, "no data available");
        }
        Long id = quartzTaskInformations.getId();
        String sendType = quartzTaskInformations.getSendtype();
        String executorNo = quartzTaskInformations.getExecutorno();
        String url = quartzTaskInformations.getUrl();
        String executeParameter = quartzTaskInformations.getExecuteparamter();
        log.info("定时任务被执行:taskNo={},executorNo={},sendType={},url={},executeParameter={}", taskNo, executorNo, sendType, url, executeParameter);
        QuartzTaskRecords records = null;
        try {
            //保存定时任务的执行记录
            records = this.addTaskRecords(taskNo);
            if (null == records || !CommonConstants.INIT.equals(records.getTaskstatus())) {
                log.info("taskNo={}立即运行失--->>保存执行记录失败", taskNo);
                return Results.BAD__REQUEST.result(CommonConstants.FAIL, "execute failed");
            }

            if (CommonConstants.HTTP.equals(sendType)) {
                try {
                    ResponseEntity responseEntity = restTemplate.postForEntity(url, executeParameter, ResultBody.class);
                    log.info("send http request");
                } catch (Exception ex) {
                    log.error("[exception]: {}", ex.fillInStackTrace());
                    atomicInteger.incrementAndGet();
                    throw ex;
                }
            } else if (CommonConstants.KAFKA.equals(sendType)) {
                try {
                    String message = new StringBuffer(taskNo).append(":").append(executeParameter).toString();
                    this.sendMessage(message);
                } catch (Exception ex) {
                    log.error("[exception]: {}", ex.fillInStackTrace());
                    atomicInteger.incrementAndGet();
                    throw ex;
                }
            }
        } catch (Exception ex) {
            log.error("[exception] : {}", ex.fillInStackTrace());
            atomicInteger.incrementAndGet();
            this.addTaskErrorRecord(records.getId().toString(), taskNo + ":" + ex.getMessage(), ex.getCause().toString());
        }
        //更改record表的执行状态、最后修改时间、失败个数
        this.updateRecordById(atomicInteger.get(), records.getId());

        //更新taskinfo表的最后修改时间
        QuartzTaskInformations quartzTaskInformation = new QuartzTaskInformations();
        quartzTaskInformation.setId(id);
        quartzTaskInformation.setLastmodifytime(System.currentTimeMillis());
        this.updateTask(quartzTaskInformation);
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    
    public QuartzErrorTasks detailTaskErrors(String recordId) {
        return quartzTaskErrorsService.detailTaskErrors(recordId);
    }
}
