package com.xwder.designpatterns.singleton.test;

import com.xwder.designpatterns.singleton.hungry.Singleton1;

/**
 * 测试恶汉模式
 *
 * @author xwder
 * @date 2019-10-08 23:43:21
 */
public class TestSingleton1 {

    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        System.out.println(s);
    }

}