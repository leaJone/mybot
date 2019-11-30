package com.boot.lea.mybot;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@EnableAsync
@EnableApiBootSwagger
@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper.**")
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBotApplication.class, args);
    }

    @Bean(name = "delayOrderExecutor")
    ExecutorService getOrderThreadPoolForDelayQueue() {
        return new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(),
                new ThreadFactoryBuilder().setNameFormat("DelayOrder-%d").setDaemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
