package com.xwder.example.config.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 注解 @Conditional 注释可以实现只有在特定条件满足时才启用一些配置。  <p>
 * 创建配置类，可以看到该配置的 @Conditional 传了我们刚才创建的 Condition 实现类进去，用作条件判断。  <p>
 *
 * 类似的注解还有：
 * 注解：@ConditionalOnBean            容器中存在指定Bean,则生效
 * 注解：@ConditionalOnMissingBean     容器中不存在指定Bean,,则生效
 * 注解：@ConditionalOnClass           系统中有指定类,则生效
 * 注解：@ConditionalOnMissingClass    系统中没有指定的类,则生效
 * 注解：@ConditionalOnProperty        系统中指定的属性是否有指定的值
 * 注解：@ConditionalOnWebApplication  当前是web环境,则生效
 *
 *
 * @author xwder
 * @date 2021/2/22 15:32
 */
@Configuration
@Conditional(MyCondition.class)
public class ConditionConfig {

    @Bean
    public ConditionBean conditionBean(){
        return new ConditionBean();
    }
}
