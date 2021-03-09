package com.xwder.example.cache.ehcache.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;


/**
 * @author xwder
 */
@Component
@Slf4j
@CacheConfig(cacheNames = {"CACHE_DEMO"})
public class EhcacheUtils {

    /**
     * 注解@Cacheable可以标记在一个方法上，也可以标记在一个类上。 <p>
     * 当标记在一个方法上时表示该方法是支持缓存的，当标记在一个类上时则表示该类所有的方法都是支持缓存的。
     *
     * 对于一个支持缓存的方法，Spring会在其被调用后将其返回值缓存起来，以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果，而不需要再次执行该方法。
     *
     * 一个支持缓存的方法在对象内部被调用时是不会触发缓存功能的。
     * @param keyName
     * @return
     */
    @Cacheable(key = "#keyName")
    public Object getCache(String keyName) {
        log.info("[ EHCACHE ] 正在缓存 ==> Key: {}", keyName);
        return null;
    }

    @CachePut(key = "#keyName")
    public <T> T updateCache(String keyName, T t) {
        log.info("[ EHCACHE ] 正在保存 ==> Key: {},Value: {}", keyName, t);
        return t;
    }

    @CacheEvict(key = "#keyName")
    public void delCache(String keyName) {
        log.info("[ EHCACHE ] 正在删除 ==> Key:{}", keyName);
    }

}

