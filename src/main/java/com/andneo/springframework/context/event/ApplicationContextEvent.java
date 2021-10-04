package com.andneo.springframework.context.event;

import com.andneo.springframework.context.ApplicationContext;
import com.andneo.springframework.context.ApplicationEvent;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 00:12
 **/
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
