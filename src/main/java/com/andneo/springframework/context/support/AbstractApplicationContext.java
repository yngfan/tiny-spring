package com.andneo.springframework.context.support;

import com.andneo.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.andneo.springframework.context.ConfigurableApplicationContext;
import com.andneo.springframework.core.io.DefaultResourceLoader;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:47
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public void refresh() {

    }
}
