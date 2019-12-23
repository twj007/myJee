package com.component;

import com.alibaba.druid.support.json.JSONUtils;
import com.common.constant.CommonConstants;
import com.common.model.vo.message.EmailEntity;
import com.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 **@project: base
 **@description:
 **@Author: twj
 **@Date: 2019/06/11
 **/
@Component
@Slf4j
public class RabbitReceiver {

    private static AtomicInteger count = new AtomicInteger(0);

//    @RabbitHandler
//    @RabbitListener(queues = RabbitConfig.QUEUE_FLASH)
//    public void process(String content){
//        log.info("【队列msg】: {} {}", content, count.incrementAndGet());
//    }

    /****
     *
     * @param message
     * @param channel
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_DEAD)
    public void processTTL(Message message, Channel channel) throws IOException {
        try{
            channel.basicQos(1);
            log.info("[{}][mq] get dead msg {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), new String(message.getBody()));
            //手动去确认消息已消费完成
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e){
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_DEAD)
    public void processDead(Message message, Channel channel) throws IOException {
        try{
            channel.basicQos(1);
            log.info("[{}] [mq] another delayed msg. {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), new String(message.getBody()));
            //手动去确认消息已消费完成
            Thread.sleep(3000);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e){
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }

    }

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_DELAY)
    public void processDelay(Message content, Channel channel) throws IOException {
        try{
            log.info("[mq] message delayed : {}", content);
            //手动去确认消息已消费完成
            channel.basicAck(content.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e){
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
            channel.basicAck(content.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_MAIL)
    public void  processEmail(Message msg, Channel channel) throws IOException {
        boolean success = false;
        try {

            EmailEntity emailEntity = null;
            byte[] body = msg.getBody();
            ByteArrayInputStream is = new ByteArrayInputStream(body);
            ObjectInputStream ois = new ObjectInputStream(is);
            emailEntity = (EmailEntity) ois.readObject();

            MimeMessage message = mailSender.createMimeMessage();
            message.setSubject(emailEntity.getSubject());
            //发件人，注意中文的处理
            message.setFrom(sender);
            //设置邮件收件人
            message.setRecipients(javax.mail.Message.RecipientType.TO, emailEntity.getReceiver());
            // 整封邮件的MINE消息体 multipart/mixed 可添加附件 > multipart/related 内嵌资源 > multipart/alternative 超文本
            MimeMultipart msgMultipart = new MimeMultipart("alternative");
            //设置邮件的MINE消息体
            message.setContent(msgMultipart);
            //html代码部分
            MimeBodyPart htmlPart = new MimeBodyPart();
            msgMultipart.addBodyPart(htmlPart);
            //html代码
            htmlPart.setContent(emailEntity.getContent(), "text/html;charset=utf-8");

            log.info("【发送邮件】: {}", message);
            mailSender.send(message);
            success = true;
        } catch (MessagingException e) {
            log.error("[{}] [{}] [exception]: exception when create mime {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
        } catch (MailException e){
            log.error("[{}] [{}] [exception]: exception will mail sending {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
        } catch (ClassNotFoundException e){
            log.error("[{}] [{}] [exception]: can not cast class, class not found {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
        } catch (IOException e){
            log.error("[{}] [{}] [exception]: cant read input stream {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
        } catch (Exception e){
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
        } finally{
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), success);
        }

    }

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 保存未读信息
     * @param message
     * @param channel
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_UN_READ)
    public void saveUnRead(Message message, Channel channel){
        boolean success = false;
        try {
            Map map = (Map) JSONUtils.parse(new String(message.getBody()));
            String userId = (String) map.get("userId");
            redisTemplate.opsForList().rightPush(CommonConstants.UN_READ_MESSAGE_PREFIX+userId, map.get("msg"));
            success = true;
        } catch (Exception e){
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());
            e.printStackTrace();
        } finally {
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), success);
            } catch (IOException e1) {
                log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e1.fillInStackTrace());
                e1.printStackTrace();
            }
        }
    }

    /***
     * 方法有问题
     * @param message
     * @param channel
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitConfig.QUEUE_READ)
    public void saveRead(Message message, Channel channel){
        boolean success = false;
        try{
            Map map = (Map) JSONUtils.parse(new String(message.getBody()));
            String userId = (String) map.get("userId");
            List<String> unReadMsg = (ArrayList)map.get("msg");
            long totalSize = redisTemplate.opsForList().size(CommonConstants.READ_MESSAGE_PREFIX + userId) + unReadMsg.size();
            long readSize = redisTemplate.opsForList().size(CommonConstants.READ_MESSAGE_PREFIX + userId);
            if(readSize == 0){

                if(unReadMsg.size() <= CommonConstants.MAX_CACHE_SIZE){
                    redisTemplate.opsForList().rightPushAll(CommonConstants.READ_MESSAGE_PREFIX + userId, unReadMsg);
                }else{
                    redisTemplate.opsForList().rightPushAll(CommonConstants.READ_MESSAGE_PREFIX + userId, unReadMsg.subList(0, 100));
                    // 保存其余部分到数据库
                    log.info("[{}] [{}] [operate jdbc] : {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), unReadMsg.subList(100, unReadMsg.size() - 1));
                }
                unReadMsg.forEach(p->{redisTemplate.opsForList().leftPop(CommonConstants.UN_READ_MESSAGE_PREFIX+userId);});
            }else if(unReadMsg.size() > CommonConstants.MAX_CACHE_SIZE){
                if(unReadMsg.size() - CommonConstants.MAX_CACHE_SIZE > CommonConstants.MAX_CACHE_SIZE){
                    List<String> readMsg = redisTemplate.opsForList().range(CommonConstants.READ_MESSAGE_PREFIX + userId, 0, 100 );
                    redisTemplate.expire(CommonConstants.READ_MESSAGE_PREFIX + userId, 1L, TimeUnit.MILLISECONDS);
                    redisTemplate.opsForList().rightPushAll(CommonConstants.READ_MESSAGE_PREFIX + userId, unReadMsg.subList(0, 100));
                    // save read and unRead
                    log.info("[{}] [{}] [msg] 保存已读数据到数据库 : {}", readMsg.addAll(unReadMsg.subList(100, unReadMsg.size() - 1)));

                }else{
                    List<String> readMsg = redisTemplate.opsForList().range(CommonConstants.READ_MESSAGE_PREFIX + userId, 0, readSize );
                    // 保存readMsg到 数据库
                    log.info("[{}] [{}] [msg] 保存已读数据到数据库 : {}", readMsg);
                    readMsg.forEach(p->{redisTemplate.opsForList().leftPop(CommonConstants.UN_READ_MESSAGE_PREFIX+userId);});
                    redisTemplate.opsForList().rightPushAll(CommonConstants.READ_MESSAGE_PREFIX + userId, readMsg);
                }

            }else{
                unReadMsg.forEach(p->{redisTemplate.opsForList().leftPop(CommonConstants.UN_READ_MESSAGE_PREFIX+userId);});
                redisTemplate.opsForList().rightPushAll(CommonConstants.READ_MESSAGE_PREFIX + userId, unReadMsg);
            }

            success = true;

        }catch(Exception e){
            e.printStackTrace();
            log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e.fillInStackTrace());

        }finally {
            try{
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), success);
            }catch (Exception e1){
                log.error("[{}] [{}] [exception]: {}", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), e1.fillInStackTrace());
                e1.printStackTrace();
            }
        }
    }
}

