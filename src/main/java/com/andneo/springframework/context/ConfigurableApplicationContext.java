package com.andneo.springframework.context;

import com.andneo.springframework.beans.BeansException;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:46
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {
    // 刷新容器
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
