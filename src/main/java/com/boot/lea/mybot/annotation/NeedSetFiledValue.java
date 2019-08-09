package com.boot.lea.mybot.annotation;

/**
 * @Title: NeedSetFiledValue.java
 * @Package com.boot.lea.mybot.annotation
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:32
 * @version v.3.0
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: NeedSetFiledValue
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:32 
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedSetFiledValue {
}

