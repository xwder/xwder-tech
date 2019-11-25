package com.xwder.designpatterns.singleton.test;

import com.xwder.designpatterns.singleton.lazy.Singleton4;

import java.util.concurrent.*;

/**
 * 测试懒汉模式多线程不安全模式
 *
 * @author xwder
 * @date 2019-10-08 23:43:21
 */
public class TestSingleton4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
/*		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();
	
		System.out.println(s1 == s2);
		System.out.println(s1);
		System.out.println(s2);*/

        Callable<Singleton4> c = new Callable<Singleton4>() {

            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getInstance();
            }
        };

        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Singleton4> f1 = es.submit(c);
        Future<Singleton4> f2 = es.submit(c);

        Singleton4 s1 = f1.get();
        Singleton4 s2 = f2.get();

        System.out.println(s1 == s2);
        System.out.println(s1);
        System.out.println(s2);

        es.shutdown();

    }

}