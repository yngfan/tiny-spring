package com.andneo.springframework.context.event;

import com.andneo.springframework.context.ApplicationEvent;
import com.andneo.springframework.context.ApplicationListener;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 00:24
 **/
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);

}
