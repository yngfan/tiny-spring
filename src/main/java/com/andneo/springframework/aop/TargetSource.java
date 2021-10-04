package com.andneo.springframework.aop;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 11:35
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?>[] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }


    public Object getTarget() {
        return this.target;
    }
}
