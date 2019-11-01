package com.common.model.dto.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/09/20
 **/
@Getter
@Setter
@ToString
public class FormAttr {

    private Long id;
    private Long formId;
    private String attrDesc;
    private String attrDescCn;
    private Date createDate;
    private String status;
    private String icon;
    private String order;
}
