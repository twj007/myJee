package com.common.model.dto.common;

import lombok.*;

/***
 **@project: cli
 **@description:
 **@Author: twj
 **@Date: 2019/09/19
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ModuleDetail {

    private Long id;
    private Long pId;
    private String lineContent;

    public ModuleDetail(Long pId, String lineContent) {
        this.pId = pId;
        this.lineContent = lineContent;
    }
}
