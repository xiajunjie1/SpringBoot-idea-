package com.maker.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/object/*",produces = "application/json;charset=utf-8")
public class InnerObjectAction {
    /**
     * 第一种获取内置对象的方法
     *  Controller中的方法，通过形参自动注入对应的内置对象
     * */
    @GetMapping("first")
    public Object First(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        Map<String,Object> params=new HashMap<>();
        params.put("【request】contextpath",req.getContextPath());
        params.put("【request】method",req.getMethod());
        params.put("【request】参数",req.getParameter("msg"));
        params.put("【session】id",req.getSession().getId());
        params.put("【application】VirtualServerName",req.getServletContext().getVirtualServerName());
        params.put("【application】InitParameter",req.getServletContext().getInitParameter("name"));

        return params;
    }

    /**
     * 在传统的SpringMVC开发过程之中，也可以通过一个内置对象的获取操作方法来完成
     *  ServletRequestAttributes
     * */
    @GetMapping("second")
    public Object Second()throws Exception{
     ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest req=attributes.getRequest();
       HttpServletResponse resp=attributes.getResponse();
        Map<String,Object> params=new HashMap<>();
        params.put("【request】contextpath",req.getContextPath());
        params.put("【request】method",req.getMethod());
        params.put("【request】参数",req.getParameter("msg"));
        params.put("【session】id",req.getSession().getId());
        params.put("【application】VirtualServerName",req.getServletContext().getVirtualServerName());
        params.put("【application】InitParameter",req.getServletContext().getInitParameter("name"));

        return params;
    }
}
