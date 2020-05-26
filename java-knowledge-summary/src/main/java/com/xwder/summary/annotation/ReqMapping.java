package com.xwder.summary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 新建自定注解 @ReqMapping
 * Description:
 * Created by gaowei on 2018/1/3.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReqMapping {

    ReqMethod[] method() default {};

    String[] val() default "";
}