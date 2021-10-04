package com.andneo.springframework.context.event;

import com.andneo.springframework.beans.factory.BeanFactory;
import com.andneo.springframework.context.ApplicationEvent;
import com.andneo.springframework.context.ApplicationListener;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 01:07
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
