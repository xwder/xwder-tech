package com.xwder.designpatterns.observer.demo;

/**
 * 2. 观察者
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public abstract class Observer {

    protected String name;
    protected Subject subject;

    public Observer(String name, Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public abstract void update();

}