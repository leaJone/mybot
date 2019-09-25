package com.boot.lea.mybot.cache;

/**
 * @Title: CaffeineCache.java
 * @Package com.boot.lea.mybot.practice
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/9/2 15:36
 * @version v.3.0
 */


import com.github.benmanes.caffeine.cache.CacheLoader;
import org.springframework.context.annotation.Bean;

/**
 * @author LiJing
 * @ClassName: CaffeineCache
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/9/2 15:36
 */
public class CaffeineCache {


    @Bean
    public CacheLoader<Object, Object> cacheLoader() {
        CacheLoader<Object, Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                return null;
            }

            // 重写这个方法将oldValue值返回回去，进而刷新缓存
            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                return oldValue;
            }
        };

        return cacheLoader;
    }

}
