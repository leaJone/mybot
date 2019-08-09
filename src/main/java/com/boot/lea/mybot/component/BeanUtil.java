package com.boot.lea.mybot.component;


import com.boot.lea.mybot.annotation.NeedSetValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LiJing
 * @ClassName: BeanUtil
 * @Description: 在不实用关联查询时，如果更优雅的实现两个表的查询并组合在一起返回。
 * @date 2019/8/9 15:31
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (this.applicationContext == null) {
            this.applicationContext = applicationContext;
        }
    }

    public void setValue(Collection col) throws Exception {
        Class<?> clazz = col.iterator().next().getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            NeedSetValue sv = field.getAnnotation(NeedSetValue.class);
            if (sv == null) {
                continue;
            }
            field.setAccessible(true);
            Object bean = this.applicationContext.getBean(sv.beanClass());
            Method method = sv.beanClass().getMethod(sv.method(), clazz.getDeclaredField(sv.params()).getType());
            Field paramField = clazz.getDeclaredField(sv.params());
            paramField.setAccessible(true);
            Field targetField = null;

            boolean needInnerField = !StringUtils.isEmpty(sv.targetFiled());
            //可以提到方法外结合 缓存快速设置 这里只是简单的举例  可接入redis guava等缓存
            Map<String, Object> cache = new HashMap<>(10);

            String keyPrefix = sv.beanClass() + "-" + sv.method() + "-" + sv.targetFiled();
            for (Object obj : col) {
                Object paramValue = paramField.get(obj);
                if (paramValue == null) {
                    continue;
                }
                Object value = null;
                // 先从缓存中拿
                String key = keyPrefix + paramValue;
                if (cache.containsKey(key)) {
                    value = cache.get(key);
                } else {
                    value = method.invoke(bean, paramValue);
                    if (needInnerField && value != null) {
                        if (targetField == null) {
                            targetField = value.getClass().getDeclaredField(sv.targetFiled());
                            targetField.setAccessible(true);
                        }
                        value = targetField.get(value);
                    }
                    cache.put(key, value);
                }
                field.set(obj, value);
            }
        }
    }
}

