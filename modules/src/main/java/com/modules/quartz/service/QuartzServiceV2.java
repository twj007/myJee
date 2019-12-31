package com.modules.quartz.service;

import com.common.utils.ResultBody;
import com.modules.mybatis.modal.TaskError;
import com.modules.mybatis.modal.TaskInformation;
import com.modules.mybatis.modal.TaskRecord;
import org.quartz.SchedulerException;

import java.util.List;

public interface QuartzServiceV2 {

    /**
     * 查询task
     * @param information
     * @return
     */
    List<TaskInformation> listTask(TaskInformation information);

    /***
     * 查询record
     * @param record
     * @return
     */
    List<TaskRecord> listRecord(TaskRecord record);

    /***
     * 查询errors
     * @param error
     * @return
     */
    List<TaskError> listError(TaskError error);

    /***
     * 添加任务
     * @param information
     * @return
     * @throws SchedulerException
     */
    ResultBody addTask(TaskInformation information) throws SchedulerException;

    /***
     * 添加记录
     * @param record
     * @return
     */
    ResultBody addRecord(TaskRecord record);

    /**
     * 添加error
     * @param error
     * @return
     */
    ResultBody addError(TaskError error);

    /***
     * 更新task
     * @param information
     * @return
     */
    int updateTask(TaskInformation information);

    /***
     * 更新record
     * @param record
     * @return
     */
    ResultBody updateRecord(TaskRecord record);

    /***
     * 更新error
     * @param error
     * @return
     */
    ResultBody updateError(TaskError error);

    /***
     * 删除任务
     * @param information
     * @return
     */
    int deleteTask(TaskInformation information);

    /***
     * 部署定时任务
     * @param information
     * @throws SchedulerException
     */
    void schedule(TaskInformation information) throws SchedulerException;

    /***
     * 添加执行记录
     * @param information
     * @return
     * @throws Exception
     */
    TaskRecord addExecuteRecord(TaskInformation information) throws Exception;


    /***
     * 立即运行一次
     * @param information
     * @return
     */
    ResultBody runRightNow(TaskInformation information);

    /***
     * 修改job状态
     * @param information
     * @return
     * @throws SchedulerException
     */
    ResultBody changeStatus(TaskInformation information) throws SchedulerException;

    /***
     * 更新job并重新部署
     * @param information
     * @return
     * @throws SchedulerException
     */
    ResultBody updateAndReDeploy(TaskInformation information) throws SchedulerException;
}
