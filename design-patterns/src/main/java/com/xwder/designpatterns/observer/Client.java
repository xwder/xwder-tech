package com.xwder.designpatterns.observer;

/**
 * 5、Client客户端
 * 首先创建一个被观察者，然后定义一个观察者，将该被观察者添加到该观察者的观察者数组中，进行测试。
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class Client {

    public static void main(String[] args) {
        //创建一个主题
        ConcreteSubject subject = new ConcreteSubject();
        //定义一个观察者
        Observer observer = new ConcreteObserver();
        //观察
        subject.addObserver(observer);
        //开始活动
        subject.doSomething();
    }

}