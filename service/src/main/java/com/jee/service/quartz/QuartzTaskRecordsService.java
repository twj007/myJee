package com.jee.service.quartz;

import com.common.model.vo.quartz.QuartzTaskRecords;

import java.util.List;

public interface QuartzTaskRecordsService {

    long addTaskRecords(QuartzTaskRecords quartzTaskRecords);

    Integer updateTaskRecords(QuartzTaskRecords quartzTaskRecords);

    List<QuartzTaskRecords> listTaskRecordsByTaskNo(String taskNo);

}
