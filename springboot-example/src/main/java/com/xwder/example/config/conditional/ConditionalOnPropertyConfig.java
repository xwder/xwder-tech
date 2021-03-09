package com.xwder.example.config.conditional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试 @ConditionalOnProperty <p>
 * 当matchIfMissing = false时，必须要有对应的property <p>
 * 当matchIfMissing = true时，即使没有该parentName.sonName属性也会加载正常 <p>
 * 更多参考: https://blog.csdn.net/u010002184/article/details/79353696 <p>
 *
 * @author xwder
 * @date 2021/3/3 15:04
 */
//@ConditionalOnProperty(value = "system.testConditionalOnProperty", havingValue = "true")
@ConditionalOnProperty(prefix = "system", value = "testConditionalOnProperty",
        havingValue = "true", matchIfMissing = false)
@Configuration
public class ConditionalOnPropertyConfig {

    @Bean(name = "ConditionalOnPropertyConfigBean")
    public ConditionBean conditionBean() {
        return new ConditionBean();
    }
}
