package com.component;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/

@Slf4j
public class MyTestJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();

        log.info("[{}] [{}]: execute my job", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Thread.currentThread().getName());
        log.info("execute job expend time: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
