package com.xwder.example.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 获取spring容器上下文、容器内对象工具类
 *
 * @author xwder
 * @date 2021年2月26日
 */
@Component
public class WebSpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // applicationContext = applicationContext; 直接这样写 WebSpringBeanUtils.applicationContext null
        WebSpringBeanUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, @Nullable Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    public static String[] getBeanForType(Class<?> requiredType) {
        return applicationContext.getBeanNamesForType(requiredType);
    }

}