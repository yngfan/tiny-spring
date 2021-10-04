package com.andneo.springframework.aop.framework;

import com.andneo.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 11:32
 **/
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advised = advisedSupport;
    }

    @Override
    public Object getProxy() {

        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {

            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();

            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));

        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
