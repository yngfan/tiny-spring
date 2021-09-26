package com.andneo.springframework.context.support;

import com.andneo.springframework.beans.BeansException;

import java.util.Map;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-26 01:31
 **/
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;


    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    public String[] getConfigLocations() {
        return configLocations;
    }
}
