package com.xwder.example.cache.ehcache.controller;

import com.xwder.example.cache.ehcache.dto.EhcacheDto;
import com.xwder.example.cache.ehcache.service.EhcacheDemoService;
import com.xwder.example.cache.ehcache.util.EhcacheUtils;
import com.xwder.example.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwder
 * @date 2021/3/8 16:13
 */
@Slf4j
@Validated
@RestController()
@RequestMapping(value = "/cache/ehcache")
public class CacheController {

    @Autowired
    private EhcacheUtils ehcacheUtils;

    @Autowired
    private EhcacheDemoService ehcacheDemoService;

    /**
     * put
     *
     * @param ehcacheDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/put")
    public Object cachePut(@RequestBody @Validated EhcacheDto ehcacheDto) {
        String key = ehcacheDto.getID() + "_" + ehcacheDto.getName();
        EhcacheDto cacheObject = ehcacheUtils.updateCache(key, ehcacheDto);
        return CommonResult.success(cacheObject);
    }

    /**
     * get
     *
     * @param ehcacheDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/query")
    public Object cacheQuery(@RequestBody @Validated EhcacheDto ehcacheDto) {

        String key = ehcacheDto.getID() + "_" + ehcacheDto.getName();
        EhcacheDto cacheObject = (EhcacheDto) ehcacheUtils.getCache(key);
        if (cacheObject == null) {
            return CommonResult.failed("未查询到数据");
        }
        return CommonResult.success(cacheObject);
    }

    /**
     * Cacheable
     *
     * @param ehcacheDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/cacheable")
    public Object cacheable(@RequestBody @Validated EhcacheDto ehcacheDto) {
        EhcacheDto result = ehcacheDemoService.cacheable(ehcacheDto);
        return CommonResult.success(result);
    }

    /**
     * cachePut
     *
     * @param ehcacheDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/cacheput")
    public Object cachePut2(@RequestBody @Validated EhcacheDto ehcacheDto) {
        EhcacheDto cacheObject = ehcacheDemoService.cachePut(ehcacheDto);
        return CommonResult.success(cacheObject);
    }


    /**
     * customCacheManagerPut
     *
     * @param ehcacheDto
     * @return
     */
    @ResponseBody
    @RequestMapping("/customCacheManagerQuery")
    public Object customCacheManagerQuery(@RequestBody @Validated EhcacheDto ehcacheDto) {
        EhcacheDto result = ehcacheDemoService.customCacheManagerQuery(ehcacheDto);
        return CommonResult.success(result);
    }
}
