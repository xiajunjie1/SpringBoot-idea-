package com.maker.common.action_abs;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 公共Action父类
 *      用于对控制器注册一个属性编辑器
 *      在common中配置好属性注册器后，启动web模块，会出现报错，找不到AbstractBaseAction类
 *      对mircoboot-common使用gradle进行build，之后仍然会报错原因如下：
 *          在本次项目搭建时，引入了spring-boot-gradle-plugin插件，这个插件时将已有的gradle任务与Springboot相关的任务进行绑定
 *          那么在对mircoboot-common子模块进行构建的时候就表示要进行的是spring boot的项目构建，可是该模块并不是一个spring boot项目模块
 *          所以自然无法进行构建了，那么此时就需要修改模块中的build.gradle配置文件，进行任务的禁用
 * */
public abstract class AbstractBaseAction {
    private static final DateTimeFormatter LOCAL_DATE_FORMAT=DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @InitBinder//用于在Controller中标注于方法上，为当前控制器注册一个属性编辑器，被它标注的方法必须有一个参数WebDataBinder
    //可以理解为帮助我们完成参数的绑定
    public void initBinder(WebDataBinder binder){//实体类中有Date属性，需要将传来的字符串转化为Date属性
        binder.registerCustomEditor(java.util.Date.class,
                new PropertyEditorSupport(){
                   //text为传入的参数值
                    @Override
                    public void setAsText(String text) throws IllegalArgumentException {
                        LocalDate localDate=LocalDate.parse(text,LOCAL_DATE_FORMAT);
                        System.err.println(text);
                        Instant instant=localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                        super.setValue(java.util.Date.from(instant));

                    }
                });
    }
}
