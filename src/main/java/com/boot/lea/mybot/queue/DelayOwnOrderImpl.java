package com.boot.lea.mybot.queue;

/**
 * @Title: DelayOrderComponent.java
 * @Package com.cn.alasga.order.queue
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/9/16 15:52
 * @version v.3.0
 */

import com.boot.lea.mybot.entity.Order;
import com.boot.lea.mybot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
 * @author LiJing
 * @ClassName: DelayOrderComponent
 * @Description: 延时队列 订单组件
 * @date 2019/9/16 15:52
 */
@Slf4j
@Lazy(false)
@Component
public class DelayOwnOrderImpl implements DelayOrder<Order> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExecutorService delayOrderExecutor;

    private final static DelayQueue<ItemDelayed<Order>> DELAY_QUEUE = new DelayQueue<>();

    /**
     * 初始化时加载数据库中需处理超时的订单
     * 系统启动:扫描数据库中未支付(要在更新时:加上已支付就不用更新了),未过期的的订单
     */
    @PostConstruct
    public void init() {
        log.info("系统启动:扫描数据库中未支付,未过期的的订单");
        List<Order> orderList = orderService.selectFutureOverTimeOrder();
        for (Order order : orderList) {
            ItemDelayed<Order> orderDelayed = new ItemDelayed<>(order.getId(), order.getCreateDate().getTime());
            this.addToOrderDelayQueue(orderDelayed);
        }
        log.info("系统启动:扫描数据库中未支付的订单,总共扫描了" + orderList.size() + "个订单,推入检查队列,准备到期检查...");

        /*启动一个线程，去取延迟订单*/
        delayOrderExecutor.execute(() -> {
            log.info("启动处理的订单线程:" + Thread.currentThread().getName());
            ItemDelayed<Order> orderDelayed;
            while (true) {
                try {
                    orderDelayed = DELAY_QUEUE.take();
                    //处理超时订单
                    orderService.updateCloseOverTimeOrder(orderDelayed.getDataId());
                } catch (Exception e) {
                    log.error("执行自营超时订单的_延迟队列_异常:" + e);
                }
            }
        });
    }

    /**
     * 加入延迟消息队列
     **/
    @Override
    public boolean addToOrderDelayQueue(ItemDelayed<Order> orderDelayed) {
        return DELAY_QUEUE.add(orderDelayed);
    }

    /**
     * 加入延迟消息队列
     **/
    @Override
    public boolean addToDelayQueue(Order order) {
        ItemDelayed<Order> orderDelayed = new ItemDelayed<>(order.getId(), order.getCreateDate().getTime());
        this.addToOrderDelayQueue(orderDelayed);
        return DELAY_QUEUE.add(orderDelayed);
    }

    /**
     * 从延迟队列中移除 主动取消就主动从队列中取出
     **/
    @Override
    public void removeToOrderDelayQueue(Order order) {
        if (order == null) {
            return;
        }
        for (Iterator<ItemDelayed<Order>> iterator = DELAY_QUEUE.iterator(); iterator.hasNext(); ) {
            ItemDelayed<Order> queue = iterator.next();
            if (queue.getDataId().equals(order.getId())) {
                DELAY_QUEUE.remove(queue);
            }
        }
    }

}
