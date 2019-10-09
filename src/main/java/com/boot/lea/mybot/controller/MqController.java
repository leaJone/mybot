package com.boot.lea.mybot.controller;

/**
 * @Title: MqController.java
 * @Package com.boot.lea.mybot.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/9 15:54
 * @version v.3.0
 */

import com.boot.lea.mybot.mq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: MqController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/9 15:54 
 *
 */
@RestController
@RequestMapping("/mq")
public class MqController extends AbstractController  {

    @Autowired
    private MQSender mqSender;

    @GetMapping("/send/lazy")
    public void sendLazy() throws  Exception {
        String msg = "hello lazy";
        System.out.println("测试发送lazy消息====>hello lazy");
        mqSender.sendLazy(msg + ":");
    }

//    @GetMapping("/send/delay")
//    public void sendDelay() throws  Exception {
//        String msg = "hello delay";
//        System.out.println("测试发送delay消息====>hello delay");
//        mqSender.sendDelayMsg(msg,6);
//    }
}
