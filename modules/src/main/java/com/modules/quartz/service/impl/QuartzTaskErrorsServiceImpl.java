package com.modules.quartz.service.impl;

import com.common.model.vo.quartz.QuartzErrorTasks;
import com.modules.quartz.dao.QuartzTaskErrorsMapper;
import com.modules.quartz.service.QuartzTaskErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName QuartzTaskErrorsServiceImpl
 * @Description TODO
 * @Author simonsfan
 * @Date 2019/1/3
 * Version  1.0
 */
@Service
public class QuartzTaskErrorsServiceImpl implements QuartzTaskErrorsService {

    @Autowired
    private QuartzTaskErrorsMapper quartzTaskErrorsMapper;

    @Override
    public Integer addTaskErrorRecord(QuartzErrorTasks quartzTaskErrors) {
        return quartzTaskErrorsMapper.insert(quartzTaskErrors);
    }

    @Override
    public QuartzErrorTasks detailTaskErrors(String recordId) {
        return quartzTaskErrorsMapper.detailTaskErrors(recordId);
    }

}
