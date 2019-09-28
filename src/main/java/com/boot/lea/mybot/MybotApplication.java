package com.boot.lea.mybot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper.**")
@EnableAsync
public class MybotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybotApplication.class, args);
    }

}
