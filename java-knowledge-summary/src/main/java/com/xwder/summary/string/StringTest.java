package com.xwder.summary.string;

import org.junit.Test;

/**
 * @author xwder
 * @date 2021/2/25 16:41
 */
public class StringTest {

    @Test
    public void stringSplitTest(){
        String str = "苏CJR736_1";
        System.out.println(str.split("_")[0]);
    }
}
