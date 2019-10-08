package com.boot.lea.mybot.enums;

/**
 * @Title: OwnOrderStatusEnum.java
 * @Package com.cn.alasga.order.enums
 * @Description:
 * @author Lijing
 * @date 2019-09-12 14:08:37
 * @version 4.6.2
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Lijing
 * @ClassName: OwnOrderStatusEnum
 * @Description: ownOrder常规状态
 * @date 2019-09-12 14:08:37
 */

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    /**
     * 排序规则
     * 待支付-待使用-已使用-已取消
     * 订单状态: 10:待支付(初始下单)  20:待使用(已支付) 30:已使用(已支付) 100:已取消(未支付)
     */
    CREATE(10, "下单"),
    UNUSED(20, "待使用"),
    USED(30, "已使用"),
    CANCEL(100, "已取消"),
    ;
    private Integer code;
    private String msg;

}
