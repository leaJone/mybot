package com.boot.lea.mybot.service.impl;


import com.boot.lea.mybot.entity.User;
import com.boot.lea.mybot.service.SendService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class SendServiceImpl implements SendService {


    @Override
    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean senMsg(User user) {
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("发送短信中:.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = 1 / 0;
        System.out.println(Thread.currentThread().getName() + "给用户id:" + user.getId() + ",手机号:" + user.getMobile() + "发送短信成功");
        return true;
    }

    @Async
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Boolean senEmail(User user) {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("发送邮件中:.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "给用户id:" + user.getId() + ",邮箱:" + user.getEmail() + "发送邮件成功");
        return true;
    }


}
