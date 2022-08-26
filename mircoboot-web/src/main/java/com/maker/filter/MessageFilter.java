package com.maker.filter;

import org.apache.poi.util.StringUtil;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器
 *      通过继承HttpFilter，也可以通过实现接口Filter来实现过滤器
 * */
@WebFilter("/*")//过滤的处理路径
public class MessageFilter  extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
       if("/msg/echo".equals(request.getRequestURI())){//判断当前的路径
            String title=request.getParameter("title");
            if(StringUtils.hasLength(title)){
                //title参数有内容
                System.err.println("【Filter】执行");
            }
       }
       chain.doFilter(request,response);
    }
}
