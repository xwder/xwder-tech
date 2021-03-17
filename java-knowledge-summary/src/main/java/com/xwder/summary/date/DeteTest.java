package com.xwder.summary.date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.junit.Test;

/**
 * @author xwder
 * @date 2021/3/4 11:26
 */
public class DeteTest {

    @Test
    public void timeTest() {
        DateTime dateTime = DateUtil.parseDate("2021-03-04 11:00:00");
        System.out.println(System.currentTimeMillis() - dateTime.getTime());
        System.out.println((System.currentTimeMillis() - dateTime.getTime()) / 1000);
    }
}
