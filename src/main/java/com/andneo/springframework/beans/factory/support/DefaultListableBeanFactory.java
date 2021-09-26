package com.andneo.springframework.beans.factory.support;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 核心类实现
 * @program: tiny-spring
 * @description: DefaultListableBeanFactory
 * @author: fanfan.yang
 * @create: 2021-09-23 17:47
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

    public boolean containsBeanDefinition(String beanName) throws BeansException {
        return beanDefinitionMap.containsKey(beanName);
    }

    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeanOfType(Class<T> type) {
        Map<String, T> result = new HashMap();
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }
}
