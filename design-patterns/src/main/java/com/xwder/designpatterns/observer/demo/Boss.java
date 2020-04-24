package com.xwder.designpatterns.observer.demo;

import java.util.LinkedList;
import java.util.List;

/**
 * 3. 具体通知者 老板通知者
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class Boss implements Subject {

    //同事列表
    private List<Observer> observers = new LinkedList<>();
    private String action;

    //添加
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    //删除
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    //通知
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    //前台状态
    @Override
    public String getAction() {
        return action;
    }

    @Override
    public void setAction(String action) {
        this.action = action;
    }

}