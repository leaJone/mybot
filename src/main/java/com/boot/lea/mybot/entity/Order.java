package com.boot.lea.mybot.entity;


import com.boot.lea.mybot.annotation.NeedSetValue;
import com.boot.lea.mybot.mapper.UserMapper;
import lombok.Data;

/**
 * @author LiJing
 * @ClassName: Order
 * @Description: 订单实体
 * @date 2019/8/9 15:33
 */
@Data
public class Order {

    private Integer id;

    private String name;

    private Integer customerId;

    /**
     * 注解标记 AOP 查询和设置 @NeedSetValue
     */
    @NeedSetValue(beanClass = UserMapper.class, params = "customerId", method = "selectById", targetFiled = "username")
    private String customerName;

}
