package com.andneo.springframework.context;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 00:57
 **/
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
