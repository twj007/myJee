package com.modules.mybatis.dao;

import com.modules.mybatis.modal.TaskInformation;

import java.util.List;

public interface TaskInformationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskInformation record);

    TaskInformation selectByPrimaryKey(Long id);

    List<TaskInformation> selectAll();

    int updateByPrimaryKey(TaskInformation record);

    List<TaskInformation> list(TaskInformation info);
}