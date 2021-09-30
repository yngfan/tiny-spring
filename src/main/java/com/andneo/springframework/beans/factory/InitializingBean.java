package com.andneo.springframework.beans.factory;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-28 23:26
 **/
public interface InitializingBean {

    /**
     * Bean处理了属性填充后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;



}
