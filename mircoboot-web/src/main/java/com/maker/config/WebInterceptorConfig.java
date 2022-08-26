package com.maker.config;

import com.maker.interceptor.FirstInterceptor;
import com.maker.interceptor.MdcInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * 拦截器配置类
 * */
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //registry.addInterceptor(this.getInterceptor()).addPathPatterns("/**");//添加拦截器，并设置拦截路径
        registry.addInterceptor(this.getMDCInterceptor()).addPathPatterns("/logger/**");
    }

    public FirstInterceptor getInterceptor(){
        return new FirstInterceptor();
    }
    @Bean
    public HandlerInterceptor getMDCInterceptor(){
        return new MdcInterceptor();
    }
}
