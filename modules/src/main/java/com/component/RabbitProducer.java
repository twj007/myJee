package com.component;

import com.common.model.vo.message.EmailEntity;
import com.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * @param msg work模式下，通过交换机和routekey向对应的队列发布消息 发布订阅
     * @return
     */
    public void send(String msg){
        log.info("【send msg】: {}", msg);
        rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_FLASH,  msg);
    }

    public void sendEmail(EmailEntity emailEntity){
        log.info("[{}] [{}] [mq producer] send email", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        CorrelationData data  = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_MAIL, RabbitConfig.ROUTING_KEY_MAIL, emailEntity, data);
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
