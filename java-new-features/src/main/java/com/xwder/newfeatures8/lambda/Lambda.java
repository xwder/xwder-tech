package com.xwder.newfeatures8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * lambda 测试
 *
 * @Author: xwder
 * @Date: 2019/9/16 21:51
 * @Description:
 */
public class Lambda {

    /**
     * 匿名内部类方式
     */
    @Test
    public void compareInteger() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeMap = new TreeSet(comparator);
    }

    /**
     * Lambda 表达式
     */
    @Test
    public void lambdaMethod() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeMap = new TreeSet(comparator);

    }


}
