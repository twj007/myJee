package com.common.model.vo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/31
 **/
@Data
@EqualsAndHashCode
public class AnnounceVo {
    private Long id;
    private Long announceId;
    private Long userId;

}
