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
public class QuartzTaskRecords {
    private Long id;

    private String taskNo;

    private String timeKeyValue;

    private Long executeTime;

    private String taskStatus;

    private Integer failCount;

    private String failReason;

    private Long createTime;

    private Long lastModifyTime;
}
