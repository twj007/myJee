package com.common.model.vo.system;

import com.common.model.dto.dict.DictDetail;
import com.common.model.vo.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

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
public class SysDict extends PageInfo {
    private Long id;
    private String keyType;
    private String value;
    private String description;
    private List<DictDetail> detail;
    private Date createdate;
    private Date updatedate;
    private String status;
}
