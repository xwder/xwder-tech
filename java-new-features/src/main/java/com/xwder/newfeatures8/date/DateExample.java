package com.xwder.newfeatures8.date;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Java8 date
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/07
 */
public class DateExample {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        System.out.println( "今天的⽇期:"+today);

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println( "year:"+year);
        System.out.println( "month:"+month);
        System.out.println( "day:"+day);

        //LocalDateTime localDateTime = new LocalDateTime();
        LocalDate date = LocalDate.of( 2018,2,6);
        System.out.println( "⾃定义⽇期:"+date);
    }
}
