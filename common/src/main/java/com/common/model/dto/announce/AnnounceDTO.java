package com.common.model.dto.announce;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2020/01/02
 **/
@Data
@EqualsAndHashCode
public class AnnounceDTO implements Serializable {
    private Long id;
    private Long authorId;
    private String authorName;
    private String title;
    private String content;
    private String fileUrl;
    private String status;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private Long count;
    private Timestamp sendDate;
    /***
     * 1. 正常  2. 草稿  3. 删除  4. 已全部发送
     */
    private String type;
    /***
     *  B 广播   U 单对单
     */
    private String sendType;
    private List<AnnounceDetailDTO> receiver;
    private List<String> receiverIds;
}
