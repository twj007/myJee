package com.jee.service.quartz;


import com.common.model.vo.quartz.QuartzErrorTasks;

public interface QuartzTaskErrorsService {
    Integer addTaskErrorRecord(QuartzErrorTasks quartzTaskErrors);

    QuartzErrorTasks detailTaskErrors(String recordId);
}
