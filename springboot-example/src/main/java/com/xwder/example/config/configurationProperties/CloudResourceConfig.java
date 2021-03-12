package com.xwder.example.config.configurationProperties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取指定 config.properties 文件内容
 *
 * @author xwder
 * @date 2021/2/22 17:17
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "cloud-resource")
@PropertySource(value = "classpath:config.properties")
public class CloudResourceConfig {
    private String baiduOcrKey;
    private String baiduOcrSecret;
}
