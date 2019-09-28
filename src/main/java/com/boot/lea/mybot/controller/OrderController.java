package com.boot.lea.mybot.controller;


import com.boot.lea.mybot.dto.RspDTO;
import com.boot.lea.mybot.entity.Order;
import com.boot.lea.mybot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LiJing
 * @ClassName: OrderController
 * @Description: 订单控制器
 * @date 2019/8/9 15:53
 */
@RestController
@RequestMapping("order")
public class OrderController extends AbstractController {


    @Autowired
    OrderService orderService;


    @GetMapping("/getAllOrder")
    public RspDTO getAllOrder() {
        List<Order> allOrder = orderService.getAllOrder();
        return new RspDTO<List<Order>>().success(allOrder);
    }

}
