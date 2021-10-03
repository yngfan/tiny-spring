package com.andneo.springframework.beans.factory;

import com.andneo.springframework.beans.BeansException;
import com.andneo.springframework.context.ApplicationContext;

public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
