package com.xwder.example.springboot.config.configurationProperties;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统信息配置类
 * <p>
 * 读取配置文件信息方式一：使用 {@link Component @Component} 和 {@link ConfigurationProperties @ConfigurationProperties} 注解  <p>
 * 读取配置文件信息方式二：使用 {@link Value @Value} 注解 ${key} 方式从环境变量中获取值  <p>
 *
 * <p>
 * 注解@Value注解有Spring提供，并非是Spring Boot中的，该注解存在于spring-beans.jar中。
 * 使用@Value有三个缺点：
 * <p>配置属性不统一，没有结构。
 * <p>注入麻烦每个属性都要写配置名，和属性名。（只要有重复的工作，就应该重构）
 * <p>配置零散在项目中各处
 *
 * @author xwder
 * @date 2021/2/22 13:55
 */
@Data
@ToString
@Component
@ConfigurationProperties(prefix = "system")
public class SystemInfoConfig {

   @Value("system-version")
    private String version;

    private String systemName;

    private String sessionTokenName;

    private List<Groups> groups;

    private Map<String,Integer> limitSizeMap;

    private String copyright;

    /**
     * @Constructor（构造方法）->@Autowired（依赖注入）->@PostConstruct（注解的方法）
     */
    @PostConstruct
    public void init(){
        copyright = systemName+"-"+ DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN);
    }

    /**
     * 不一定要用静态内部类，这里使用是为了方便看（可以单独创建一个类）
     */
    @ToString
    @Setter
    @Getter
    static class Groups{
        private String name;
        private String description;
    }
}
