package com.xwder.designpatterns.singleton.hungry;

/**
 * 饿汉式：
 * 在类初始化时直接创建实例对象，不管你是否需要这个对象都会创建
 * <p>
 * （1）构造器私有化
 * （2）自行创建，并且用静态变量保存
 * （3）向外提供这个实例
 * （4）强调这是一个单例，我们可以用final修改
 *
 * @author xwder
 * @date 2019-10-08 23:38:57
 */
public class Singleton1 {
    public static final Singleton1 INSTANCE = new Singleton1();

    private Singleton1() {

    }
}
