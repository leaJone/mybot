package com.boot.lea.mybot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@SpringBootApplication
@MapperScan("com.boot.lea.mybot.mapper.**")
@EnableAspectJAutoProxy(exposeProxy =true)
//@EnableTransactionManagement//1.4+以后默认开启当前为1.5+
public class MybotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybotApplication.class, args);
    }

}
