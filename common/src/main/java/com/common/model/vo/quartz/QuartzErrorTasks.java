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

    private String taskexecuterecordid;

    private String errorkey;

    private Long createtime;

    private Long lastmodifytime;

    private String errorvalue;

}
