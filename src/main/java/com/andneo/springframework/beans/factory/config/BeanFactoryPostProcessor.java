package com.andneo.springframework.beans.factory.config;

import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:30
 **/
public interface BeanFactoryPostProcessor {

    // 在所有的beanDefinition加载完成后，实例化bean对象之前，提供修改BeanDefinition属性的机制
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
