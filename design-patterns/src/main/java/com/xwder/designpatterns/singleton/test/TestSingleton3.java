package com.xwder.designpatterns.singleton.test;

import com.xwder.designpatterns.singleton.hungry.Singleton3;

/**
 * @author xwder
 * @date 2019-10-08 23:43:21
 */
public class TestSingleton3 {

    public static void main(String[] args) {
        Singleton3 s = Singleton3.INSTANCE;
        System.out.println(s);
    }

}