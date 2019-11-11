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

    private String taskno;

    private String taskname;

    private String schedulerrule;

    private String frozenstatus;

    private String executorno;

    private Long frozentime;

    private Long unfrozentime;

    private Long createtime;

    private Long lastmodifytime;

    private String sendtype;

    private String url;

    private String executeparamter;

    private String timekey;
}
