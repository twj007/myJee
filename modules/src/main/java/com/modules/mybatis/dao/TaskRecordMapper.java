package com.modules.mybatis.dao;

import com.modules.mybatis.modal.TaskRecord;

import java.util.List;

public interface TaskRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskRecord record);

    TaskRecord selectByPrimaryKey(Long id);

    List<TaskRecord> selectAll();

    int updateByPrimaryKey(TaskRecord record);

    List<TaskRecord> list(TaskRecord taskNo);
}