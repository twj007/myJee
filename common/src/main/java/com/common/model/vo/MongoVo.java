package com.common.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/06
 **/
@Data
@EqualsAndHashCode
@ToString
public class MongoVo {

    private Long id;
    private Long brandId;
    private String brandName;
    private BigDecimal price;
    private String unit;
    private String name;
    private String sku;
}
