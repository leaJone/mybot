package com.boot.lea.mybot.service.impl;

import com.boot.lea.mybot.annotation.NeedSetFiledValue;
import com.boot.lea.mybot.entity.Order;
import com.boot.lea.mybot.mapper.OrderMapper;
import com.boot.lea.mybot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiJing
 * @ClassName: OrderServiceImpl
 * @Description: 订单Service服务类
 * @date 2019/8/9 15:33
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    @NeedSetFiledValue
    public List<Order> getAllOrder() {
//        List<Order> orderList = orderMapper.getAllOrder();
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order();
        order1.setId(8888L);
        order1.setName("订单:" + order1.getId());
        order1.setCustomerId(206);
        orderList.add(order1);

        Order order2 = new Order();
        order2.setId(9999L);
        order2.setName("订单:" + order2.getId());
        order2.setCustomerId(204);
        orderList.add(order2);

        return orderList;
    }

    @Override
    public List<Order> selectFutureOverTimeOrder() {
        return orderMapper.selectFutureOverTimeOrder();
    }

    @Override
    public void updateCloseOverTimeOrder(Long orderId) {
        orderMapper.updateCloseOverTimeOrder(orderId);
    }
}
