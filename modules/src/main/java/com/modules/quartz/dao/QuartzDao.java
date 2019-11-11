package com.modules.quartz.dao;

import com.common.model.vo.quartz.QuartzErrorTasks;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.model.vo.quartz.QuartzTaskRecords;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@Mapper
public interface QuartzDao {
    Integer updateTaskInfoByPrimaryKeySelective(QuartzTaskInformations quartzTaskInformations);

    List<QuartzTaskInformations> getUnfrozenTasks(String status);

    QuartzTaskInformations getTaskByTaskNo(String taskNo);

    Integer selectByTaskNo(String taskno);

    QuartzTaskInformations selectTaskInfoByPrimaryKey(long parseLong);

    List<QuartzTaskInformations> selectTaskList(Map<String, Object> map);

    int insertTaskInfo(QuartzTaskInformations quartzTaskInformations);

    long insertRecord(QuartzTaskRecords quartzTaskRecords);

    QuartzErrorTasks detailTaskErrors(String recordId);

    Integer insertError(QuartzErrorTasks quartzTaskErrors);

    Integer updateRecordByPrimaryKeySelective(QuartzTaskRecords quartzTaskRecords);

    List<QuartzTaskRecords> getTaskRecordsByTaskNo(String taskNo);
}
