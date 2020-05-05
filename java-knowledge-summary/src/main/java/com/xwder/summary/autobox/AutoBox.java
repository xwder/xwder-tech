package com.xwder.summary.autobox;

import org.junit.Test;

/**
 * 自动装箱拆箱
 *
 * @author wande
 * @version 1.0
 * @date 2020/05/05
 */
public class AutoBox {

    public static void main(String[] args) {

    }

    @Test
    public void testAutoBox1() {
        Integer iObj = 3;
        System.out.println(iObj + 12);

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2);

        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);

        Integer i3 = Integer.valueOf(213);
        Integer i4 = Integer.valueOf(213);
        System.out.println(i3 == i4);

        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i5 == i6);

    }
}
