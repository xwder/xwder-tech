package com.xwder.designpatterns.observer;

/**
 * 4、具体观察者
 * 实现Observer接口。
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class ConcreteObserver implements Observer {

    @Override
    public void update() {
        System.out.println("收到消息，进行处理");
    }

}