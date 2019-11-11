package com.modules.quartz.dao;


import com.common.model.vo.quartz.QuartzTaskRecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuartzTaskRecordsMapper {
    int deleteByPrimaryKey(Long id);

    long insert(QuartzTaskRecords record);

    int insertSelective(QuartzTaskRecords record);

    QuartzTaskRecords selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(QuartzTaskRecords record);

    int updateByPrimaryKey(QuartzTaskRecords record);

    List<QuartzTaskRecords> getTaskRecordsByTaskNo(String taskNo);
}
