package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @program: tiny-spring
 * @description: 定义实例化策略接口
 * @author: fanfan.yang
 * @create: 2021-09-23 23:15
 **/
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;

}
