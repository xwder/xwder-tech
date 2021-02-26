package com.xwder.example.config.lableimport;

import lombok.extern.slf4j.Slf4j;

/**
 * 普通JavaBean  <p>
 * 用于测试 @Import 注解配合自定义的 ImportSelector 使用 装配Java类到spring容器  <p>
 *
 * @author xwder
 * @date 2021/2/22 15:00
 */
@Slf4j
public class ImportSelectorClass {

    public ImportSelectorClass() {
        log.info(" I'm ImportSelectorClass construct method ");
    }

    public void sayHi() {
        log.info(" I'm ImportSelectorClass sayHi ~~~  ");
    }
}
