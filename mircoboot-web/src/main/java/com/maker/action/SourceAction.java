package com.maker.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 将application.yml中定义的属性值，注入到该action中
 * 并将属性值进行响应显示
 *
 * 由该action程序可知，所有配置在application.yml中的资源key都可以自动注入到
 * springboot的程序类之中，这一点的配置比原始Spring开发中的资源注入要更加简单一些
 *
 * */
@RestController
@RequestMapping("/source/*")
public class SourceAction {
    @Value("${sources.mysql}")
    private String mysql;
    @Value("${sources.redis}")
    private String redis;
    @Value("${sources.message}")
    private List<String> message;
    @Value("#{${sources.infos}}")//由于是注入map集合，所以要用到SpEL表达式来进行处理
    private Map<String,String> infos;
    @RequestMapping(value="show", produces ="application/json;charset=utf-8")
    public Object show(){
        Map<Object,Object> maps=new HashMap<>();
        maps.put("mysql",this.mysql);
        maps.put("redis",this.redis);
        maps.put("message",this.message);
        maps.put("infos",this.infos);
        return maps;
    }
}
