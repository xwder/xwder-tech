package com.xwder.designpatterns.observer.demo;

/**
 * 1、通知者接口
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
interface Subject {

    //增加
    void attach(Observer observer);

    //删除
    void detach(Observer observer);

    //通知
    void notifyObservers();

    //状态
    void setAction(String action);

    String getAction();

}