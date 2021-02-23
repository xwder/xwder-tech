package com.xwder.example.springboot.config;

import com.xwder.example.springboot.config.conditional.ConditionBean;
import com.xwder.example.springboot.config.conditional.ConditionConfig;
import com.xwder.example.springboot.config.configurationProperties.CloudResourceConfig;
import com.xwder.example.springboot.config.configurationProperties.SystemInfoConfig;
import com.xwder.example.springboot.config.lableimport.CommonJavaBean;
import com.xwder.example.springboot.config.lableimport.CommonJavaBeanTwo;
import com.xwder.example.springboot.config.lableimport.ImportSelectorClass;
import com.xwder.example.springboot.config.lableimport.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author xwder
 * @date 2021/2/22 14:49
 */
@Slf4j
public class ConfigTest {

    /**
     * 读取配置文件信息测试 @ConfigurationProperties 注解
     */
    @Test
    void ConfigurationPropertiesTest() {
        AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
        context.register(SystemInfoConfig.class);
        context.register(CommonJavaBean.class);
        context.register(CloudResourceConfig.class);
        context.refresh();

        SystemInfoConfig systemInfoConfig = context.getBean(SystemInfoConfig.class);
        log.info(systemInfoConfig.toString());

        CloudResourceConfig cloudResourceConfig = context.getBean(CloudResourceConfig.class);
        log.info(cloudResourceConfig.toString());

        CommonJavaBean commonJavaBean = context.getBean(CommonJavaBean.class);
        commonJavaBean.sayHi();
    }

    /**
     * 测试 @Import  注解
     */
    @Test
    void importTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        CommonJavaBean commonJavaBean = context.getBean(CommonJavaBean.class);
        commonJavaBean.sayHi();

        ImportSelectorClass importSelectorClass = context.getBean(ImportSelectorClass.class);
        importSelectorClass.sayHi();

        CommonJavaBeanTwo commonJavaBeanTwo = context.getBean(CommonJavaBeanTwo.class);
        commonJavaBeanTwo.sayHi();
    }


    /**
     * 测试 @Conditional  注解
     */
    @Test
    void conditionalTest(){
        ApplicationContext context = new AnnotationConfigApplicationContext(ConditionConfig.class);
        ConditionBean conditionBean = context.getBean(ConditionBean.class);
        conditionBean.sayHi();
    }

}
