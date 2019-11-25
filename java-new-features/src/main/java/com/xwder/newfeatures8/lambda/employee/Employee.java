package com.xwder.newfeatures8.lambda.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: xwder
 * @Date: 2019/9/16 21:42
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    private String name;
    private Integer age;
    private Double salary;

}
