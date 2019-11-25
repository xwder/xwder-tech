package com.xwder.designpatterns.singleton.test;

import com.xwder.designpatterns.singleton.hungry.Singleton2;

/**
 * @author xwder
 * @date 2019-10-08 23:43:21
 */
public class TestSingleton2 {

    public static void main(String[] args) {
        Singleton2 s = Singleton2.INSTANCE;
        System.out.println(s);
    }

}