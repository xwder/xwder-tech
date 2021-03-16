package com.xwder.example.cache.ehcache.service;

import com.xwder.example.cache.ehcache.dto.EhcacheDto;
import com.xwder.example.util.WebSpringBeanUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * SpEl表达式参考： https://blog.csdn.net/likun557/article/details/107853045 <p>
 * Cacheable 使用详解: https://www.cnblogs.com/coding-one/p/12401630.html <p>
 * 持久化参考： https://www.cnblogs.com/dw039/p/14121788.html <p>
 *
 * @author xwder
 * @date 2021/3/9 9:02
 */
@Service
public class EhcacheDemoService {

    /**
     * 测试 @ Cacheable注解
     * Cacheable 提供两个参数来指定缓存名：value、cacheNames，二者选其一即可。
     *
     * @param ehcacheDto 缓存查询对象
     * @return 根据缓存查询对象构建key查询缓存，查询到直接返回查询内容，没有则直接缓存并返回
     */
    @Cacheable(value = {"CACHE_DEMO"}, key = "''.concat(#ehcacheDto.ID).concat('_').concat(#ehcacheDto.name)")
    public EhcacheDto cacheable(EhcacheDto ehcacheDto) {
        return EhcacheDto.builder().ID(ehcacheDto.getID())
                .name(ehcacheDto.getName())
                .age(new Random().nextInt(100))
                .build();
    }


    /**
     * 测试 @ Cacheable注解
     * Cacheable 提供两个参数来指定缓存名：value、cacheNames，二者选其一即可。
     *
     * @param ehcacheDto 缓存对象
     * @return 缓存对象
     */
    @CachePut(value = {"CACHE_DEMO"}, key = "''.concat(#ehcacheDto.ID).concat('_').concat(#ehcacheDto.name)")
    public EhcacheDto cachePut(EhcacheDto ehcacheDto) {
        return ehcacheDto;
    }

    /**
     * 自定义缓存 使用ehCacheCacheManager
     *
     * @param ehcacheDto 缓存查询对象
     * @return 返回EhcacheDto
     */
    public EhcacheDto customCacheManagerQuery(EhcacheDto ehcacheDto) {
        EhCacheCacheManager ehCacheCacheManager = WebSpringBeanUtils.getBean(EhCacheCacheManager.class);
        //获取CacheManager类  可以直接注入 CacheManager cacheManager;
        CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
        //获取Cache
        String key = ehcacheDto.getID() + "_" + ehcacheDto.getName();
        assert cacheManager != null;
        Cache cache = cacheManager.getCache("CACHE_DEMO");
        Element element = cache.get(key);
        /*cache.put(new Element("Hello", "123"));*/
        Object objectValue = element.getObjectValue();
        return objectValue == null ? null : (EhcacheDto) objectValue;

    }

}
