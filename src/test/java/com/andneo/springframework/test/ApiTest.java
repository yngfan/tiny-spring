package com.andneo.springframework.test;

import com.andneo.springframework.beans.PropertyValue;
import com.andneo.springframework.beans.PropertyValues;
import com.andneo.springframework.beans.factory.config.BeanDefinition;
import com.andneo.springframework.beans.factory.config.BeanReference;
import com.andneo.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.andneo.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.andneo.springframework.context.support.ClassPathXmlApplicationContext;
import com.andneo.springframework.core.io.DefaultResourceLoader;
import com.andneo.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @program: tiny-spring
 * @description: ApiTest
 * @author: fanfan.yang
 * @create: 2021-09-23 18:56
 **/

public class ApiTest {

//    @Test
    public static void main(String[] args) {

//        run05();

//        runXML();
        // applicationContext
//        runApplicationContext();

//        runShutDownHook();

        runPrototype();
    }

    private static void runPrototype() {

        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        String result = userService01.queryUserInfo();
        System.out.println("测试结果：" + result);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
//        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }

    private static void runShutDownHook() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    private static void runApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        String s = userService.queryUserInfo();
        System.out.println("测试结果" + s);
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
