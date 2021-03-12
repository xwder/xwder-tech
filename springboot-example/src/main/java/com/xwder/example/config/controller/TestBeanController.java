package com.xwder.example.config.controller;

import com.xwder.example.common.result.CommonResult;
import com.xwder.example.config.configurationProperties.CloudResourceConfig;
import com.xwder.example.config.configurationProperties.SystemInfoConfig;
import com.xwder.example.util.WebSpringBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * context bean test controller
 *
 * @author xwder
 * @date 2021/2/26 15:26
 */
@Slf4j
@RestController
@RequestMapping(value = {"/test/bean"})
public class TestBeanController {

    @Autowired
    private SystemInfoConfig systemInfoConfig;

    @RequestMapping(value = "/systemInfoConfig")
    public Object testConfigurationProperties() {
        SystemInfoConfig systemInfoConfig2 = (SystemInfoConfig) WebSpringBeanUtils.getBean("systemInfoConfig");
        log.info("容器bean比较,使用Autowired获取的bean和根据上下文和名称获取的bean比较：{}", systemInfoConfig == systemInfoConfig2);
        return CommonResult.success(systemInfoConfig2);
    }
}
