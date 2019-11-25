package com.xwder.commom.autoincrement;

import org.junit.Test;

/**
 * 自增
 *
 * @Author: xwder
 * @Date: 2019/10/10 23:17
 * @Description:
 */
public class AutoIncrement {
    public static void main(String[] args) {

    }

    /**
     * i=4
     * j=1
     * k=11
     */
    @Test
    public  void autoIncrement1Test(){
        int i = 1;
        i = i++;
        int j = i++;
        int k = i + ++i * i++;
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
