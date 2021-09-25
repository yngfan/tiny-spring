package com.andneo.springframework.context;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-25 01:46
 **/
public interface ConfigurableApplicationContext extends ApplicationContext {
    // 刷新容器
    void refresh();
}
