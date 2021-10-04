package com.andneo.springframework.test.event;

import com.andneo.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 11:12
 **/
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
