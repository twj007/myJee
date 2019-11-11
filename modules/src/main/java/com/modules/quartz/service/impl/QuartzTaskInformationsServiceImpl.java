package com.modules.quartz.service.impl;


import com.common.constant.CommonConstants;
import com.common.model.vo.quartz.QuartzTaskInformations;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.modules.quartz.dao.QuartzTaskInformationsMapper;
import com.modules.quartz.service.QuartzTaskInformationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResultBody insert(QuartzTaskInformations quartzTaskInformations) {
        String taskNo = quartzTaskInformations.getTaskno();
        quartzTaskInformations.setVersion(0);
        quartzTaskInformations.setCreatetime(System.currentTimeMillis());
        quartzTaskInformations.setLastmodifytime(System.currentTimeMillis());
        Integer count = quartzTaskInformationsMapper.selectByTaskNo(taskNo);
        //判断是否重复任务编号
        if (count > 0) {
            return Results.REPEAT_REQUEST.result("key重复", null);
        }
        int insert = quartzTaskInformationsMapper.insert(quartzTaskInformations);
        if (insert < 1) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @Override
    public List<QuartzTaskInformations> selectList() {
        Map<String, Object> map = new HashMap<>();
        return quartzTaskInformationsMapper.selectList(map);
    }

    @Override
    public QuartzTaskInformations getTaskById(String id) {
        return quartzTaskInformationsMapper.selectByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public ResultBody updateTask(QuartzTaskInformations quartzTaskInformations) {
        Integer count = quartzTaskInformationsMapper.selectByTaskNo(quartzTaskInformations.getTaskno());
        //判断是否重复任务编号
        if (count >= 2) {
            return Results.REPEAT_REQUEST.result("key is duplicate", null);
        }
        //设置解冻时间或冻结时间及最后修改时间
        if (CommonConstants.FROZEN.equals(quartzTaskInformations.getFrozenstatus())) {
            quartzTaskInformations.setFrozentime(System.currentTimeMillis());
        } else if (CommonConstants.UNFROZEN.equals(quartzTaskInformations.getFrozenstatus())) {
            quartzTaskInformations.setUnfrozentime(System.currentTimeMillis());
        }
        quartzTaskInformations.setLastmodifytime(System.currentTimeMillis());
        int updateCount = quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
        //乐观锁控制并发修改
        if (updateCount < 1) {
            return Results.BAD__REQUEST.result(CommonConstants.FAIL, null);
        }
        return Results.SUCCESS.result(CommonConstants.SUCCESS, null);
    }

    @Override
    public QuartzTaskInformations getTaskByTaskNo(String taskNo) {
        return quartzTaskInformationsMapper.getTaskByTaskNo(taskNo);
    }

    @Override
    public Integer updateStatusById(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
    }

    @Override
    public List<QuartzTaskInformations> getUnnfrozenTasks(String status) {
        return quartzTaskInformationsMapper.getUnfrozenTasks(status);
    }

    @Override
    public Integer updateModifyTimeById(QuartzTaskInformations quartzTaskInformations) {
        return quartzTaskInformationsMapper.updateByPrimaryKeySelective(quartzTaskInformations);
    }

}
