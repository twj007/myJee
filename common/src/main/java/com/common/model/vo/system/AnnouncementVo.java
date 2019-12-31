package com.common.model.vo.system;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/15
 **/
@Data
@EqualsAndHashCode
public class AnnouncementVo {

    private long id;
    private String title;
    private String content;
    private String author;
    private long authorId;
    /**
     * 1. 公共  2.个人
     */
    private String type;
    /***
     * 前台字符串截取获取多个url
     */
    private String fileUrl;
    private MultipartFile[] files;
    /***
     * 1. 正常  2. 禁用  3.草稿
     */
    private String status;
    private Date createDate;
    private Date modifyDate;
    private List<Long> receiver;
}
