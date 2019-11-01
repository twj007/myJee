package com.common.model.dto.form;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/09/20
 **/
@Getter
@Setter
@ToString
public class Form {

    private Long id;
    private String name;
    private String description;
    private String type;
    private Date createDate;
    private List<FormAttr> attributes;
}
