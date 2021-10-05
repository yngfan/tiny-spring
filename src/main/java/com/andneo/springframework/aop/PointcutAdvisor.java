package com.andneo.springframework.aop;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 18:38
 **/
public interface PointcutAdvisor extends Advisor {

    Pointcut getPointcut();

}
