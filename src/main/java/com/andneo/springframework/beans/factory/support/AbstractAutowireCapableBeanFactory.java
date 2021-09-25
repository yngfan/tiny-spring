package com.andneo.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.PropertyValue;
import com.andneo.springframework.beans.PropertyValues;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiny-spring
 * @description: AbstractAutowireCapableBeanFactory
 * @author: fanfan.yang
 * @create: 2021-09-23 17:21
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 给bean填充属性。利用反射
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 放入容器
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Constructor constructor2Use = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor constructor: declaredConstructors) {
            if (args != null && args.length == constructor.getParameterTypes().length) {
                constructor2Use = constructor;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructor2Use, args);
    }

    /**
     * bean属性填充
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        if (propertyValues == null || propertyValues.getPropertyValues().length == 0) {
            return;
        }
        for (PropertyValue pv: propertyValues.getPropertyValues()) {
            String name = pv.getName();
            Object value = pv.getValue();
            if (value instanceof BeanReference) {
                // A依赖B，获取B的实例化
                BeanReference beanReference = (BeanReference) value;

                value = getBean(beanReference.getBeanName());
            }
            // 属性填充
            BeanUtil.setFieldValue(bean, name, value);
        }
    }
}
