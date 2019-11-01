package com.common.model.dto.quartz;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/09/23
 **/
@Getter
@Setter
@ToString
public class JobEntity {

    private String schedName;
    private String jobName;
    private String jobGroup;
    private String description;
    private String isDurable;
    private String isUpdateData;
    private String isNonCurrent;

}
