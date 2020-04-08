package com.xwder.designpatterns.proxy.staticproxy;

/**
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class PersonDao implements IPersonDao{

    @Override
    public void update() {
        System.out.println("修改个人信息");
    }
}