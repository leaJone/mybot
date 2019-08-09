package com.boot.lea.mybot.service.impl;

/**
 * @Title: OrderServiceImpl.java
 * @Package com.boot.lea.mybot.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:33
 * @version v.3.0
 */

import com.boot.lea.mybot.annotation.NeedSetFiledValue;
import com.boot.lea.mybot.entity.Order;
import com.boot.lea.mybot.mapper.OrderMapper;
import com.boot.lea.mybot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @ClassName: OrderServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:33 
 *
 */
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    // 使用注解定在需要的方法上
    @Override
    @NeedSetFiledValue
    public List<Order> getAllOrder() {
        List<Order> orderList = orderMapper.getAllOrder();
        return orderList;
    }
}
