package com.cskaoyan.mall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.cskaoyan.mall")
@EnableAspectJAutoProxy                              //使用注解类的切面编程
public class SpringConfig {
}
