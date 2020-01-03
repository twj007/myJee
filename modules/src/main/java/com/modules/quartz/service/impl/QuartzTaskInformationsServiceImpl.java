package com.modules.quartz.service.impl;


import com.common.constant.CommonConstants;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.jee.service.quartz.QuartzTaskInformationsService;
import com.modules.quartz.dao.QuartzTaskInformationsMapper;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QuartzTaskInformationsServiceImpl
 * @Description TODO
 * @Author simonsfan
 * @Date 2019/1/3
 * Version  1.0
 */
@Service
public class QuartzTaskInformationsServiceImpl implements QuartzTaskInformationsService {

    @Autowired
    private QuartzTaskInformationsMapper quartzTaskInformationsMapper;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private QuartzService quartzService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody insert(QuartzTaskInformations quartzTaskInformations) throws SchedulerException {
        String taskNo = quartzTaskInformations.getTaskNo();
        quartzTaskInformations.setVersion(0);
        quartzTaskInformations.setCreateTime(System.currentTimeMillis());
        quartzTaskInformations.setLastModifyTime(System.currentTimeMillis());
        Integer count = quartzTaskInformationsMapper.selectByTaskNo(taskNo);
        //判断是否重复任务编号
        if (count > 0) {
            return Results.REPEAT_REQUEST.result("key重复", null);
        }
        int insert = quartzTaskInformationsMapper.insert(quartzTaskInformations);
        if (insert < 1) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
        quartzService.schedule(quartzTaskInformations, scheduler);
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @Override
    public List<QuartzTaskInformations> selectList(String searchStr) {
        Map<String, Object> map = new HashMap<>(16);
        if(searchStr != null){
            map.put("searchStr", searchStr);
        }
        return quartzTaskInformationsMapper.selectList(map);
    }

    @Override
    public QuartzTaskInformations getTaskById(String id) {
        return quartzTaskInformationsMapper.selectByPrimaryKey(Long.parseLong(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody updateTask(QuartzTaskInformations quartzTaskInformations){

        Integer count = quartzTaskInformationsMapper.selectByTaskNo(quartzTaskInformations.getTaskNo());
        //判断是否重复任务编号
        if (count >= 2) {
            return Results.REPEAT_REQUEST.result("key is duplicate", null);
        }
        //设置解冻时间或冻结时间及最后修改时间
        if (CommonConstants.FROZEN.equals(quartzTaskInformations.getFrozenStatus())) {
            quartzTaskInformations.setFrozenTime(System.currentTimeMillis());
        } else if (CommonConstants.UNFROZEN.equals(quartzTaskInformations.getFrozenStatus())) {
            quartzTaskInformations.setUnfrozenTime(System.currentTimeMillis());
        }
        quartzTaskInformations.setLastModifyTime(System.currentTimeMillis());
        Long updateCount = quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
        //乐观锁控制并发修改
        if (updateCount < 1L) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    /***
     * 在更新了规则以后，需要重新部署job
     * @param quartzTaskInformations
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBody updateTaskNew(QuartzTaskInformations quartzTaskInformations) throws SchedulerException {
        if(!scheduler.checkExists(JobKey.jobKey(quartzTaskInformations.getTaskNo(), Scheduler.DEFAULT_GROUP))){
            return Results.BAD__REQUEST.result("job key not found", null);
        }
        //设置解冻时间或冻结时间及最后修改时间
        if (CommonConstants.FROZEN.equals(quartzTaskInformations.getFrozenStatus())) {
            quartzTaskInformations.setFrozenTime(System.currentTimeMillis());
        } else if (CommonConstants.UNFROZEN.equals(quartzTaskInformations.getFrozenStatus())) {
            quartzTaskInformations.setUnfrozenTime(System.currentTimeMillis());
        }
        quartzTaskInformations.setLastModifyTime(System.currentTimeMillis());
        Long updateCount = quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
        //乐观锁控制并发修改
        if (updateCount < 1L) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
        if(scheduler.deleteJob(JobKey.jobKey(quartzTaskInformations.getTaskNo()))){
            // 冻结了则不去重新部署job
            if(!CommonConstants.FROZEN.equals(quartzTaskInformations.getFrozenStatus())){
                quartzService.schedule(quartzTaskInformations, scheduler);
            }
            return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
        }else{
            return Results.BAD__REQUEST.result("delete job failed", null);
        }

    }


    @Override
    public QuartzTaskInformations getTaskByTaskNo(String taskNo) {
        return quartzTaskInformationsMapper.getTaskByTaskNo(taskNo);
    }

    @Override
    public Long updateStatusById(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
    }

    @Override
    public List<QuartzTaskInformations> getUnnfrozenTasks(String status) {
        return quartzTaskInformationsMapper.getUnfrozenTasks(status);
    }

    @Override
    public Long updateModifyTimeById(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
    }



}
