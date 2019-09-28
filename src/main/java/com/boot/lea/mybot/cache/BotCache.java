package com.boot.lea.mybot.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * @author LiJing
 * @ClassName: BotCache
 * @Description: Guava Cache
 * @date 2019/8/9 16:48
 */
public class BotCache {



    /*
     * 需要引用缓存就
     * 以 @Autowired Resource
     *    private Cache<String, Integer> cache;
     * 的形式注入
     */


    @Bean(name = "myCache")
    public Cache<String, Integer> getCache() {
        // 缓存有效期为2秒
        return CacheBuilder.newBuilder()
                .expireAfterWrite(2L, TimeUnit.SECONDS)
                .build();
    }

    @Bean(name = "botCache")
    public Cache<String, String> getBotCache() {
        // 缓存有效期为5秒
        return CacheBuilder.newBuilder()
                .expireAfterWrite(5L, TimeUnit.SECONDS)
                .initialCapacity(20)
                .build();
    }

}
