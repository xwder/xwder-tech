package com.xwder.example.springboot.contorller.test;

import com.xwder.example.springboot.config.configurationProperties.CloudResourceConfig;
import com.xwder.example.springboot.config.configurationProperties.SystemInfoConfig;
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
public class TestController {

    @Autowired
    private SystemInfoConfig systemInfoConfig;

    @Autowired
    private CloudResourceConfig cloudResourceConfig;

    @RequestMapping(value = "/systemInfoConfig")
    public Object testConfigurationProperties() {
        return systemInfoConfig;
    }

    @RequestMapping(value = "/cloudResourceConfig")
    public Object testCloudResourceConfig() {
        return cloudResourceConfig;
    }
}
