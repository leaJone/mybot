package com.boot.lea.mybot.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NeedSetFiledValue
 * @Description: 利用注解+aop实现关联查询
 * @author LiJing
 * @date 2019/8/9 15:32 
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetFiledValue {
}

