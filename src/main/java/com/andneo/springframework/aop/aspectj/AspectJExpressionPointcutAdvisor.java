package com.andneo.springframework.aop.aspectj;

import com.andneo.springframework.aop.Pointcut;
import com.andneo.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-10-04 18:33
 **/
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointCut pointCut;
    // 具体的拦截方法
    private Advice advice;
    // 表达式
    private String expression;

    @Override
    public Pointcut getPointcut() {
        if (pointCut == null) {
            pointCut = new AspectJExpressionPointCut(expression);
        }
        return pointCut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }

    public void setExpression(String expression){
        this.expression = expression;
    }

}
