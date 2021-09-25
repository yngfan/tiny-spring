package com.andneo.springframework.test;

import com.andneo.springframework.beans.PropertyValue;
import com.andneo.springframework.beans.PropertyValues;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanReference;
import com.andneo.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.andneo.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.andneo.springframework.core.io.DefaultResourceLoader;
import com.andneo.springframework.test.bean.UserService;

/**
 * @program: tiny-spring
 * @description: ApiTest
 * @author: fanfan.yang
 * @create: 2021-09-23 18:56
 **/

public class ApiTest {

    public static void main(String[] args) {

//        run05();

        runXML();
    }

    private static void runXML() {
        // 初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 读取配置文件，注册bean
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = (UserService) beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();

    }

//    private static void run05() {
//        // 初始化beanFactory
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // UserDao注册
//        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
//        // UserService注册
//        PropertyValues propertyValues = new PropertyValues();
//        propertyValues.addPropertyValue(new PropertyValue("uid", "001"));
//        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
//
//        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));
//
//        UserService userService = (UserService) beanFactory.getBean("userService");
//        userService.queryUserInfo();
//    }


}
