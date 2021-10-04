package com.andneo.springframework.context.event;

import com.andneo.springframework.context.ApplicationContext;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 00:19
 **/
public class ContextClosedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

}
