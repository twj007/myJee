package com.common.model.workflow.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/06
 **/
@Data
@EqualsAndHashCode
@ToString
public class HistoricInstanceVo {
    private String executionId;
    private String name;
    private String localizedName;
    private String parentTaskId;
    private String description;
    private String localizedDescription;
    private String owner;
    private String assignee;
    private String taskDefinitionKey;
    private String formKey;
    private int priority;
    private Date dueDate;
    private Date claimTime;
    private String category;
    private String tenantId = "";
}
