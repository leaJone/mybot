package com.boot.lea.mybot.mq;

/**
 * @Title: MQReceiver.java
 * @Package com.boot.lea.mybot.mq
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/9 11:51
 * @version v.3.0
 */


import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author LiJing
 * @ClassName: MQReceiver
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/9 11:51
 */
@Component
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = MQConfig.LAZY_QUEUE)
    @RabbitHandler
    public void onLazyMessage(Message msg, Channel channel) throws IOException {
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        System.out.println("延迟队列在延迟后收到消息:lazy receive " + new String(msg.getBody()));
    }

//    @RabbitListener(queues = DEAD_LETTER_QUEUEA_NAME)
//    public void receiveA(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//        log.info("当前时间：{},死信队列A收到消息：{}", new Date().toString(), msg);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
//
//    @RabbitListener(queues = DEAD_LETTER_QUEUEB_NAME)
//    public void receiveB(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//        log.info("当前时间：{},死信队列B收到消息：{}", new Date().toString(), msg);
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }
}