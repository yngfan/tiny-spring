package com.andneo.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 18:36
 **/
public interface Advisor {

    Advice getAdvice();

}
