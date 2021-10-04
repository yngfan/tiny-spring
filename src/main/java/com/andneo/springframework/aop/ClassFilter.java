package com.andneo.springframework.aop;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 11:33
 **/
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
