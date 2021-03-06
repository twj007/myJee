package com.common.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/01
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class PageInfo implements Serializable {
    private static final String DEFAULT_ORDER = "asc";
    private int pageSize;
    private int pageNum;
    private String order;
    private String groupBy;
}
