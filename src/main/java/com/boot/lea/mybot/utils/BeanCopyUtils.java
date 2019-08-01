package com.boot.lea.mybot.utils;

/**
 * @Title: BeanCopyUtils.java
 * @Package com.cn.alasga.common.core.util.copy
 * @Description: TODO(用一句话描述该文件做什么)
 * @author LiJing
 * @date 2019/4/17 9:15
 * @version v.3.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author LiJing
 * @ClassName: BeanCopyUtils
 * @Description: 基于BeanCopier的属性拷贝
 * @date 2019/4/17 9:15
 * @implNote <p>
 * 1.当源类和目标类的属性名称、类型都相同，拷贝结果棒棒哒。
 * 2.当源对象和目标对象的属性名称相同、类型不同,那么名称相同而类型不同的属性不会被拷贝。
 * 另外注意，原始类型（int，short，char）和 他们的包装类型，在这里都被当成了不同类型。因此不会被拷贝。
 * 3.源类或目标类的setter比getter少，拷贝没问题，此时setter多余，但是不会报错。
 * 4.源类和目标类有相同的属性（两者的getter都存在），但是目标类的setter不存在，
 * 此时会抛出NullPointerException(这个在高版本bug已经修改测试通过)
 * <p>
 * * 测试发现:
 * * 场景           耗时(百万次调用)           原理
 * * get/set          22ms              setter/getter调用
 * * BeanCopier       22ms              基于cglib,修改字节码
 * * PropertyUtils    922ms                反射
 * * BeanUtils       12983ms               反射
 * * ^^ <p>
 * * 凡是和反射相关的操作，基本都是低性能的。凡是和字节码相关的操作，基本都是高性能的。
 * <p/>
 */
@Slf4j
public class BeanCopyUtils {

    /**
     * 创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();

    /**
     * 该方法没有自定义Converter,简单进行常规属性拷贝
     *
     * @param srcObj  源对象
     * @param destObj 目标对象
     */
    public static void copy(Object srcObj, Object destObj) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
