package com.xwder.newfeatures8.lambda.employee;

/**
 * @Author: xwder
 * @Date: 2019/9/16 22:16
 * @Description:
 */
public class FilterEmployeeByAge implements MyPredicate<Employee> {

    /**
     * 过滤年龄
     *
     * @param employee
     * @return
     */
    @Override
    public boolean filterCondition(Employee employee) {
        return employee.getAge() > 20;
    }
}
