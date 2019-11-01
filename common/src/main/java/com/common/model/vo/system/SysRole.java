package com.common.model.vo.system;

import com.common.model.vo.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

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
public class SysRole extends PageInfo {
    private Long id;

    private String name;

    private Date createdate;

    private Date updaedate;

    private String status;

    private String desc;

    private Long userId;
}
