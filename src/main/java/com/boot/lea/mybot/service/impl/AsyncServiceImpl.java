package com.boot.lea.mybot.service.impl;

import com.boot.lea.mybot.service.AsyncService;
import com.boot.lea.mybot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
@Async("taskExecutor")
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private UserService userService;


    @Override
    public Future<Long> queryUserMsgCount(final Long userId) {
        System.out.println("当前线程:" + Thread.currentThread().getName() + "=-=====queryUserMsgCount");
        long countByUserId = userService.countMsgCountByUserId(userId);
        return new AsyncResult<>(countByUserId);
    }


    @Override
    public Future<Long> queryCollectCount(final Long userId) {
        System.out.println("当前线程:" + Thread.currentThread().getName() + "=-====queryCollectCount");
        long collectCount = userService.countCollectCountByUserId(userId);
        return new AsyncResult<>(collectCount);
    }

}
