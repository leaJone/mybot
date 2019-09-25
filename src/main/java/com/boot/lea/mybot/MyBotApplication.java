package com.boot.lea.mybot;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableApiBootSwagger
@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper.**")
@EnableAspectJAutoProxy(exposeProxy = true)
public class MyBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBotApplication.class, args);
    }

}
