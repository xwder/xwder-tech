package com.xwder.summary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 新建自定义注解 @ReqValue
 * Description:
 *
 * @author xwder
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqValue {

    String value1() default "";

    String value2() default "";
}