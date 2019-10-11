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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author LiJing
 * @ClassName: MqController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/9 15:54
 */
@RestController
@RequestMapping("/mq")
public class MqController extends AbstractController {

    @Autowired
    private MQSender mqSender;

    //http://localhost:8080/api/mq/send/delay?delayTime=6000
    //http://localhost:8080/api/mq/send/delay?delayTime=10000

    @GetMapping(value = "/send/delay")
    public void sendDelay(int delayTime) {
        String msg = "hello delay";
        System.out.println("发送开始时间:" + LocalDateTime.now() + "测试发送delay消息====>" + msg);
        mqSender.sendDelay(msg, delayTime);
    }


    @GetMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.baidu.com/");
    }
}
