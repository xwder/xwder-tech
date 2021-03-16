package com.xwder.example.listener;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author: xwder
 * @date: 2021/3/15
 **/
@Slf4j
@Component
public class ApplicationCloseEventListener implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    private CacheManager cacheManager;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.info("CacheManager shutdown ~~~");
        cacheManager.shutdown();
    }

}
