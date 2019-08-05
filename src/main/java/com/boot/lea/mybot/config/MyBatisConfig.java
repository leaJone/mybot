package com.boot.lea.mybot.config;


import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJing
 * @ClassName: MyBatisConfig
 * @Description: MyBatisConfig配置类
 * @date 2019/8/1 15:49
 */
@Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                //开启驼峰转换映射查询结果
                configuration.setMapUnderscoreToCamelCase(true);
//                configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
//                configuration.setSafeRowBoundsEnabled(true);
//                configuration.setSafeResultHandlerEnabled(true);
            }
        };
    }
}
