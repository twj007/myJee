package com.modules.quartz.service.impl;


import com.common.model.vo.quartz.QuartzTaskRecords;
import com.modules.quartz.dao.QuartzTaskRecordsMapper;
import com.modules.quartz.service.QuartzTaskRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName QuartzTaskRecordsServiceImpl
 * @Description TODO
 * @Author simonsfan
 * @Date 2019/1/3
 * Version  1.0
 */
@Service
public class QuartzTaskRecordsServiceImpl implements QuartzTaskRecordsService {

    @Autowired
    private QuartzTaskRecordsMapper quartzTaskRecordsMapper;

    @Override
    public long addTaskRecords(QuartzTaskRecords quartzTaskRecords) {
        return quartzTaskRecordsMapper.insert(quartzTaskRecords);
    }

    @Override
    public Integer updateTaskRecords(QuartzTaskRecords quartzTaskRecords) {
        return quartzTaskRecordsMapper.updateByPrimaryKeySelective(quartzTaskRecords);
    }

    @Override
    public List<QuartzTaskRecords> listTaskRecordsByTaskNo(String taskNo) {
        return quartzTaskRecordsMapper.getTaskRecordsByTaskNo(taskNo);
    }

    ;

}
