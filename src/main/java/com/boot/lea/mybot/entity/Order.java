package com.boot.lea.mybot.entity;

/**
 * @Title: Order.java
 * @Package com.boot.lea.mybot.entity
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:33
 * @version v.3.0
 */

import com.boot.lea.mybot.annotation.NeedSetValue;
import com.boot.lea.mybot.mapper.UserMapper;

/**
 * @ClassName: Order
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:33 
 *
 */
public class Order {

    private Integer id;

    private String name;

    private Integer customerId;

    // 使用注解定义在参数上
    @NeedSetValue(beanClass = UserMapper.class, params = "customerId", method = "getUserById",targetFiled = "name")
    private String customerName;

}
