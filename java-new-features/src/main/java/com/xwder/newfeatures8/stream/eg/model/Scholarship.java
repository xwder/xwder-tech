package com.xwder.newfeatures8.stream.eg.model;

/**
 * 奖学金类
 *
 * @author zifangsky
 * @date 2018/8/5
 * @since 1.0.0
 */
public class Scholarship {
    /**
     * 学生信息
     */
    private final Student student;
    /**
     * 获奖年份
     */
    private final int year;
    /**
     * 奖金金额
     */
    private final int money;

    public Scholarship(Student student, int year, int money) {
        this.student = student;
        this.year = year;
        this.money = money;
    }

    public Student getStudent() {
        return student;
    }

    public int getYear() {
        return year;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "student=" + student +
                ", year=" + year +
                ", money=" + money +
                '}';
    }
}