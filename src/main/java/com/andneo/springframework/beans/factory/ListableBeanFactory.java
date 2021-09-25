package com.andneo.springframework.beans.factory;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory {
    // 按照类型返回bean实例
    <T> Map<String, T> getBeanOfType(Class<T> type);

    // 返回注册表中所有Bean名称
    String[] getBeanDefinitionNames();
}
