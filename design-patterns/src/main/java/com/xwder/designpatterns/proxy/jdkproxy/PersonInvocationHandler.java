package com.xwder.designpatterns.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class PersonInvocationHandler implements InvocationHandler {
    Object target;

    public PersonInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] arg2) throws Throwable {
        System.out.println("修改个人信息前记录日志");
        method.invoke(target);
        System.out.println("修改个人信息后记录日志");
        return null;
    }

}
