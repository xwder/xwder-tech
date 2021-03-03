package com.xwder.example.contorller.test;

import com.xwder.example.common.result.CommonResult;
import com.xwder.example.config.configurationProperties.CloudResourceConfig;
import com.xwder.example.config.configurationProperties.SystemInfoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * config test controller
 *
 * @author xwder
 * @date 2021/2/22 17:26
 */
@RestController
@RequestMapping(value = {"/test/config"})
public class TestConfigController {

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
}
