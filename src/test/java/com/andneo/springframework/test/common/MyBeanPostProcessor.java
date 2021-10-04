package com.andneo.springframework.test.common;

import com.andneo.springframework.beans.factory.config.BeanPostProcessor;
import com.andneo.springframework.test.bean.UserService2;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-28 22:26
 **/
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)) {
            UserService2 userService = (UserService2) bean;
            userService.setLocation("改为：上海");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
