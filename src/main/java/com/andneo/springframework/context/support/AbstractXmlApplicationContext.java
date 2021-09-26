package com.andneo.springframework.context.support;

import com.andneo.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.andneo.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-26 01:23
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (configLocations != null) {
            reader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
