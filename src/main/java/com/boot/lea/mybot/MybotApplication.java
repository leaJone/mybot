package com.boot.lea.mybot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper")
public class MybotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybotApplication.class, args);
    }

}
