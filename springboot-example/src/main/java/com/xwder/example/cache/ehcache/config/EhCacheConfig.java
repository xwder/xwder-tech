package com.xwder.example.cache.ehcache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 缓存@Cacheable注解属性介绍: https://blog.csdn.net/lzb348110175/article/details/105349109
 * 开启缓存 @EnableCaching也可以标注在启动类上面 <p>
 * 在 @Cacheable 注解的使用中，共有 9 个属性供我们来使用，
 * 这 9 个属性分别是： value、 cacheNames、 key、 keyGenerator、 cacheManager、 cacheResolver、 condition、 unless、 sync <p>
 *
 * @author xwder
 * @date 2021年3月9日 09点10分
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    /**
     *
     * @return
     */
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName()+ Arrays.asList(params).toString();
            }
        };
    }

    /**
     * 支持 lambda 表达式编写
     */
    /*@Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return ( target,  method, params)-> method.getName()+ Arrays.asList(params).toString();
    }*/
}