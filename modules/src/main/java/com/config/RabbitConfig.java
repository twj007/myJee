package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.HashMap;
import java.util.Map;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/11/14
 **/
@Configuration
@Slf4j
public class RabbitConfig {

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    public static final String EXCHANGE_FLASH = "MQ-EXCHANGE_FLASH";
    public static final String EXCHANGE_FANOUT = "MQ-EXCHANGE_FANOUT";
    public static final String EXCHANGE_DEAD = "MQ-EXCHANGE-DEAD-10M";
    public static final String EXCHANGE_MAIL = "MQ-EXCHANGE-MAIL";
    public static final String EXCHANGE_SOCKET = "MQ-EXCHANGE-A";
    public static final String EXCHANGE_TOPIC = "MQ-EXCHANGE-TOPIC";
    public static final String EXCHANGE_UN_READ = "MQ-EXCHANGE-UN-READ";
    public static final String EXCHANGE_READ = "MQ-EXCHANGE-READ";

    public static final String QUEUE_FLASH = "QUEUE_FLASH";
    public static final String QUEUE_DELAY = "QUEUE_DELAY";
    public static final String QUEUE_DEAD = "QUEUE_DEAD";
    public static final String QUEUE_MAIL = "QUEUE_MAIL";
    public static final String QUEUE_A = "QUEUE_A";

    /**
     * 实时消息队列
     */
    public static final String QUEUE_TOPIC = "QUEUE-TOPIC";
    /***
     * 未读消息队列
     */
    public static final String QUEUE_UN_READ = "QUEUE-UN-READ";
    public static final String QUEUE_READ = "QUEUE-READ";

    public static final String ROUTING_KEY_DEAD_10M = "ROUTING-KEY-DELAY-10M";
    public static final String ROUTING_KEY_MAIL = "ROUTING_KEY_MAIL";
    public static final String ROUTING_KEY_A = "51451";
    public static final String ROUTING_KEY_UN_READ_1M = "ROUTING_KEY_UN_READ_1M";
    public static final String ROUTING_KEY_READ = "ROUTING_KEY_READ";
    /**
     * topic 中匹配的路由key * 匹配一个 # 匹配 零个或一个
     */
    public static final String ROUTING_KEY_TOPIC = "order.#";
    public static final String ROUTING_KEY_PUSH_MESSAGE = "message.topic.*";
    public static final String ROUTING_KEY_PUSH_MESSAGE_PREFIX = "message.topic.";


    @Bean("queueA")
    Queue queueA(){
        return new Queue(RabbitConfig.QUEUE_A);
    }

    @Bean("exchangeA")
    DirectExchange exchange(){
        return new DirectExchange(RabbitConfig.EXCHANGE_SOCKET);
    }

    @Bean
    Binding bindingA(@Qualifier("queueA")Queue queue, @Qualifier("exchangeA")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConfig.ROUTING_KEY_A);
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    /***
     * 必须是prototype类型
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /***
     * 正常队列
     * @return
     */
    @Bean("flashQueue")
    public Queue queueTTL(){
        //持久化队列
        Map args = new HashMap<>(16);
        args.put("x-message-ttl", 10000);
        args.put("x-dead-letter-exchange", RabbitConfig.EXCHANGE_DEAD);
        args.put("x-dead-letter-routing-key", RabbitConfig.ROUTING_KEY_DEAD_10M);
        return new Queue(RabbitConfig.QUEUE_FLASH, true, false, false, args);
    }

    /***
     * 死信后接收队列
     * @return
     */
    @Bean("delayQueue")
    public Queue queueDelay(){
        return new Queue(RabbitConfig.QUEUE_DELAY, true);
    }

    /***
     * 交换机通信方式为直连
     * @return
     */
    @Bean("flashFanoutExchange")
    public FanoutExchange flashFanoutExchange(){
        return new FanoutExchange(RabbitConfig.EXCHANGE_FLASH);
    }

    /***
     * 绑定队列到交换机（默认）
     * @return
     */
    @Bean("flashBinding")
    public Binding binding(@Qualifier("flashQueue")Queue queue, @Qualifier("flashFanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    @Bean("delayBinding")
    public Binding bindingDelay(@Qualifier("delayQueue")Queue queue, @Qualifier("flashFanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /***
     * 死信队列
     */
    @Bean("deadQueue")
    public Queue deadQueue(){
        return new Queue(RabbitConfig.QUEUE_DEAD, true);
    }

    /***
     * 死信交换机
     */
    @Bean("deadExchange")
    public DirectExchange deadExchange(){
        return new DirectExchange(RabbitConfig.EXCHANGE_DEAD);
    }

    @Bean("deadBinding")
    public Binding deadBinding(@Qualifier("deadQueue")Queue queue, @Qualifier("deadExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConfig.ROUTING_KEY_DEAD_10M);
    }


    /***
     * 广播方式 绑定的队列都会获取到消息
     * @return
     */
    @Bean("mailExchange")
    public DirectExchange fanoutExchange(){
        return new DirectExchange(RabbitConfig.EXCHANGE_MAIL);
    }


    @Bean("mailQueue")
    public Queue queueFanout(){
        return new Queue(RabbitConfig.QUEUE_MAIL, true);
    }


    @Bean("mailBinding")
    public Binding bindingFanout(@Qualifier("mailQueue")Queue queue, @Qualifier("mailExchange") DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitConfig.ROUTING_KEY_MAIL);
    }

    @Bean("queueTopic")
    Queue queueWorker(){
        Map args = new HashMap<>(16);
        args.put("x-message-ttl", 1000 * 60);
        args.put("x-dead-letter-exchange", RabbitConfig.EXCHANGE_UN_READ);
        args.put("x-dead-letter-routing-key", RabbitConfig.ROUTING_KEY_UN_READ_1M);
        return new Queue(RabbitConfig.QUEUE_TOPIC, true, false, false, args);
    }

    @Bean("topicExchange")
    TopicExchange topicExchange(){
        return new TopicExchange(RabbitConfig.EXCHANGE_TOPIC);
    }

    @Bean("topicBinding")
    Binding topicBinding(@Qualifier("queueTopic")Queue queue, @Qualifier("topicExchange")TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(RabbitConfig.ROUTING_KEY_PUSH_MESSAGE);
    }

    @Bean("unReadExchange")
    FanoutExchange unReadExchange(){
        return new FanoutExchange(RabbitConfig.EXCHANGE_UN_READ);
    }

    @Bean("unReadQueue")
    Queue unReadQueue(){
        return new Queue(RabbitConfig.QUEUE_UN_READ, true);
    }

    @Bean("unReadBinding")
    Binding unReadbinding(@Qualifier("unReadQueue")Queue queue, @Qualifier("unReadExchange")FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean("readExchange")
    DirectExchange readExchange(){
        return new DirectExchange(RabbitConfig.EXCHANGE_READ);
    }

    @Bean("readQueue")
    Queue readQueue(){
        return new Queue(RabbitConfig.QUEUE_READ);
    }

    @Bean("readBinding")
    Binding readBinding(@Qualifier("readQueue")Queue queue, @Qualifier("readExchange")DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(RabbitConfig.ROUTING_KEY_READ);
    }

}
