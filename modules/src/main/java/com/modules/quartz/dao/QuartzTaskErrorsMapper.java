package com.modules.quartz.dao;


import com.common.model.vo.quartz.QuartzErrorTasks;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuartzTaskErrorsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(QuartzErrorTasks record);

    int insertSelective(QuartzErrorTasks record);

    QuartzErrorTasks selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuartzErrorTasks record);

    int updateByPrimaryKeyWithBLOBs(QuartzErrorTasks record);

    int updateByPrimaryKey(QuartzErrorTasks record);

    QuartzErrorTasks detailTaskErrors(String recordId);
}
