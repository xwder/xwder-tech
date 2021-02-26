package com.xwder.example.jsr.annotion;

import cn.hutool.core.util.StrUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解
 *
 * @author xwder
 * @date 2021年2月26日
 */
public class StartWithValidator implements ConstraintValidator<StartWithValidation, String> {
    private String start;

    @Override
    public void initialize(StartWithValidation constraintAnnotation) {
        start = constraintAnnotation.start();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (StrUtil.isNotEmpty(value)) {
            return value.startsWith(start);
        }
        return false;
    }
}