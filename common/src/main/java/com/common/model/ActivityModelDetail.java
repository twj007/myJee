package com.common.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/19
 **/
@Data
@ToString
public class ActivityModelDetail {
    private Long id;
    private Long planId;
    private Integer year;
    private Integer month;
    private BigDecimal money;
    private Integer fromDay;
    private Integer toDay;
}
