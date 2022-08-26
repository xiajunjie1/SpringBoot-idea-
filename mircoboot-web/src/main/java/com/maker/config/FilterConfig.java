package com.maker.config;

import com.maker.filter.JieFilter;
import com.maker.filter.JunFilter;
import com.maker.filter.XiaFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean getXiaFilterRegistation(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(this.getXiaFilter());
        filterRegistrationBean.setName("xiaFilter");//设置过滤器名字
        filterRegistrationBean.addUrlPatterns("/orders/*");//设置过滤路径
        filterRegistrationBean.setOrder(5);//设置执行顺序
        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean getJunFilterRegistation(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(this.getJunFilter());
        filterRegistrationBean.setName("junFilter");//设置过滤器名字
        filterRegistrationBean.addUrlPatterns("/orders/*");//设置过滤路径
        filterRegistrationBean.setOrder(3);//设置执行顺序
        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean getJieFilterRegistation(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(this.getJieFilter());
        filterRegistrationBean.setName("jieFilter");//设置过滤器名字
        filterRegistrationBean.addUrlPatterns("/orders/*");//设置过滤路径
        filterRegistrationBean.setOrder(10);//设置执行顺序
        return filterRegistrationBean;

    }

    @Bean
    public XiaFilter getXiaFilter(){
        return new XiaFilter();//手工实例化Filter对象
    }

    @Bean
    public JunFilter getJunFilter(){
        return new JunFilter();//手工实例化Filter对象
    }

    @Bean
    public JieFilter getJieFilter(){
        return new JieFilter();//手工实例化Filter对象
    }
}
