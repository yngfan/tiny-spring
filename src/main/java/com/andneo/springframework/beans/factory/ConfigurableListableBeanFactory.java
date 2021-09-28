package com.andneo.springframework.beans.factory;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:33
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
