package com.boot.lea.mybot.queue;

/**
 * @Title: ItemDelayed.java
 * @Package com.cn.alasga.order.queue
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/9/16 15:53
 * @version v.3.0
 */

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author LiJing
 * @ClassName: ItemDelayed
 * @Description: 数据延迟实现
 * @date 2019/9/16 15:53
 */

@Setter
@Getter
public class ItemDelayed<T> implements Delayed {

    /**默认延迟30分钟*/
    private final static long DELAY = 30 * 60 * 1000L;

    /**数据id*/
    private Long dataId;

    /**开始时间*/
    private long startTime;

    /**到期时间*/
    private long expire;

    /**创建时间*/
    private Date now;

    /**泛型data*/
    private T data;


    public ItemDelayed(Long dataId, long startTime, long secondsDelay) {
        super();
        this.dataId = dataId;
        this.startTime = startTime;
        this.expire = startTime + (secondsDelay * 1000);
        this.now = new Date();
    }

    public ItemDelayed(Long dataId, long startTime) {
        super();
        this.dataId = dataId;
        this.startTime = startTime;
        this.expire = startTime + DELAY;
        this.now = new Date();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}
