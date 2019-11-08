package com.common.model.workflow.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/07
 **/
@Data
@EqualsAndHashCode
public class ProcessDefinitionVo {
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
    protected boolean hasStartFormKey;
    protected int suspensionState;
    protected boolean isIdentityLinksInitialized;
    protected String engineVersion;
}
