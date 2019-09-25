package com.boot.lea.mybot.mapper;

/**
 * @Title: OrderMapper.java
 * @Package com.boot.lea.mybot.mapper
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/8/9 15:34
 * @version v.3.0
 */

import com.boot.lea.mybot.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ClassName: OrderMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/8/9 15:34 
 *
 */
@Mapper
public interface OrderMapper {

    /** 查询订单
     * @return
     */
    List<Order> getAllOrder();

    /**
     * @return
     */
    List<Order> selectFutureOverTimeOrder();

    /**
     * @param orderId
     */
    void updateCloseOverTimeOrder(Long orderId);
}
