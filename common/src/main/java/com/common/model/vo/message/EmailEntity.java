package com.common.model.vo.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
@Data
@EqualsAndHashCode
public class EmailEntity implements Serializable {

    private String receiver;
    private String subject;
    private String content;


}
