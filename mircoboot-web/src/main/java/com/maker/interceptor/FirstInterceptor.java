package com.maker.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod=(HandlerMethod) handler;
            System.err.println("【Action对象】"+handlerMethod.getBean());//控制器的实例
            System.err.println("【Action类型】"+handlerMethod.getBeanType());
            System.err.println("【Action方法】"+handlerMethod.getMethod());
        }
        return true;
    }
}
