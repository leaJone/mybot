package com.boot.lea.mybot.annotation;

/**
 * @Title: NeedSetValue.java
 * @Package com.boot.lea.mybot.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:31
 * @version v.3.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NeedSetValue
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:31 
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetValue{
    // 调用的类
    Class<?> beanClass();
    // 传入的参数
    String params();
    // 调用类中的方法
    String method();
    // 返回的参数值
    String targetFiled();
}
