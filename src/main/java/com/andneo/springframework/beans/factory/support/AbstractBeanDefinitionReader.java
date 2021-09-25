package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.core.io.ResourceLoader;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-24 23:16
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    // 注册
    private BeanDefinitionRegistry registry;
    // 加载
    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
