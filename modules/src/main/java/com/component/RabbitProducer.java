package com.component;

import com.alibaba.druid.support.json.JSONUtils;
import com.common.model.vo.message.EmailEntity;
import com.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

/***
 **@project: base
 **@description:
 **@Author: twj
 **@Date: 2019/06/11
 * 消息生产者
 **/
@Component
@Slf4j
public class RabbitProducer implements RabbitTemplate.ConfirmCallback {


    private  RabbitTemplate rabbitTemplate;

    /***
     * 设置回调为该类的confirm方法
     * @param rabbitTemplate
     */
    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }


    /****
     *
     * @param msg work模式下，通过交换机和route key向对应的队列发布消息 发布订阅
     * @return
     */
    public void send(String msg){
        log.info("【生产消息】: {}", msg);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_FLASH,  msg);
    }

    public void sendEmail(EmailEntity emailEntity){
        log.info("[{}] [{}] [生产消息] 发送邮件", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        CorrelationData data  = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_MAIL, RabbitConfig.ROUTING_KEY_MAIL, emailEntity, data);
    }

    public void sendSocket(String msg){
        log.info("[{}] [{}] [生产消息] 推送连接", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_SOCKET, RabbitConfig.ROUTING_KEY_A, msg);
    }

    /***
     * 实时消息推送
     */
    public void sendMessageSocket(Map map){
        log.info("[{}] [{}] [生产消息] 推送发布消息", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_TOPIC, RabbitConfig.ROUTING_KEY_PUSH_MESSAGE_PREFIX+map.get("userId"), JSONUtils.toJSONString(map));
    }

    /***
     * 保存已读消息
     */
    public void sendReadMessage(Map map){
        log.info("[{}] [{}] [生产消息] 历史消息", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_READ, RabbitConfig.ROUTING_KEY_READ, JSONUtils.toJSONString(map));
    }


    /***
     * 广播队列消息发送 绑定了对应交换机的队列都会接收到下列消息
     * @param msg
     */
    public void sendAll(String msg){
        log.info("【send all】: {}", msg);
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT, "", msg, data);
    }

    public void sendOrder(Object obj){
        log.info("【send order】: {}" + obj);
        CorrelationData data = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_FANOUT, "", obj, data);
    }

    /****
     * 发送消息后的回调 b为true 发送成功且接收成功 b为false 接收失败
     * @param correlationData
     * @param b
     * @param s
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        log.info("[correlationData] :{}", correlationData);
        log.info("【final callback】: {}", s);
        if(b){
            log.info("【result】: send success");
        }else{
            log.error("【send error】: {}", s);
        }
    }



}
