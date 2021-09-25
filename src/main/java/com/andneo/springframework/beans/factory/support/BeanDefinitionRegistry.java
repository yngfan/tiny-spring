package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.config.BeanDefinition;

/**
 * @program: tiny-spring
 * @description: BeanDefinitionRegistry
 * @author: fanfan.yang
 * @create: 2021-09-23 15:59
 **/
public interface BeanDefinitionRegistry {
    // 向注册表中注册BeanDefinition
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
    // 使用beanName查找BeanDefinition
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    // 是否有指定名称的BeanDefinition
    boolean containsBeanDefinition(String beanName) throws BeansException;
    // 返回注册表中所有bean名称
    String[] getBeanDefinitionNames();
}
