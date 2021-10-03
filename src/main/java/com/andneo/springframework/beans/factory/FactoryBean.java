package com.andneo.springframework.beans.factory;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-03 18:48
 **/
public interface FactoryBean<T> {

    Object getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
