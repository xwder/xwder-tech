package com.xwder.summary.math;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Java 数字计算
 *
 * @author xwder
 * @date 2021/2/18 17:17
 */
public class NumberCalculateTest {

    /**
     * 保留两位小数
     */
    @Test
    public void keepTwoNumbers() {
        int intNumber1 = 10;
        int intNumber2 = 3;

        double doubleNumber1 = 10.0d;
        double doubleNumber2 = 3.0d;

        // 方式一
        double towNumbers = new BigDecimal((float) intNumber1 / intNumber2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("keepTwoNumbers: " + towNumbers);
        towNumbers = new BigDecimal(doubleNumber1 / doubleNumber2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("keepTwoNumbers: " + towNumbers);

        // 方式二
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        //返回的是String类型
        String num = df.format((float) intNumber1 / intNumber2);
        System.out.println("keepTwoNumbers: " + num);
        num = df.format(doubleNumber1 / doubleNumber2);
        System.out.println("keepTwoNumbers: " + num);

    }
}
