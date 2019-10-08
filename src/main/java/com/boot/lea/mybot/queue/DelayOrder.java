package com.boot.lea.mybot.queue;

public interface DelayOrder<T> {


    /**
     * 添加延迟对象到延时队列
     *
     * @param itemDelayed 延迟对象
     * @return boolean
     */
    boolean addToOrderDelayQueue(ItemDelayed<T> itemDelayed);

    /**
     * 根据对象添加到指定延时队列
     *
     * @param data 数据对象
     * @return boolean
     */
    boolean addToDelayQueue(T data);

    /**
     * 移除指定的延迟对象从延时队列中
     *
     * @param data
     */
    void removeToOrderDelayQueue(T data);
}
