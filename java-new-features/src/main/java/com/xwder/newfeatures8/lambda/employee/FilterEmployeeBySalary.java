package com.xwder.newfeatures8.lambda.employee;

/**
 * @Author: xwder
 * @Date: 2019/9/16 22:17
 * @Description:
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {

    /**
     * 过滤工资
     *
     * @param employee
     * @return
     */
    @Override
    public boolean filterCondition(Employee employee) {
        return employee.getSalary() > 7000.00;
    }
}
