package com.andneo.springframework.beans;

/**
 * @program: tiny-spring
 * @description: BeansException
 * @author: fanfan.yang
 * @create: 2021-09-23 17:06
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
