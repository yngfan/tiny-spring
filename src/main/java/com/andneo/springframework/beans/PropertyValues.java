package com.andneo.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiny-spring
 * @description: PropertyValues
 * @author: fanfan.yang
 * @create: 2021-09-24 11:51
 **/
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getProperty(String propertyName) {
        for (PropertyValue propertyValue: propertyValueList) {
            if (propertyName.equals(propertyValue.getName())) {
                return propertyValue;
            }
        }
        return null;
    }

}
