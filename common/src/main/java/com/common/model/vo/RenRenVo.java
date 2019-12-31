package com.common.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/24
 **/
@Data
@ToString
@EqualsAndHashCode
public class RenRenVo {
    private String status;
    private String info;
    private Dataz data;
}
