package com.xwder.designpatterns.strategy;

/**
 * 策略上下文<p>
 * 参考博客 <a href="https://blog.csdn.net/tugangkai/article/details/88074288">深入解析策略模式</a>
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/08
 */
public class StrategyContext {

    //持有一个策略实现的引用
    private IStrategy strategy;

    //使用构造器注入具体的策略类
    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void contextMethod() {
        //调用策略实现的方法
        strategy.algorithmMethod();
    }
}