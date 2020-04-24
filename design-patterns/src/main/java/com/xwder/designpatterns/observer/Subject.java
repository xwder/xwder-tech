package com.xwder.designpatterns.observer;

import java.util.Vector;

/**
 * 1、主题Subject
 * 首先定义一个观察者数组，并实现增、删及通知操作。
 * 它的职责很简单，就是定义谁能观察，谁不能观察，用Vector是线程同步的，比较安全，
 * 也可以使用ArrayList，是线程异步的，但不安全
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class Subject {

    //观察者数组
    private Vector<Observer> oVector = new Vector<>();

    //增加一个观察者
    public void addObserver(Observer observer) {
        this.oVector.add(observer);
    }

    //删除一个观察者
    public void deleteObserver(Observer observer) {
        this.oVector.remove(observer);
    }

    //通知所有观察者
    public void notifyObserver() {
        for (Observer observer : this.oVector) {
            observer.update();
        }
    }
}
