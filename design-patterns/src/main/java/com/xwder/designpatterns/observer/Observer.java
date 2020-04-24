package com.xwder.designpatterns.observer;

/**
 * 2、抽象观察者Observer
 * 观察者一般是一个接口，每一个实现该接口的实现类都是具体观察者。
 * <p>
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public interface Observer {

    //更新
    void update();

}
