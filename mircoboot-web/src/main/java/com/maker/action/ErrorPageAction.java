package com.maker.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局错误页，用来以Restful风格的形式返回错误信息
 * 等同于前端页面，在config中定义配置类，针对不同的错误跳转到相应的ActionMethod的路径
 * */
@RestController
@RequestMapping(value="/errors/*",produces = "application/json;charset=utf-8")
public class ErrorPageAction {//用于进行错误页的处理
    @RequestMapping("error404")
    public Object errorCode404(){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req=attributes.getRequest();
        HttpServletResponse resp=attributes.getResponse();
        Map<String,Object> errinfo=new HashMap<>();//用来存储错误信息
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);//404响应编码
        errinfo.put("status",resp.getStatus());//设置状态
        errinfo.put("content","无法找到该页面");
        errinfo.put("refere",req.getHeader("Refere"));//获取之前的路径来源
        errinfo.put("path",req.getRequestURI());//访问路径
        return errinfo;
    }

    @RequestMapping("error500")
    public Object errorCode500(){
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req=attributes.getRequest();
        HttpServletResponse resp=attributes.getResponse();
        Map<String,Object> errinfo=new HashMap<>();//用来存储错误信息
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);//500响应编码
        errinfo.put("status",resp.getStatus());//设置状态
        errinfo.put("content","服务器内部异常");
        errinfo.put("refere",req.getHeader("Refere"));//获取之前的路径来源
        errinfo.put("path",req.getRequestURI());//访问路径
        return errinfo;
    }

}
