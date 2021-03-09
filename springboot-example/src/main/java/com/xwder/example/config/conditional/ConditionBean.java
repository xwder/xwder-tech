package com.xwder.example.config.conditional;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * 验证 @Conditional 【Spring提供】
 * 创建普通 Java 类 ConditionBean，该类主要用来验证 Bean 是否成功加载。 <p>
 *
 * @author xwder
 * @date 2021/2/22 15:24
 */
@ToString
@Slf4j
public class ConditionBean implements Serializable {

    private static final long serialVersionUID = -7970005053591210082L;

    @Getter
    @Setter
    private String name = "I'm ConditionBean";

    public ConditionBean() {
        log.info(" I'm ConditionBean construct method ");
    }

    public void sayHi() {
        log.info(" I'm ConditionBean sayHi ~~~  ");
    }
}


