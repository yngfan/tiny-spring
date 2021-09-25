package com.andneo.springframework.beans.factory;


import com.andneo.springframework.beans.BeansException;

/**
 * @program: tiny-spring
 * @description: BeanFactory
 * @author: fanfan.yang
 * @create: 2021-09-23 14:58
 **/
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;
    // 按照类型获取bean
    <T> T getBeansOfType(Class<T> requiredType) throws BeansException;


}
