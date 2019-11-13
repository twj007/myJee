package com.common.model.vo.quartz;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@Data
@EqualsAndHashCode
public class QuartzErrorTasks {
    private Long id;

    private String taskExecuteRecordId;

    private String errorKey;

    private Long createTime;

    private Long lastModifyTime;

    private String errorValue;

}
