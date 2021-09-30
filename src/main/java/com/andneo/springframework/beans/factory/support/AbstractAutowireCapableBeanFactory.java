package com.andneo.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.PropertyValue;
import com.andneo.springframework.beans.PropertyValues;
import com.andneo.springframework.beans.factory.DisposableBean;
import com.andneo.springframework.beans.factory.InitializingBean;
import com.andneo.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiny-spring
 * @description: AbstractAutowireCapableBeanFactory
 * @author: fanfan.yang
 * @create: 2021-09-23 17:21
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 实例化bean
            bean = createBeanInstance(beanName, beanDefinition, args);
            // 给bean填充属性。利用反射
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行Bean的初始化，BeanPostProcessor前置和后置处理
            bean = initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 注册实现了DisposableBean接口的Bean对象
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        // 放入容器
        addSingleton(beanName, bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // BeanPostProcessor前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        // 初始化
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // BeanPostProcessor后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1、实现接口，InitializingBean
        if (bean instanceof InitializingBean) {
            try {
                ((InitializingBean) bean).afterPropertiesSet();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // 2、配置信息，init-method
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            try {
                Method initMethod = beanDefinition.getClass().getMethod(initMethodName);
                if (initMethod == null) {
                    throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
                }
                initMethod.invoke(bean);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
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

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor: getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor: getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) return result;
            result = current;
        }
        return result;
    }
}
