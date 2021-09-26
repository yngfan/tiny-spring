package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.core.io.Resource;
import com.andneo.springframework.core.io.ResourceLoader;

/**
 * bean定义读取接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
