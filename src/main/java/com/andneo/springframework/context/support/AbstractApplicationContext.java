package com.andneo.springframework.context.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.context.ConfigurableApplicationContext;
import com.andneo.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:47
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public void refresh() {
        // 1、创建beanFactory，加载BeanDefinition
        refreshBeanFactory();
        // 2、获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3、Bean实例化前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);
        // 4、Bean对象实例化之前，执行注册操作
        registerBeanPostProcessor(beanFactory);

        // 提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeanOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor: beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeanOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) {
        return getBeanFactory().getBeanOfType(type);
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }

}
