package com.xwder.newfeatures8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Java8 LocalDate、LocalTime、LocalDateTime
 *
 * @author wande
 * @version 1.0
 * @date 2020/04/07
 */
public class DateExample {

    public static void main(String[] args) {

        /**----------- LocalDate -----------**/
        // LocalDate 它只有日期，不包含时间
        LocalDate today = LocalDate.now();
        System.out.println("今天的⽇期:" + today);

        // 获取年月日
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println("year:" + year);
        System.out.println("month:" + month);
        System.out.println("day:" + day);

        // 构造指定日期
        LocalDate date = LocalDate.of(2018, 2, 6);
        System.out.println("⾃定义⽇期:" + date);

        // 比较两个日期是否相等
        LocalDate now = LocalDate.now();
        LocalDate otherDate = LocalDate.of(2018, 9, 24);
        System.out.println("日期是否相等=" + now.equals(otherDate));
        // 日期之前
        date.isAfter(otherDate);
        // 日期之后
        date.isBefore(otherDate);

        // 日期增量
        LocalDate addDate = LocalDate.now();
        LocalDate newDate = addDate.plus(1, ChronoUnit.WEEKS);
        System.out.println("newDate=" + newDate);

        // 日期格式化
        // 解析日期
        String dateText = "20180924";
        LocalDate formatDdate = LocalDate.parse(dateText, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("格式化之后的日期=" + formatDdate);

        // 格式化日期
        dateText = formatDdate.format(DateTimeFormatter.ISO_DATE);
        System.out.println("dateText=" + dateText);

        /**---------- LocalTime ------------**/

        //LocalTime 获取当前时间 它只有时间，不包含日期
        LocalTime time = LocalTime.now();
        System.out.println("当前时间=" + time);

        // 时间增量
        LocalTime addTime = LocalTime.now();
        LocalTime newTime = addTime.plusHours(2);
        System.out.println("newTime=" + newTime);

        /**---------- LocalDateTime ------------**/

        // 时区
        // 上海时间
        ZoneId shanghaiZoneId = ZoneId.of("Asia/Shanghai");
        ZonedDateTime shanghaiZonedDateTime = ZonedDateTime.now(shanghaiZoneId);

        // 东京时间
        ZoneId tokyoZoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoZonedDateTime = ZonedDateTime.now(tokyoZoneId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("上海时间: " + shanghaiZonedDateTime.format(formatter));
        System.out.println("东京时间: " + tokyoZonedDateTime.format(formatter));

        // 日期时间转字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String nowText = localDateTime.format(formatter);
        System.out.println("nowText=" + nowText);

        // 字符串转日期时间
        String datetimeText = "1999-12-31 23:59:59";
        LocalDateTime datetime = LocalDateTime.parse(datetimeText, formatter);
        System.out.println(datetime);

        /**----------Date 和 LocalDateTime 转换 ------------**/

        Date todayDate = new Date();
        LocalDateTime ldt = todayDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        System.out.println(ldt);

        // LocalDateTime 转 Date
        localDateTime = LocalDateTime.now();
        Date convertDdate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(convertDdate);
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate
     * @return
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDateTime
     * @return
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date 转 LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
