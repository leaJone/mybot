package com.boot.lea.mybot.jwt;


import java.lang.annotation.*;

/**
 * @ClassName: JwtIgnore
 * @Description: JWT验证忽略注解
 * @author LiJing
 * @date 2019/10/18 11:03 
 *
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
