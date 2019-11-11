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

    private String taskno;

    private String timekeyvalue;

    private Long executetime;

    private String taskstatus;

    private Integer failcount;

    private String failreason;

    private Long createtime;

    private Long lastmodifytime;

    private Long time;

}
