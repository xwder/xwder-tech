package com.xwder.summary.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 数组 集合 排序
 *
 * @author xwder
 * @date 2021/3/3 11:26
 */
public class ListSortDemo {

    private List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 5000.00),
            new Employee("李四", 19, 6000.00),
            new Employee("王五", 20, 7000.00),
            new Employee("赵六", 21, 8000.00),
            new Employee("郑七", 21, 9000.00)
    );

    /**
     * list 排序
     */
    public void listSort() {
        // age 升序
        System.out.println("age 升序-----------");
        employeeList.sort(Comparator.comparing(Employee::getAge));
        employeeList.forEach(System.out::println);

        // age 降序
        System.out.println("age 降序-----------");
        employeeList.sort(Comparator.comparing(Employee::getAge).reversed());
        employeeList.forEach(System.out::println);
        //多条件升序
        System.out.println("多条件升序-----------");
        employeeList.sort(Comparator.comparing(Employee::getAge).thenComparing(Employee::getSalary));
        employeeList.forEach(System.out::println);
        System.out.println("-----------");
        //age降序,salary升序
        System.out.println("age降序,salary升序-----------");
        employeeList.sort(Comparator.comparing(Employee::getAge).reversed().thenComparing(Employee::getSalary));
        employeeList.forEach(System.out::println);
        // 自定义排序 返回正数，零，负数各代表大于，等于，小于
        employeeList.sort((o1, o2) -> o1.getAge() - o2.getAge());
        employeeList.sort(Comparator.comparingInt(Employee::getAge));
        // 自定义多条件排序
        Collections.sort(employeeList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int flag = o1.getAge() - o2.getAge();
                // 零，负数各代表大于，等于，小于
                if (flag == 0) {
                    if (o1.getSalary() > o2.getSalary()) {
                        flag = 1;
                    } else if (o1.getSalary().equals(o2.getSalary())) {
                        flag = 0;
                    } else {
                        flag = -1;
                    }
                }
                return 0;
            }
        });
    }

    /**
     * 数组排序
     */
    public void arraySort() {
        Employee[] employees = (Employee[]) employeeList.toArray();
        Arrays.stream(employees).sorted(Comparator.comparingInt(Employee::getAge));
        Arrays.sort(employees, (o1, o2) -> o1.getAge() - o2.getAge());
    }

    public static void main(String[] args) {
        ListSortDemo listSortDemo = new ListSortDemo();
        listSortDemo.listSort();
    }
}
