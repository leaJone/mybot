package com.boot.lea.mybot.mq;

/**
 * @Title: MQConfig.java
 * @Package com.boot.lea.mybot.mq
 * @Description:
 * @author LiJing
 * @date 2019/10/9 11:43
 * @version v.3.0
 */

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiJing
 * @ClassName: MQConfig
 * @Description: *核心概念*
 * * broker：消息队列服务器实体。
 * * exchange：消息交换机，它指定消息按什么规则，路由到哪个队列。
 * * queue：消息队列，每个消息都会被投入到一个或多个队列。
 * * binding：绑定，它的作用就是把exchange和queue按照路由规则绑定起来。
 * * routing Key：路由关键字，exchange根据这个关键字进行消息投递。
 * * vhost：虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离。
 * * producer：消息生产者，就是投递消息的程序。
 * * consumer：消息消费者，就是接受消息的程序。
 * * channel：消息通道，在客户端的每个连接里，可建立多个channel，每个channel代表一个会话任务。
 * <p>
 * （1）客户端连接到消息队列服务器broker，打开一个channel。
 * （2）客户端声明一个exchange，并设置相关属性。
 * （3）客户端声明一个queue，并设置相关属性。
 * （4）客户端使用routing key，在exchange和queue之间建立好绑定关系。
 * （5）客户端投递消息到exchange。
 * （6）exchange接收到消息后，就根据消息的key和已经设置的binding，进行消息路由，将消息投递到一个或多个队列里。
 * @date 2019/10/9 11:43
 */
@Configuration
public class MQConfig {


//        @Bean
//    public ConnectionFactory connectionFactory(){
//        return new CachingConnectionFactory();
//    }
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(){
//        return new RabbitAdmin(connectionFactory());
//    }


    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    public static final String LAZY_EXCHANGE = "Ex.LazyExchange";
    public static final String LAZY_QUEUE = "MQ.LazyQueue";
    public static final String LAZY_KEY = "lazy.#";

    @Bean
    public TopicExchange lazyExchange() {
        Map<String, Object> pros = new HashMap<>();
        //设置交换机支持延迟消息推送
        pros.put("x-delayed-message", "topic");
        TopicExchange exchange = new TopicExchange(LAZY_EXCHANGE, true, false, pros);
        //我们在 Exchange 的声明中可以设置exchange.setDelayed(true)来开启延迟队列，
        // 也可以设置为以下内容传入交换机声明的方法中，因为第一种方式的底层就是通过这种方式来实现的
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    public Queue lazyQueue() {
        return new Queue(LAZY_QUEUE, true);
    }

    @Bean
    public Binding lazyBinding() {
        return BindingBuilder.bind(lazyQueue()).to(lazyExchange()).with(LAZY_KEY);
    }

    @Bean
    public Exchange bootExchange() {
        return new TopicExchange("BOOT-EXCHANGE-1", true, false);
    }

    @Bean
    public Queue bootQueue() {
        return new Queue("boot.queue1", true);
    }
}
