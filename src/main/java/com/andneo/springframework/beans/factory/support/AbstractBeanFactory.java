package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.beans.factory.BeanFactory;
import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.andneo.springframework.beans.factory.config.DefaultSingletonBeanRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义模板方法
 * @program: tiny-spring
 * @description: AbstractBeanFactory
 * @author: fanfan.yang
 * @create: 2021-09-23 16:54
 **/
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            return (T) bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
