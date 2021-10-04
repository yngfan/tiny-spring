package com.andneo.springframework.context;

import com.andneo.springframework.beans.factory.HierarchicalBeanFactory;
import com.andneo.springframework.beans.factory.ListableBeanFactory;
import com.andneo.springframework.core.io.ResourceLoader;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:39
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
