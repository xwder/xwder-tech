package com.xwder.newfeatures8.lambda.employee;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: xwder
 * @Date: 2019/9/16 22:02
 * @Description:
 */
public class EmployeeLambda {

    private List<Employee> employees = Arrays.asList(
            new Employee("张三", 18, 5000.00),
            new Employee("李四", 19, 6000.00),
            new Employee("王五", 20, 7000.00),
            new Employee("赵六", 21, 8000.00),
            new Employee("郑七", 21, 9000.00)
    );

    @Test
    public void practice() {
        // 年龄大于20
        List<Employee> employeeList = this.employees.stream().filter((employee -> employee.getAge() > 20)).collect(Collectors.toList());
        // 姓名集合
        List<String> nameList = employees.stream().map(employee -> employee.getName()).collect(Collectors.toList());
        // 遍历打印
        employees.forEach(employee -> System.out.println(employee));
        employees.forEach(System.out::println);
    }

    /**
     * 过滤员工年龄
     *
     * @param employees
     * @return
     */
    public List<Employee> filterAge(List<Employee> employees) {

        List<Employee> employeeList = employees.stream().filter((employee) -> employee.getAge() > 20).collect(Collectors.toList());

        List newEmploys = new ArrayList();
        for (Employee employee : employees) {
            if (employee.getAge() > 20) {
                newEmploys.add(employee);
            }
        }
        return newEmploys;
    }


    /**
     * 过滤员工工资
     *
     * @param employees
     * @return
     */
    public List<Employee> filterSalary(List<Employee> employees) {

        List<Employee> employeeList = employees.stream().filter(employee -> employee.getSalary() > 7000.00).collect(Collectors.toList());

        List newEmploys = new ArrayList();
        for (Employee employee : employees) {
            if (employee.getSalary() > 7000.00) {
                newEmploys.add(employee);
            }
        }
        return newEmploys;
    }

    /**
     * 过滤优化方式一: 策略模式
     *
     * @param list
     * @param myPredicate
     * @return
     */
    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> myPredicate) {
        List newEmploys = new ArrayList();
        for (Employee employee : employees) {
            if (myPredicate.filterCondition(employee)) {
                newEmploys.add(employee);
            }
        }
        return newEmploys;
    }

    @Test
    public void testFilterEmployee() {

        List<Employee> list = filterEmployee(employees, new FilterEmployeeByAge());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        System.out.println("-----------------------------------------");
        List<Employee> list2 = filterEmployee(employees, new FilterEmployeeBySalary());
        for (Employee employee : list2) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式二：匿名内部类
     */
    @Test
    public void filterEmployeeAnonymousInnerClass() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean filterCondition(Employee employee) {
                return employee.getSalary() > 7000.00;
            }
        });
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    /**
     * 优化方式三： lambda
     */
    @Test
    public void filterEmployeeWithLambda() {
        List<Employee> list = filterEmployee(employees, (e) -> e.getSalary() > 7000.00);
        list.forEach(System.out::println);
    }

    /**
     * 优化方式四： stream
     */
    @Test
    public void filterEmployeeWithStream() {
        employees.stream()
                .filter((e) -> e.getSalary() > 7000.00)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------");

        employees.stream()
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
