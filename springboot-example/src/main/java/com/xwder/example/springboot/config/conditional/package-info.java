/**
 * @Conditional 注解使用
 */
package com.xwder.example.springboot.config.conditional;

/**
 * 类似的注解还有：
 * 注解：@ConditionalOnBean            容器中存在指定Bean,则生效
 * 注解：@ConditionalOnMissingBean     容器中不存在指定Bean,,则生效
 * 注解：@ConditionalOnClass           系统中有指定类,则生效
 * 注解：@ConditionalOnMissingClass    系统中没有指定的类,则生效
 * 注解：@ConditionalOnProperty        系统中指定的属性是否有指定的值
 * 注解：@ConditionalOnWebApplication  当前是web环境,则生效
 */