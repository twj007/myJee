package com.modules.quartz.service.impl;

import com.common.constant.CommonConstants;
import com.common.model.dto.quartz.TaskError;
import com.common.model.dto.quartz.TaskInformation;
import com.common.model.dto.quartz.TaskRecord;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.component.ManageableJob;
import com.jee.service.quartz.QuartzServiceV2;
import com.modules.mybatis.dao.TaskErrorMapper;
import com.modules.mybatis.dao.TaskInformationMapper;
import com.modules.mybatis.dao.TaskRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/25
 **/
@Service
@Slf4j
public class QuartzServiceV2Impl implements QuartzServiceV2, InitializingBean {

    @Autowired
    private TaskInformationMapper informationMapper;

    @Autowired
    private TaskErrorMapper taskErrorMapper;

    @Autowired
    private TaskRecordMapper taskRecordMapper;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public List<TaskInformation> listTask(TaskInformation information) {
        return informationMapper.list(information);
    }

    @Override
    public List<TaskRecord> listRecord(TaskRecord record) {
        return taskRecordMapper.list(record);
    }

    @Override
    public List<TaskError> listError(TaskError error) {
        return taskErrorMapper.list(error);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBody addTask(TaskInformation information) throws SchedulerException {
        information.setVersion(0);
        information.setCreatetime(System.currentTimeMillis());
        TaskInformation info = new TaskInformation();
        info.setTaskno(information.getTaskno());
        if(informationMapper.list(info).size() > 0){
            log.warn("[quartz job] 添加定时任务失败");
            return Results.BAD__REQUEST.result("该job已存在", null);
        }
        int num = informationMapper.insert(information);
        if(num > 0){
            // 部署定时任务到quartz
            this.schedule(information);
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("保存job失败", null);

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBody addRecord(TaskRecord record) {
        int num = taskRecordMapper.insert(record);
        if(num > 0){
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("保存record失败", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBody addError(TaskError error) {

        int num =  taskErrorMapper.insert(error);
        if(num > 0){
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("保存错误信息失败", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int updateTask(TaskInformation information) {
        return informationMapper.updateByPrimaryKey(information);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public ResultBody updateRecord(TaskRecord record) {
        int num = taskRecordMapper.updateByPrimaryKey(record);
        if( num > 0 ){
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("更新失败", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public ResultBody updateError(TaskError error) {
        int num =  taskErrorMapper.updateByPrimaryKey(error);
        if( num > 0 ){
            return Results.SUCCESS.result("success", null);
        }
        return Results.BAD__REQUEST.result("更新失败", null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public int deleteTask(TaskInformation information) {
        return informationMapper.deleteByPrimaryKey(information.getId());
    }


    @Override
    public void schedule(TaskInformation information) throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(information.getTaskno(), Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(ManageableJob.class).withDescription(information.getTaskname()).withIdentity(information.getTaskno(), Scheduler.DEFAULT_GROUP).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("id", information.getId().toString());
        jobDataMap.put("taskNo", information.getTaskno());
        jobDataMap.put("executorNo", information.getExecutorno());
        jobDataMap.put("sendType", information.getSendtype());
        jobDataMap.put("url", information.getUrl());
        jobDataMap.put("executeParameter", information.getExecuteparamter());
        jobDataMap.put("version", information.getVersion());
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(information.getSchedulerrule());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withDescription(information.getTaskname()).withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        if(scheduler.checkExists(jobDetail.getKey())){
            log.info("[job deploy] scheduler already bean stored, so we delete and reschedule");
            scheduler.deleteJob(jobDetail.getKey());
        }
        scheduler.scheduleJob(jobDetail, cronTrigger);
        log.info("taskNo={},taskName={},scheduleRule={} load to quartz success!", information.getTaskno(), information.getTaskname(), information.getSchedulerrule());
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.NESTED)
    public TaskRecord addExecuteRecord(TaskInformation information) throws Exception {

        long happenedTime = System.currentTimeMillis();

        information.setLastmodifytime(happenedTime);
        if(updateTask(information) == 0){
            log.error("保存运行时间失败");
            throw new Exception("保存运行时间失败");
        }
        TaskRecord record = new TaskRecord();
        record.setTaskno(information.getTaskno());
        record.setExecutetime(happenedTime);
        record.setTimekeyvalue(information.getTimekey());
        record.setCreatetime(System.currentTimeMillis());
        taskRecordMapper.insert(record);
        return record;
    }


    @Override
    public ResultBody runRightNow(TaskInformation information) {
        boolean success = false;
        List<TaskInformation> informations = informationMapper.list(information);
        if(CollectionUtils.isEmpty(informations)){
            return Results.BAD__REQUEST.result("未找到符合的job", null);
        }
        TaskInformation info = informations.get(0);
        if(CommonConstants.FROZEN.equals(info.getFrozenstatus().toUpperCase())){
            return Results.BAD__REQUEST.result("请先启动该job再运行", null);
        }
        log.info("[quartz job] 手动执行job");

        log.info("[quartz job] 创建执行记录");
        TaskRecord record;
        try {
            record = addExecuteRecord(info);
        } catch (Exception e) {
            log.error("[execute jon] exception when execute: {}", e.fillInStackTrace());
            return Results.BAD__REQUEST.result("执行失败, 添加记录失败", null);
        }
        if(CommonConstants.HTTP.equals(info.getSendtype())){
            try{
                ResponseEntity responseEntity = restTemplate.postForEntity(info.getUrl(), information.getExecuteparamter(), ResultBody.class);
                if(responseEntity.getStatusCode().is2xxSuccessful()){
                    log.info("[quartz job] 执行成功: {}", responseEntity.getBody());
                    success = true;
                }else{
                    log.error("[quartz job] job 执行失败： {}", responseEntity.getBody());
                    return Results.BAD__REQUEST.result("job执行请求失败", null);
                }
            }catch (Exception e){
                log.info("[quartz job] 执行job时发送异常： {}", e.fillInStackTrace());
                return Results.BAD__REQUEST.result("job执行异常", null);
            }
        }
        if(CommonConstants.KAFKA.equals(information.getSendtype())){
            log.info("执行http请求调用卡夫卡");
        }
        /***
         * 更新执行状态
         */
        record.setFailcount(success ? 0 : 1);
        if(record.getFailcount() > 0){
            record.setTaskstatus(CommonConstants.FAIL);
        }else{
            record.setTaskstatus(CommonConstants.SUCCESS);
        }
        return updateRecord(record);
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public ResultBody changeStatus(TaskInformation information) throws SchedulerException {
        int num = informationMapper.updateByPrimaryKey(information);
        if(num > 0){
            TaskInformation info = informationMapper.list(information).get(0);
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            if(CommonConstants.FROZEN.equals(info.getFrozenstatus().toUpperCase())&& scheduler.checkExists(JobKey.jobKey(info.getTaskno()))){
                scheduler.deleteJob(JobKey.jobKey(info.getTaskno()));
            }
            if(CommonConstants.UNFROZEN.equals(info.getFrozenstatus().toUpperCase())){
                if(scheduler.checkExists(JobKey.jobKey(info.getTaskno()))){
                    scheduler.deleteJob(JobKey.jobKey(info.getTaskno()));
                }
                schedule(info);
            }
            return Results.SUCCESS.result("success", null);
        }else{
            return Results.BAD__REQUEST.result("保存失败", null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public ResultBody updateAndReDeploy(TaskInformation information) throws SchedulerException {
       int num = updateTask(information);
       if(num > 0) {
           if(CommonConstants.UNFROZEN.equals(information.getFrozenstatus().toUpperCase())){
               schedule(information);
           }
           if(CommonConstants.FROZEN.equals(information.getFrozenstatus().toUpperCase())){
               Scheduler scheduler = schedulerFactoryBean.getScheduler();
               if(scheduler.checkExists(JobKey.jobKey(information.getTaskno()))){
                   scheduler.deleteJob(JobKey.jobKey(information.getTaskno()));
               }
           }
           return Results.SUCCESS.result("success", null);
       }else {
           return Results.BAD__REQUEST.result("更新失败", null);
       }
    }


    /****
     * 初始化job
     * 在service初始化后去查询并部署管理表中未冻结的job
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        TaskInformation information = new TaskInformation();
        information.setFrozenstatus(CommonConstants.UNFROZEN);
        List<TaskInformation> informations = listTask(information);
        if(CollectionUtils.isEmpty(informations)){
            log.info("[after propertiesSet] 未冻结任务为空，不需要部署");
        }else{
            for(TaskInformation unFrozenInformation : informations){
                try {
                    this.schedule(unFrozenInformation);
                } catch(Exception e){
                    log.error("[afterPropertiesSet] 部署定时任务时发生异常：{}", e.fillInStackTrace());
                }
            }
        }
    }


}
