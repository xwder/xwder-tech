package com.xwder.designpatterns.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class PersonProxy {
    private Object target;
    private InvocationHandler invocationHandler;

    public PersonProxy(Object target, InvocationHandler invocationHandler) {
        this.target = target;
        this.invocationHandler = invocationHandler;
    }

    public Object getPersonProxy() {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), invocationHandler);
        return proxy;
    }

}
