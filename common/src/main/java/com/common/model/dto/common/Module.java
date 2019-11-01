package com.common.model.dto.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/09/19
 **/
@Getter
@Setter
@ToString
public class Module {

    private Long id;
    private String description;
    private String lineName;
    private Date createDate;
    private List<ModuleDetail> line;

    private List<String> lineNames;

}
