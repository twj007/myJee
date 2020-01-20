package com.common.model;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/19
 **/
@Data
@ToString
public class ActivityModel {

    private Long id;
    private Date startDate;
    private Date endDate;
    private BigDecimal totalMoney;
    private Date createDate;
}
