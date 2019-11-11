package com.config;

import com.component.MyTestJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@Configuration
public class QuartzConfiguration {
    @Bean
    Scheduler scheduler(SchedulerFactoryBean factory, JobDetail myTestJob) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.addJob(myTestJob, true);
        return scheduler;
    }

    @Bean("myTestJob")
    JobDetail myTestJob(){
        return JobBuilder.newJob(MyTestJob.class)
                .withIdentity("myTestJob")
                .storeDurably()
                .withDescription("[it's a job using cron expression]")
                .build();
    }

    /**
     * 把jobDetail注册到Cron表达式的trigger上去
     */
    @Bean
    public Trigger cronJobTrigger(JobDetail myTestJob) {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        return TriggerBuilder.newTrigger()
                .forJob(myTestJob)
                .withIdentity("myCornJob")
                .withSchedule(cronScheduleBuilder)
                .build();
    }


//    @Bean
//    SchedulerFactory factory(){
//        return new StdSchedulerFactory();
//    }
//
//    @Bean
//    @Primary
//    SchedulerFactoryBean schedulerFactoryBean(SchedulerFactory s, @Qualifier("systemDatasource") DataSource dataSource){
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        factoryBean.setDataSource(dataSource);
//        // factoryBean.setQuartzProperties()
//        factoryBean.setSchedulerFactory(s);
//        factoryBean.setDataSource(dataSource);
//        return factoryBean;
//    }

}
