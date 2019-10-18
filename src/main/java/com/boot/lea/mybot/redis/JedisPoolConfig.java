package com.boot.lea.mybot.redis;

/**
 * @Title: JedisPoolConfig.java
 * @Package com.boot.lea.mybot.redis
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/17 18:02
 * @version v.3.0
 */

/**
 * @ClassName: JedisPoolConfig
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiJing
 * @date 2019/10/17 18:02 
 *
 */

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *初始化Jedis连接池
 */
public class JedisPoolConfig {
    private static JedisPool pool = null;

    /**
     * 静态代码块 构建redis连接池
     */
    static {
        if (pool == null) {
            redis.clients.jedis.JedisPoolConfig config = new redis.clients.jedis.JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            config.setMaxTotal(50);
            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(10);
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；单位毫秒
            //小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(1000 * 100);
            //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
            config.setTestOnReturn(true);
            //connectionTimeout 连接超时（默认2000ms）
            //soTimeout 响应超时（默认2000ms）
            pool = new JedisPool(config, "192.168.1.1", 6379, 10000);
        }
    }

    /**
     * 方法描述 获取Jedis实例
     *
     * @return
     */
    public static Jedis getJedis() {
        return pool.getResource();
    }

    /**
     * 方法描述 释放jedis连接资源
     *
     * @param jedis
     */
    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
