package com.boot.lea.mybot.redis;

/**
 * @Title: RedisDistributedLock.java
 * @Package com.boot.lea.mybot.redis
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/10/17 11:39
 * @version v.3.0
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * @author LiJing
 * @ClassName: RedisDistributedLock
 * @Description: redis分布式锁
 * @date 2019/10/17 11:39
 */

@Component
public class RedisDistributedLock {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    private final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);

    /**
     * @param key    参数是key字符串和过期时间(单位是毫秒)，
     * @param expire
     * @return
     */
    public boolean setLock(String key, long expire) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                String uuid = UUID.randomUUID().toString();
                //EX = seconds; PX = milliseconds 单位不同而已，所以用的 PX 也没毛病
                return commands.set(key, uuid, "NX", "PX", expire);
            };
            String result = redisTemplate.execute(callback);

            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            logger.error("set redis occured an exception", e);
        }
        return false;
    }

    public String get(String key) {
        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.get(key);
            };
            String result = redisTemplate.execute(callback);
            return result;
        } catch (Exception e) {
            logger.error("get redis occured an exception", e);
        }
        return "";
    }

    /**
     * @param key       解锁的参数是key和key对应的redis里存的值
     * @param requestId 解锁的key对应的redis里的value
     * @return
     */
    public boolean unLock(String key, String requestId) {
        // 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = new ArrayList<>();
            keys.add(key);
            List<String> args = new ArrayList<>();
            args.add(requestId);
            // 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            // spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            };
            Long result = redisTemplate.execute(callback);
            return result != null && result > 0;
        } catch (Exception e) {
            logger.error("release lock occured an exception", e);
        } finally {
//            清除掉ThreadLocal中的数据，避免内存溢出
//            lockFlag.remove();
        }
        return false;
    }
}
