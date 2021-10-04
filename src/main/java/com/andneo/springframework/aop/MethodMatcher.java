package com.andneo.springframework.aop;

import java.lang.reflect.Method;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 11:34
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);

}
