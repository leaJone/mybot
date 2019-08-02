package com.boot.lea.mybot;

import org.minbox.framework.api.boot.autoconfigure.swagger.annotation.EnableApiBootSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper.**")
@EnableAsync
@EnableApiBootSwagger
public class MybotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybotApplication.class, args);
    }

}
