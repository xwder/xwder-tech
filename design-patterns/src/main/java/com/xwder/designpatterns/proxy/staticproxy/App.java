package com.xwder.designpatterns.proxy.staticproxy;

/**
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class App {
    public static void main(String[] args) {
        PersonDao p = new PersonDao();
        PersonDaoProxy pProxy = new PersonDaoProxy(p);
        pProxy.update();
    }
}
