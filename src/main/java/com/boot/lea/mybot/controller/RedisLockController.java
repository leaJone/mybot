package com.boot.lea.mybot.controller;

/**
 * @Title: RedisLockController.java
 * @Package com.boot.lea.mybot.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/17 16:24
 * @version v.3.0
 */

import com.boot.lea.mybot.redis.RedisDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisLockController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/17 16:24 
 *
 */
@RequestMapping("redis")
@Slf4j
@RestController
public class RedisLockController {

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    private String requestId;

    private static final String key = "testKey";

    @GetMapping("/lock")
    public String execute() throws InterruptedException {
        System.out.println("============测试锁=============");
        //获得锁
        boolean lockOk = redisDistributedLock.setLock(key, 3600000);

//        TimeUnit.SECONDS.sleep(10);
        try {
            if (lockOk) {
                Long start = System.currentTimeMillis();
                //记录当前锁对应的requestId
                requestId = redisDistributedLock.get(key);
                log.info("star test.");
                TimeUnit.MILLISECONDS.sleep(8000);
                //业务代码，省略...
                log.info("end test,耗时:{}毫秒", (System.currentTimeMillis() - start));
            }
        } catch (Exception ex) {
            log.error("test error", ex);
        } finally {
            if (requestId != null) {
                redisDistributedLock.unLock(key, requestId);
            }
        }
        return "OK";
    }

}
