package com.maker.config;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {//WEB端配置
    @Bean //方法名必须为localeResolver，否则无法生成bean对象，此处的方法名是作为bean id的
    public LocaleResolver localeResolver(){//Locale解析器
        SessionLocaleResolver slr = new SessionLocaleResolver();//针对当前Session配置Local环境
        slr.setDefaultLocale(Locale.getDefault());
        return slr;
    }

    public LocaleChangeInterceptor localeChangeInterceptor(){
        //系统提供的拦截器，用来修改LocaleResolver
        LocaleChangeInterceptor lci=new LocaleChangeInterceptor();
        lci.setParamName("lang");//根据lang的参数设置locale
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//通过拦截器来进行local的处理
        registry.addInterceptor(this.localeChangeInterceptor());//注册改变Locale的拦截器

    }


}
