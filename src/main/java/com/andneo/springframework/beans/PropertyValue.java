package com.andneo.springframework.beans;

/**
 * @program: tiny-spring
 * @description: PropertyValue
 * @author: fanfan.yang
 * @create: 2021-09-24 11:51
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
