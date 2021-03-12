package com.xwder.example.config.controller;

import com.xwder.example.common.result.CommonResult;
import com.xwder.example.config.conditional.ConditionBean;
import com.xwder.example.config.configurationProperties.CloudResourceConfig;
import com.xwder.example.config.configurationProperties.SystemInfoConfig;
import com.xwder.example.util.WebSpringBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xwder
 * @date 2021/3/3 15:18
 */
@RequestMapping(value = "/test/config")
@RestController
public class ConfigTestController {

    @Autowired
    private SystemInfoConfig systemInfoConfig;

    @Autowired
    private CloudResourceConfig cloudResourceConfig;

    @RequestMapping(value = "/systemInfoConfig")
    public Object testConfigurationProperties() {
        return CommonResult.success(systemInfoConfig);
    }

    @RequestMapping(value = "/cloudResourceConfig")
    public Object testCloudResourceConfig() {
        return CommonResult.success(cloudResourceConfig);
    }

    @RequestMapping("/conditionalOnProperty")
    public CommonResult testConditionalOnProperty(){
        ConditionBean conditionalOnPropertyConfigBean = (ConditionBean) WebSpringBeanUtils.getBean("ConditionalOnPropertyConfigBean");
        return CommonResult.success(conditionalOnPropertyConfigBean);
    }
}
