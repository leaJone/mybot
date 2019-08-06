package com.boot.lea.mybot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ExecutorConfig {

    /**
     * 初始线程数.
     */
    private int corePoolSize = 10;
    /**
     * 最大线程数.
     */
    private int maxPoolSize = 200;
    /**
     * 阻塞队列大小.
     */
    private int queueCapacity = 10;


    @Bean
    public Executor mySimpleAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("MySimpleExecutor-d%");
        executor.initialize();
        return executor;
    }


}