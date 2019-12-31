package com.modules.mybatis.dao;

import com.modules.mybatis.modal.TaskError;

import java.util.List;

public interface TaskErrorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskError record);

    TaskError selectByPrimaryKey(Long id);

    List<TaskError> selectAll();

    int updateByPrimaryKey(TaskError record);

    List<TaskError> list(TaskError searchStr);
}