package com.andneo.springframework.context.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.context.ApplicationEvent;
import com.andneo.springframework.context.ApplicationListener;
import com.andneo.springframework.context.ConfigurableApplicationContext;
import com.andneo.springframework.context.event.ApplicationEventMulticaster;
import com.andneo.springframework.context.event.ContextClosedEvent;
import com.andneo.springframework.context.event.ContextRefreshedEvent;
import com.andneo.springframework.context.event.SimpleApplicationEventMulticaster;
import com.andneo.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:47
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    public void refresh() {
        // 1、创建beanFactory，加载BeanDefinition
        refreshBeanFactory();

        // 2、获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3、添加ApplicationContextAwareProcessor，让继承ApplicationContextAware的Bean对象都能感知到所属的applicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4、Bean实例化前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessor(beanFactory);

        // 5、Bean对象实例化之前，执行注册操作
        registerBeanPostProcessor(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerListeners();

        // 提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();

        // 发布容器，刷新完事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor: beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor: beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
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
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }

}
