package com.xwder.example.cache.ehcache.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * @author xwder
 */
@Component
@Slf4j
public class CacheUtil {

    @Resource
    private EhcacheUtils ehcacheUtils;

    public Object cache(String keyName, Supplier supplier) {
        Object cache = ehcacheUtils.getCache(keyName);
        log.info("[ CacheUtil ] CACHE: {}", cache);
        return cache == null ? ehcacheUtils.updateCache(keyName, supplier.get()) : cache;
    }
}
