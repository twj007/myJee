package com.modules.message.service.impl;

import com.common.constant.CommonConstants;
import com.common.model.vo.message.EmailEntity;
import com.common.utils.ResultBody;
import com.common.utils.Results;
import com.component.RabbitProducer;
import com.jee.service.message.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
@Service
public class MessageServiceImpl implements IMessageService {

    private static final String RANDOM_KEY = "verify";

    private static final Long MAX_VALUE = 9999L;

    private static final Long MIN_VALUE = 1000L;

    @Autowired
    private RabbitProducer rabbitProducer;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBody sendPasswordEmail(EmailEntity emailEntity) {
        if(StringUtils.isEmpty(emailEntity) || StringUtils.isEmpty(emailEntity.getReceiver())){
            return Results.BAD__REQUEST.result("邮箱不为空", null);
        }
        emailEntity.setSubject(CommonConstants.FOUND_PASS_SUBJECT);
        String verifyCode = (String) redisTemplate.execute(new RedisCallback() {
                @Override
                public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                    // 在redis中 用 sadd verify "6610" 为verify这个key添加随机数， 再通过 sRandMember 获取随机值
                     String res = new String(redisConnection.sRandMember(RANDOM_KEY.getBytes()));
                     if(res == null){
                         long x = MIN_VALUE;
                         while(x < MAX_VALUE){
                             redisConnection.sAdd(RANDOM_KEY.getBytes(), String.valueOf(x).getBytes());
                             x++;
                         }
                         res = new String(redisConnection.sRandMember(RANDOM_KEY.getBytes()));
                     }
                     return res;
                }
            });

        emailEntity.setContent("<p>您的验证码为<strong>"+verifyCode+"</strong>, 请小心保管。5分钟内有效 </p>");
        redisTemplate.opsForValue().set(emailEntity.getReceiver(), verifyCode, 5 * 60 * 1000, TimeUnit.MILLISECONDS);
        rabbitProducer.sendEmail(emailEntity);
        return Results.SUCCESS.result("邮件发送中，请稍后", null);
    }
}
