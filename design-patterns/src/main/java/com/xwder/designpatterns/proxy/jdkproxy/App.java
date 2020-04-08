package com.xwder.designpatterns.proxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * JDKProxy
 * JDK代理，非常简单地实现了动态代理（首先是实现对应的InvocationHandler；
 * * 然后，以接口来为被调用目标构建代理对象，代理对象简介运行调用目标，并提供额外逻辑插入）
 * <p>
 * 缺点：它是只能以接口为中心的。优点：依赖JDK，更稳定可靠，跟着JDK升级，代码简单。
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class App {
    public static void main(String[] args) {
        // 目标对象
        UserService userService = new UserServiceImpl();
        // com.xwder.designpatterns.proxy.jdkproxy.UserServiceImpl
        System.out.println(userService.getClass());

        PersonInvocationHandler handler = new PersonInvocationHandler(userService);

        UserService proxyUserService = (UserService) Proxy.newProxyInstance(UserServiceImpl.class.getClassLoader(),
                UserServiceImpl.class.getInterfaces(), handler);

        proxyUserService.save();
    }
}
