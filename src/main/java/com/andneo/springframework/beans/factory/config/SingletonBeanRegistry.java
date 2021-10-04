package com.andneo.springframework.beans.factory.config;

/**
 * @program: tiny-spring
 * @description: SingletonBeanRegistry
 * @author: fanfan.yang
 * @create: 2021-09-23 16:35
 **/
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
