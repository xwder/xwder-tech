package com.xwder.newfeatures8.stream.eg;

import com.xwder.newfeatures8.stream.eg.model.Scholarship;
import com.xwder.newfeatures8.stream.eg.model.Student;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 综合测试
 * 参考 https://www.zifangsky.cn/1298.html
 *
 * @author zifangsky
 * @date 2018/8/5
 * @since 1.0.0
 */
public class Demo6 {
    private List<Scholarship> scholarships = null;

    @Before
    public void init() {
        Student stu1 = new Student("张三", 1, "广州");
        Student stu2 = new Student("李四", 1, "广州");
        Student stu3 = new Student("王五", 1, "杭州");
        Student stu4 = new Student("张伟", 1, "重庆");
        Student stu5 = new Student("王芳", 2, "重庆");
        Student stu6 = new Student("李娜", 2, "成都");
        Student stu7 = new Student("刑静", 2, "成都");
        Student stu8 = new Student("刘强", 1, "成都");

        scholarships = Arrays.asList(new Scholarship(stu1, 2016, 4000)
                , new Scholarship(stu2, 2016, 4200)
                , new Scholarship(stu3, 2016, 3500)
                , new Scholarship(stu4, 2016, 3600)
                , new Scholarship(stu2, 2017, 4400)
                , new Scholarship(stu3, 2017, 3000)
                , new Scholarship(stu5, 2017, 3800)
                , new Scholarship(stu6, 2017, 2000)
                , new Scholarship(stu3, 2018, 6000)
                , new Scholarship(stu5, 2018, 7000)
                , new Scholarship(stu7, 2018, 5500)
                , new Scholarship(stu8, 2018, 1000)
        );
    }

    /**
     * 找出2017年的奖学金获得情况，并按奖金金额排序
     * 输出如下：
     * Scholarship{student=Student{name='李娜', sex=2, city='成都'}, year=2017, money=2000}
     * Scholarship{student=Student{name='王五', sex=1, city='杭州'}, year=2017, money=3000}
     * Scholarship{student=Student{name='王芳', sex=2, city='重庆'}, year=2017, money=3800}
     * Scholarship{student=Student{name='李四', sex=1, city='广州'}, year=2017, money=4400}
     */
    @Test
    public void test1() {
        List<Scholarship> list = scholarships.stream()
                .filter(scholarship -> scholarship.getYear() == 2017) //筛选2017年的奖学金获得情况
                .sorted(Comparator.comparingInt(Scholarship::getMoney)) //按奖金金额排序
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    /**
     * 找出学生都来至于哪些城市
     * 输出如下：
     * 广州
     * 杭州
     * 重庆
     * 成都
     */
    @Test
    public void test2() {
        List<String> list = scholarships.stream()
                .map(scholarship -> scholarship.getStudent().getCity()) //提取学生信息中的城市信息
                .distinct() //去重
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    /**
     * 找出来至成都的学生并按学生姓名排序
     * 输出如下：
     * Student{name='刑静', sex=2, city='成都'}
     * Student{name='刘强', sex=1, city='成都'}
     * Student{name='李娜', sex=2, city='成都'}
     */
    @Test
    public void test3() {
        List<Student> list = scholarships.stream()
                .map(Scholarship::getStudent) //提取学生信息
                .filter(student -> "成都".equals(student.getCity())) //过滤来至成都的学生
                .sorted(Comparator.comparing(Student::getName)) //按学生姓名排序
                .distinct() //去重
                .collect(Collectors.toList());

        list.forEach(System.out::println);
    }

    /**
     * 获取所有学生姓名构成的字符串
     * 输出如下：
     * 张三, 李四, 王五, 张伟, 王芳, 李娜, 刑静, 刘强
     */
    @Test
    public void test4() {
        String stuName = scholarships.stream()
                .map(scholarship -> scholarship.getStudent().getName()) //提取学生信息中的学生姓名
                .distinct() //去重
                .collect(Collectors.joining(", ")); //拼接字符串

        System.out.println(stuName);
        ;
    }

    /**
     * 找出奖金金额最小的奖金获得情况
     * 输出如下：
     * Scholarship{student=Student{name='刘强', sex=1, city='成都'}, year=2018, money=1000}
     */
    @Test
    public void test5() {
        Optional<Scholarship> minScholarship = scholarships.stream()
//                .reduce((s1, s2) -> s1.getMoney() < s2.getMoney() ? s1 : s2) //方法一
                .min(Comparator.comparingInt(Scholarship::getMoney));

        minScholarship.ifPresent(System.out::println);
    }

    /**
     * 找出奖金金额最多的奖金金额
     * 输出如下：
     * 7000
     */
    @Test
    public void test6() {
        Optional<Integer> maxMoney = scholarships.stream()
//                .max(Comparator.comparingInt(Scholarship::getMoney)).map(Scholarship::getMoney) //方法一
                .map(Scholarship::getMoney).reduce(Integer::max); //方法二

        maxMoney.ifPresent(System.out::println);
    }

    /**
     * 计算2018年的奖金总额
     * 输出如下：
     * 19500
     */
    @Test
    public void test7() {
        Integer sumMoney = scholarships.stream()
                .filter(scholarship -> scholarship.getYear() == 2018)
//                .collect(Collectors.summingInt(Scholarship::getMoney)) //方法一
//                .collect(Collectors.reducing(0, Scholarship::getMoney, Integer::sum)) //方法二
//                .map(Scholarship::getMoney).reduce(Integer::sum).get() //方法三
                .mapToInt(Scholarship::getMoney).sum() //方法四
                ;

        System.out.println(sumMoney);
    }

    /**
     * 按年份分组，并统计每年的奖金总额
     * 输出如下：
     * 2016 --> 15300
     * 2017 --> 13200
     * 2018 --> 19500
     */
    @Test
    public void test8() {
        Map<Integer, Integer> stuSumMap = scholarships.stream()
                .collect(Collectors.groupingBy(Scholarship::getYear, Collectors.summingInt(Scholarship::getMoney)));

        stuSumMap.forEach((key, value) -> {
            System.out.println(key + " --> " + value);
        });
    }

    /**
     * 按年份分组，并获取每年获得奖金最高的学生信息
     * 输出如下：
     * 2016 --> Student{name='李四', sex=1, city='广州'}
     * 2017 --> Student{name='李四', sex=1, city='广州'}
     * 2018 --> Student{name='王芳', sex=2, city='重庆'}
     */
    @Test
    public void test9() {
        Map<Integer, Student> stuSumMap = scholarships.stream()
                .collect(Collectors.groupingBy(Scholarship::getYear //按年分组
                        , Collectors.collectingAndThen( //去除包装的Optional
                                Collectors.maxBy(Comparator.comparingInt(Scholarship::getMoney)) //按奖学金排序取最大值
                                , o -> o.isPresent() ? o.get().getStudent() : null
                        )));

        stuSumMap.forEach((key, value) -> {
            System.out.println(key + " --> " + value);
        });
    }

    /**
     * 按年份分组，再按照性别分组获取相应的学生信息
     * 输出如下：
     * 2016 --> {男生=[Student{name='张三', sex=1, city='广州'}, Student{name='李四', sex=1, city='广州'}, Student{name='王五', sex=1, city='杭州'}, Student{name='张伟', sex=1, city='重庆'}]}
     * 2017 --> {男生=[Student{name='李四', sex=1, city='广州'}, Student{name='王五', sex=1, city='杭州'}], 女生=[Student{name='王芳', sex=2, city='重庆'}, Student{name='李娜', sex=2, city='成都'}]}
     * 2018 --> {男生=[Student{name='王五', sex=1, city='杭州'}, Student{name='刘强', sex=1, city='成都'}], 女生=[Student{name='王芳', sex=2, city='重庆'}, Student{name='刑静', sex=2, city='成都'}]}
     */
    @Test
    public void test10() {
        Map<Integer, Map<String, List<Student>>> stuSexMap = scholarships.stream()
                .collect(Collectors.groupingBy(Scholarship::getYear, //外层按年分组
                        Collectors.groupingBy(s -> { //内层按学生性别分组
                                    int sex = s.getStudent().getSex();
                                    return sex == 1 ? "男生" : "女生"; //自定义返回内容
                                }, Collectors.mapping(Scholarship::getStudent, Collectors.toList()) //收集学生信息
                        )
                ));

        stuSexMap.forEach((key, value) -> {
            System.out.println(key + " --> " + value);
        });
    }

}
