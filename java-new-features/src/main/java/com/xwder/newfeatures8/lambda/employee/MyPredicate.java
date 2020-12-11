package com.xwder.newfeatures8.lambda.employee;

/**
 * 策略模式 策略
 *
 * @Author: xwder
 * @Date: 2019/9/16 22:13
 * @Description:
 */
@FunctionalInterface
public interface MyPredicate<T> {

    /**
     * 过滤条件
     *
     * @param t
     * @return
     */
    boolean filterCondition(T t);

}
