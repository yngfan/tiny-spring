package com.andneo.springframework.beans.factory.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: tiny-spring
 * @description: DefaultSingletonBeanRegistry
 * @author: fanfan.yang
 * @create: 2021-09-23 16:47
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<String, Object>();

    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
