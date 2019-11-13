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
public class QuartzTaskInformations {

    private Long id;

    private Integer version;

    private String taskNo;

    private String taskName;

    private String schedulerRule;

    private String frozenStatus;

    private String executorNo;

    private Long frozenTime;

    private Long unfrozenTime;

    private Long createTime;

    private Long lastModifyTime;

    private String sendType;

    private String url;

    private String executeParamter;

    private String timeKey;
}
