package com.boot.lea.mybot.task;

/**
 * @Title: ScheduledTask.java
 * @Package com.boot.lea.mybot.task
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/16 9:56
 * @version v.3.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author LiJing
 * @ClassName: ScheduledTask
 * @Description: 建议配置线程池进行任务的消费 一个任务能分到一个线程 开启异步
 * @date 2019/10/16 9:56
 */
@Component
public class ScheduledTask {

    private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    /**
     * 每5秒执行一次
     */
    @Scheduled(cron = "*/1 * * * * ?")
//    @Async(value = "TaskExecutor")
    public void doTask1() throws InterruptedException {
        Thread.sleep(6 * 1_000);
        System.out.println(Thread.currentThread().getName() + "===task run1");

        System.out.println(6 * 1_000);
        System.out.println(1000 == 1_000);
    }

    /**
     * 每5秒执行一次
     */
    @Scheduled(fixedDelay = 1 * 1_000)
//    @Async(value = "TaskExecutor")
    public void doTask2() {
        System.out.println(Thread.currentThread().getName() + "===task run2");
    }

    @Scheduled(fixedRate = 1 * 1_000)
//    @Async(value = "TaskExecutor")
    public void doTask3() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "===task run3");
//        Thread.sleep(6*1_000);
    }
}
