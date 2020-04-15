package com.xwder.designpatterns.decorator;

/**
 * 装饰器模式 具体构件角色（对应狗）
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/15
 */
public class ConcreteComponent implements Component {

    @Override
    public void function() {
        System.out.println("基本功能：呼吸+觅食+睡觉");
    }

}