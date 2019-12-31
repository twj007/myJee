package com.component;

import com.common.constant.CommonConstants;
import com.common.utils.ResultBody;
import com.common.utils.SpringBeanFactoryUtils;
import com.modules.mybatis.modal.TaskInformation;
import com.modules.mybatis.modal.TaskRecord;
import com.modules.quartz.service.QuartzServiceV2;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * 自己实现的可管理的job
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/26
 **/
@DisallowConcurrentExecution
@Slf4j
public class ManageableJob implements Job {


    private RestTemplate restTemplate;

    /***
     * 1.获取job的DataMap， 2.获取执行方式，通过对应组件去执行。
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer count = 0;
        JobDataMap map = jobExecutionContext.getMergedJobDataMap();
        log.info("[quartz job] 执行定时任务 [id: {}, taskNo: {}, executorNo: {}, url: {}, executeParameter: {}, sendType: {}, version: {}]",
                map.get("id"), map.get("taskNo"), map.get("executeNo"), map.get("url"), map.get("executeParameter"), map.get("sendType"), map.get("version"));
        QuartzServiceV2 quartzService = SpringBeanFactoryUtils.getBean(QuartzServiceV2.class);
        log.info("[quartz job] 创建执行记录");
        TaskInformation information = new TaskInformation();
        information.setTaskno((String) map.get("taskNO"));
        information.setSendtype((String) map.get("sendType"));
        information.setExecuteparamter((String) map.get("executeParameter"));
        information.setUrl((String) map.get("url"));
        TaskRecord record = null;
        try {
            record = quartzService.addExecuteRecord(information);
        } catch (Exception e) {
            log.error("[execute jon] exception when execute: {}", e.getStackTrace());
            count++;
        }
        if(CommonConstants.HTTP.equals(information.getSendtype())){
            try{
                if(restTemplate == null) {
                    restTemplate = SpringBeanFactoryUtils.getBean(RestTemplate.class);
                }
                ResponseEntity responseEntity = restTemplate.postForEntity(information.getUrl(), information.getExecuteparamter(), ResultBody.class);
                if(responseEntity.getStatusCode().is2xxSuccessful()){
                    log.info("[quartz job] 执行成功: {}", responseEntity.getBody());
                }else{
                    log.error("[quartz job] job 执行失败： {}", responseEntity.getBody());
                    count++;
                }
            }catch (Exception e){
                log.info("[quartz job] 执行job时发送异常： {}", e.getStackTrace());
                count++;
            }
        }
        if(CommonConstants.KAFKA.equals(information.getSendtype())){
            log.info("执行http请求调用卡夫卡");
        }
        /***
         * 更新执行状态
         */
        record.setFailcount(count);
        if(record.getFailcount() > 0){
            record.setTaskstatus(CommonConstants.SUCCESS);
        }else{
            record.setTaskstatus(CommonConstants.FAIL);
        }
        quartzService.updateRecord(record);

    }
}
