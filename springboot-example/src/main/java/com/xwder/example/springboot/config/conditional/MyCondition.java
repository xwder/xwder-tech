package com.xwder.example.springboot.config.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 注解 @Conditional 【Spring提供】 测试  <p>
 * 第2步：创建 Condition 实现类，@Conditional 注解只有一个 Condition 类型的参数，  <p>
 * Condition 是一个接口，该接口只有一个返回布尔值的 matches() 方法，该方法返回 true 则条件成立，配置类生效。  <p>
 * 反之，则不生效。在该例子中我们直接返回 true。 <p>
 *
 * @author xwder
 * @date 2021/2/22 15:29
 */
public class MyCondition implements Condition {

    /**
     * 返回布尔值的 matches() 方法，该方法返回 true 则条件成立，配置类生效。
     * 反之，则不生效。在该例子中我们直接返回 true。
     *
     * @param conditionContext
     * @param annotatedTypeMetadata
     * @return
     */
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // 这里我们直接返回true
         return true;
//        return false;
    }
}
