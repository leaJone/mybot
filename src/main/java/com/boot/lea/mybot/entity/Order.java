package com.boot.lea.mybot.entity;


import com.boot.lea.mybot.annotation.NeedSetValue;
import com.boot.lea.mybot.mapper.UserMapper;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author LiJing
 * @ClassName: Order
 * @Description: 订单实体
 * @date 2019/8/9 15:33
 */
@Data
public class Order extends BaseEntity{

    /** 订单id*/
    private Long id;

    /** 订单编号*/
    private String orderNo;

    /** 订单名称*/
    private String name;

    /** 客户id*/
    private Integer customerId;

    /** 产品id*/
    private Long goodsId;

    /** 商品价格*/
    private BigDecimal goodsPrice;

    /** 订单实付金额*/
    private BigDecimal orderAmount;

    /** 订单状态:10:待支付(初始下单)  20:待使用(已支付) 30:已使用(已支付) 100:已取消(未支付)*/
    private Integer status;

    /** 支付类型:微信1,支付宝2*/
    private Integer payType;

    /** 支付状态:0未支付 1 已支付*/
    private Integer payStatus;

    /**支付时间*/
    private Date payDate;

    /**超时时间*/
    private Date overDate;

    /**
     * 注解标记 AOP 查询和设置 @NeedSetValue
     */
    @NeedSetValue(beanClass = UserMapper.class, params = "customerId", method = "selectById", targetFiled = "username")
    private String customerName;

}
