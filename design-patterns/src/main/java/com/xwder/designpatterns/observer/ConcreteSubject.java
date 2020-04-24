package com.xwder.designpatterns.observer;

/**
 * 3、具体主题
 * 继承Subject类，在这里实现具体业务，在具体项目中，该类会有很多变种。
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class ConcreteSubject extends Subject {

    //具体业务
    public void doSomething() {
        //. ..
        super.notifyObserver();
    }
}
