package com.xwder.example.springboot.config.lableimport;

import lombok.extern.slf4j.Slf4j;

/**
 * 注解 @Import 第一种方式：直接导入普通的 Java 类  <p>
 * 普通JavaBean 用于演示 使用 @Import  注解加入不同JavaBean到spring容器中  <p>
 *
 * @author xwder
 * @date 2021/2/22 14:35
 */
@Slf4j
public class CommonJavaBean {

    public CommonJavaBean() {
        log.info(" I'm commonJavaBean construct method ");
    }

    public void sayHi() {
        log.info(" I'm commonJavaBean sayHi ~~~  ");
    }
}
