package com.modules.message.service;

import com.common.model.vo.message.EmailEntity;
import com.common.utils.ResultBody;

/***
 * @author info
 * 消息队列服务
 */
public interface IMessageService {

    /***
     * 发送忘记密码验证码
     * @param emailEntity
     * @return
     */
    ResultBody sendPasswordEmail(EmailEntity emailEntity);

}
