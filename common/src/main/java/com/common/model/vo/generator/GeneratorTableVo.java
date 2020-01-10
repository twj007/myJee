package com.common.model.vo.generator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/06
 **/
@Data
@EqualsAndHashCode
@ToString
public class GeneratorTableVo {

    private String tableName;
    private List<GeneratorColumnVo> columns;
}
