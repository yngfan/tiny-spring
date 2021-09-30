package com.andneo.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.beans.factory.DisposableBean;
import com.andneo.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-28 23:43
 **/
public class DisposableBeanAdapter implements DisposableBean {
    private Object bean;

    private String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 实现接口，DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        // 配置信息，destroy-method
        if (StrUtil.isNotEmpty(destroyMethodName)
            && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (destroyMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
