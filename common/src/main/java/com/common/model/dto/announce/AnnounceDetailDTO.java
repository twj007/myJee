package com.common.model.dto.announce;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Data
@EqualsAndHashCode
public class AnnounceDetailDTO {
    private Long id;
    private Long messageId;
    private Long userId;
    /***
     *  已接收 N 未接收 Y 禁用 F
     */
    private String sendStatus;
    private Timestamp sendDate;
}
