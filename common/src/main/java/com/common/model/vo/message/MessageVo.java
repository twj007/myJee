package com.common.model.vo.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/19
 **/
@Data
@EqualsAndHashCode
public class MessageVo {
    private String userId;
    private Object data;
}
