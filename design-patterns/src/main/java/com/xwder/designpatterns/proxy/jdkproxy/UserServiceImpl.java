package com.xwder.designpatterns.proxy.jdkproxy;

/**
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class UserServiceImpl implements UserService {
    @Override
    public void save() {
        System.out.println("userService save....");
    }
}
