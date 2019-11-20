package com.cskaoyan.mall.aopAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)   //运行时生效
@Target(ElementType.METHOD)   // 在方法上使用该注解
public @interface Order {
}
