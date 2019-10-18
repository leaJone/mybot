package com.boot.lea.mybot.redis;

/**
 * @Title: LockTest.java
 * @Package com.boot.lea.mybot.redis
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/17 16:05
 * @version v.3.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJing
 * @ClassName: LockTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/10/17 16:05
 */
//@Component
@Slf4j
public class LockTest {

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    private String requestId;

    private static final String key = "testKey";

//    @Scheduled(cron = "0 */1 * * * ?")
    public void execute() throws InterruptedException {
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
                TimeUnit.MILLISECONDS.sleep(800);
                //业务代码，省略...
                log.info("end test,耗时:{}s", (System.currentTimeMillis() - start) / 1000);
            }
        } catch (Exception ex) {
            log.error("test error", ex);
        } finally {
            if (requestId != null) {
                redisDistributedLock.unLock(key, requestId);
            }
        }
    }

    //应用注销时需要删除key
    @PreDestroy
    public void destroy() {
        if (requestId != null) {
            redisDistributedLock.unLock(key, requestId);
        }
    }
}
