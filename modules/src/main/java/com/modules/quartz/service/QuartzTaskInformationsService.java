package com.modules.quartz.service;


import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.utils.ResultBody;
import org.quartz.SchedulerException;

import java.util.List;

public interface QuartzTaskInformationsService {
    ResultBody insert(QuartzTaskInformations quartzTaskInformations) throws SchedulerException;

    List<QuartzTaskInformations> selectList(String searchStr);


    QuartzTaskInformations getTaskById(String id);

    ResultBody updateTask(QuartzTaskInformations quartzTaskInformations);

    QuartzTaskInformations getTaskByTaskNo(String taskNo);

    Long updateStatusById(QuartzTaskInformations quartzTaskInformations);

    List<QuartzTaskInformations> getUnnfrozenTasks(String status);

    Long updateModifyTimeById(QuartzTaskInformations quartzTaskInformations);

    ResultBody updateTaskNew(QuartzTaskInformations quartzTaskInformations) throws SchedulerException;

}
