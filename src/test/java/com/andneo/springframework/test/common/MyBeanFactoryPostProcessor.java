package com.andneo.springframework.test.common;

import com.andneo.springframework.beans.PropertyValue;
import com.andneo.springframework.beans.PropertyValues;
import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-26 23:56
 **/
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "美团"));
    }
}
