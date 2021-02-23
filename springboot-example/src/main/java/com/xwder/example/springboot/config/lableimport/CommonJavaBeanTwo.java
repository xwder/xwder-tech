package com.xwder.example.springboot.config.lableimport;

import lombok.extern.slf4j.Slf4j;

/**
 * 注解 @Import 配合 ImportBeanDefinitionRegistrar 使用  <p>
 * 普通JavaBean 用于演示 使用 @Import  注解加入不同JavaBean到spring容器中 <p>
 *
 * @author xwder
 * @date 2021/2/22 14:35
 */
@Slf4j
public class CommonJavaBeanTwo {

    public CommonJavaBeanTwo() {
        log.info(" I'm CommonJavaBeanTwo construct method ");
    }

    public void sayHi() {
        log.info(" I'm CommonJavaBeanTwo sayHi ~~~  ");
    }
}
