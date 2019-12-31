package com.component;

import com.common.constant.CommonConstants;
import com.common.model.vo.quartz.QuartzTaskRecords;
import com.common.utils.ResultBody;
import com.common.utils.SpringBeanFactoryUtils;
import com.modules.quartz.service.impl.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@DisallowConcurrentExecution
@Slf4j
@Deprecated
public class QuartzMainJobFactory implements Job {

    private AtomicInteger atomicInteger;

    private RestTemplate restTemplate;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        atomicInteger = new AtomicInteger(0);

        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        String id = jobDataMap.getString("id");
        String taskNo = jobDataMap.getString("taskNo");
        String executorNo = jobDataMap.getString("executorNo");
        String sendType = jobDataMap.getString("sendType");
        String url = jobDataMap.getString("url");
        String executeParameter = jobDataMap.getString("executeParameter");
        String version = jobDataMap.getString("version");
        log.info("定时任务被执行:taskNo={},executorNo={},sendType={},url={},executeParameter={}", taskNo, executorNo, sendType, url, executeParameter);
        QuartzService quartzService =  SpringBeanFactoryUtils.getBean(QuartzService.class);
        QuartzTaskRecords records = null;
        try {
            //保存定时任务的执行记录
            records = quartzService.addTaskRecords(taskNo);
            if (null == records || !CommonConstants.INIT.equals(records.getTaskStatus())) {
                log.info("taskNo={}保存执行记录失败", taskNo);
                return;
            }

            if (CommonConstants.HTTP.equals(sendType)) {
                try {
                    if(restTemplate == null){
                        restTemplate = SpringBeanFactoryUtils.getBean(RestTemplate.class);
                    }
                    ResponseEntity result = restTemplate.postForEntity(url, executeParameter, ResultBody.class);
                    log.info("taskNo={}, sendtype={} 执行结果result{}", taskNo, sendType, result);
                    if (StringUtils.isEmpty(result)) {
                        throw new RuntimeException("taskNo=" + taskNo + "http方式返回null");
                    }
                } catch (Exception ex) {
                    log.error("");
                    throw ex;
                }
            } else if (CommonConstants.KAFKA.equals(sendType)) {
                try {
                    String message = new StringBuffer(taskNo).append(":").append(id).append(":").append(executeParameter).toString();
                    quartzService.sendMessage(message);
                    log.info("taskNo={},sendtype={}推送至kafka成功", taskNo, sendType);
                } catch (Exception ex) {
                    log.error("");
                    throw ex;
                }
            } else if (CommonConstants.REDIS.equals(sendType)) {
                try {
                    //这里的话其实可以用http请求代替
                } catch (Exception e) {
                    log.error("[exception] {}", e.fillInStackTrace());
                    throw e;
                }
            }
        } catch (Exception ex) {
            log.error("[exception] : {}", ex.fillInStackTrace());
            atomicInteger.incrementAndGet();
            quartzService.addTaskErrorRecord(records.getId().toString(), taskNo + ":" + ex.getMessage(), ex.getCause().toString());
        }

        quartzService.updateRecordById(atomicInteger.get(), records.getId());
        // 留着，当kafka redis时。记录
    }
}
