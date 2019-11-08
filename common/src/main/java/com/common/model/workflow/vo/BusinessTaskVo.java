package com.common.model.workflow.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/05
 **/
@Data
@ToString
@EqualsAndHashCode
public class BusinessTaskVo {
    public static final String DELETE_REASON_COMPLETED = "completed";
    public static final String DELETE_REASON_DELETED = "deleted";
    private static final long serialVersionUID = 1L;
    protected String owner;
    protected int assigneeUpdatedCount;
    protected String originalAssignee;
    protected String assignee;
    protected String parentTaskId;
    protected String name;
    protected String localizedName;
    protected String description;
    protected String localizedDescription;
    protected int priority = 50;
    protected Date createTime;
    protected Date dueDate;
    protected int suspensionState;
    protected String category;
    protected boolean isIdentityLinksInitialized;
    protected String executionId;
    protected String processInstanceId;
    protected String processDefinitionId;
    protected String taskDefinitionKey;
    protected String formKey;
    protected boolean isDeleted;
    protected boolean isCanceled;
    protected String eventName;
    protected String tenantId;
    protected boolean forcedUpdate;
    protected Date claimTime;
}
