package com.andneo.springframework.beans.factory.config;

/**
 * @program: tiny-spring
 * @description: BeanReference
 * @author: fanfan.yang
 * @create: 2021-09-24 15:52
 **/
public class BeanReference {

    private String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

}
