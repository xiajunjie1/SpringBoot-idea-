package com.maker.apspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 增强类
 * */
@Component
@Aspect
public class MyAspect {
    //环绕增强
    @Around("execution(* com.maker.service..*.*(..))")//定义当前切面
    public Object Around(ProceedingJoinPoint point)throws Throwable{
        System.out.println("【ServiceInvokeBefore】执行参数："+ Arrays.toString(point.getArgs()));
        Object obj=point.proceed(point.getArgs());//调用真实业务主体
        System.out.println("【ServiceInvokeAfter】返回结果："+obj);
        return obj;//返回执行结果
    }
}
