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

    private String taskno;

    private String timekeyvalue;

    private Long executetime;

    private String taskstatus;

    private Integer failcount;

    private String failreason;

    private Long createtime;

    private Long lastmodifytime;
}
