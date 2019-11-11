package com.modules.quartz.service;


import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.utils.ResultBody;

import java.util.List;

public interface QuartzTaskInformationsService {
    ResultBody insert(QuartzTaskInformations quartzTaskInformations);

    List<QuartzTaskInformations> selectList();


    QuartzTaskInformations getTaskById(String id);

    ResultBody updateTask(QuartzTaskInformations quartzTaskInformations);

    QuartzTaskInformations getTaskByTaskNo(String taskNo);

    Integer updateStatusById(QuartzTaskInformations quartzTaskInformations);

    List<QuartzTaskInformations> getUnnfrozenTasks(String status);

    Integer updateModifyTimeById(QuartzTaskInformations quartzTaskInformations);
}
