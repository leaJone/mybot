package com.boot.lea.mybot.service;

import com.boot.lea.mybot.entity.User;

public interface SendService {


    /**
     * 发送短信
     *
     * @param user
     * @return
     */
    Boolean senMsg(User user);

    /**发送邮件
     * @param user
     * @return
     */
    Boolean senEmail(User user);
}
