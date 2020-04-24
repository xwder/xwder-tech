package com.xwder.designpatterns.observer.demo;

/**
 * 4. 具体观察者
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/24
 */
public class NBAObserver extends Observer {

    public NBAObserver(String name, Subject subject) {
        super(name, subject);
    }

    @Override
    public void update() {
        System.out.println(subject.getAction() + "\n" + name + "关闭NBA，继续工作");
    }

}