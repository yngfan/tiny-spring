package com.andneo.springframework.test;

import com.andneo.springframework.aop.AdvisedSupport;
import com.andneo.springframework.aop.TargetSource;
import com.andneo.springframework.aop.aspectj.AspectJExpressionPointCut;
import com.andneo.springframework.aop.framework.Cglib2AopProxy;
import com.andneo.springframework.aop.framework.JdkDynamicAopProxy;
import com.andneo.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.andneo.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.andneo.springframework.context.support.ClassPathXmlApplicationContext;
import com.andneo.springframework.core.io.DefaultResourceLoader;
import com.andneo.springframework.test.bean.IUserService;
import com.andneo.springframework.test.bean.UserService;
import com.andneo.springframework.test.bean.UserService2;
import com.andneo.springframework.test.bean.UserServiceInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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

//        runPrototype();

//        testProxyMethod();

//        testAOP();

        testDynamic();
    }

    private static void testDynamic() {
        UserService userService = new UserService();

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut("execution(* com.andneo.springframework.test.bean.IUserService.*(..))"));
        advisedSupport.setTargetSource(new TargetSource(userService));

        IUserService jdkProxy = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + jdkProxy.queryUserInfo());


        IUserService cgligProxy = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + cgligProxy.register("花花"));


    }

    private static void testAOP() {
        AspectJExpressionPointCut pointCut = new AspectJExpressionPointCut("execution(* com.andneo.springframework.test.bean.UserService.*(..))");
        Class<UserService2> clazz = UserService2.class;
        Method method = null;
        try {
            method = clazz.getDeclaredMethod("queryUserInfo");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        boolean matches = pointCut.matches(clazz);
        boolean matches1 = pointCut.matches(method, clazz);
        System.out.println("matches " + matches);
        System.out.println("matches1 " + matches1);
    }

    private static void testProxyMethod() {
        Object targetObj = new UserService2();
        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                return null;
            }
        });

    }

    private static void runPrototype() {

        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService2 userService01 = applicationContext.getBean("userService", UserService2.class);
        UserService2 userService02 = applicationContext.getBean("userService", UserService2.class);

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
        UserService2 userService = applicationContext.getBean("userService", UserService2.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }

    private static void runApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService2 userService = (UserService2) applicationContext.getBean("userService");
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

        UserService2 userService = (UserService2) beanFactory.getBean("userService", UserService2.class);
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
