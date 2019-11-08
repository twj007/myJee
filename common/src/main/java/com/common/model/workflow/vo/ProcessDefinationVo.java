package com.common.model.workflow.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/05
 **/
@Data
@ToString
@EqualsAndHashCode
public class ProcessDefinationVo {
    protected String name;
    protected String description;
    protected String key;
    protected int version;
    protected String category;
    protected String deploymentId;
    protected String resourceName;
    protected String tenantId = "";
    protected Integer historyLevel;
    protected String diagramResourceName;
    protected boolean isGraphicalNotationDefined;
    protected Map<String, Object> variables;
    protected boolean hasStartFormKey;
    protected int suspensionState;
    protected boolean isIdentityLinksInitialized;
    protected String engineVersion;
}
