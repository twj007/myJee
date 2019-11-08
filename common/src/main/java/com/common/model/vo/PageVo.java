package com.common.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/10/31
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class PageVo {
    private static final String DEFAULT_ORDER = "asc";
    private String order;
    private String groupBy;
    private List<?> data;
    private long size;
}
