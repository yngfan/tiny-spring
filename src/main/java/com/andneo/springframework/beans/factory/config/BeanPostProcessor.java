package com.andneo.springframework.beans.factory.config;

public interface BeanPostProcessor {

    // 在bean对象执行初始化方法之前，执行此方法
    Object postProcessBeforeInitialization(Object bean, String beanName);

    // bean对象初始化之后
    Object postProcessAfterInitialization(Object bean, String beanName);
}
