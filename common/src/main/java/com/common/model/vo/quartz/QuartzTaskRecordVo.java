package com.common.model.vo.quartz;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/11
 **/
@Data
@EqualsAndHashCode
@ToString
public class QuartzTaskRecordVo {
    private Long id;

    private String taskNo;

    private String timeKeyValue;

    private Long executeTime;

    private String taskStatus;

    private Integer failCount;

    private String failReason;

    private Long createTime;

    private Long lastModifyTime;

    private Long time;

}
