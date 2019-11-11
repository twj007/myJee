package com.modules.quartz.service;


import com.common.model.vo.quartz.QuartzErrorTasks;

public interface QuartzTaskErrorsService {
    Integer addTaskErrorRecord(QuartzErrorTasks quartzTaskErrors);

    QuartzErrorTasks detailTaskErrors(String recordId);
}
