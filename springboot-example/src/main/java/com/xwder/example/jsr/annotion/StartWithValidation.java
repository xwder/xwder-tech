package com.xwder.example.jsr.annotion;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * 自定义注解校验
 *
 * @author xwder
 * @date 2021年2月26日
 */
@Documented
@Constraint(validatedBy = StartWithValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface StartWithValidation {

    String message() default "地址必须以 开头";

    String start() default "_";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        StartWithValidation[] value();
    }
}