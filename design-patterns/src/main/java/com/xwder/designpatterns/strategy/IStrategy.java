package com.xwder.designpatterns.strategy;

/**
 * 策略模式接口
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public interface IStrategy {
    /**
     * 定义的抽象算法方法 来约束具体的算法实现方法
     */
    void algorithmMethod();
}
