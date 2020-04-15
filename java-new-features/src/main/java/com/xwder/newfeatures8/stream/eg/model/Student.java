package com.xwder.newfeatures8.stream.eg.model;

/**
 * 学生类
 *
 * @author zifangsky
 * @date 2018/8/5
 * @since 1.0.0
 */
public class Student {
    /**
     * 姓名
     */
    private final String name;
    /**
     * 性别：1为男性；2为女性
     */
    private final int sex;
    /**
     * 就读城市
     */
    private final String city;

    public Student(String name, int sex, String city) {
        this.name = name;
        this.sex = sex;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", city='" + city + '\'' +
                '}';
    }
}
