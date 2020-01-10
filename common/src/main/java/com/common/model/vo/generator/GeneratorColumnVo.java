package com.common.model.vo.generator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/06
 **/
@Data
@EqualsAndHashCode
@ToString
public class GeneratorColumnVo {
    private String column;
    private String type;
    private int length;
    private boolean isNull;
    private boolean isPrimary;
    private boolean autoIncrement;

}
