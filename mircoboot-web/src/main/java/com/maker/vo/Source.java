package com.maker.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
/**
 * 该类用来接收yml文件中的属性值
 *  该类中的属性名称要与yml文件中定义的key保持一致
 * */
@Data
@ConfigurationProperties("sources")//此处sources要与yml中顶部的key保持一致
@Component
public class Source {
    private String mysql;
    private String redis;
    private List<String> message;
    private Map<String,String> infos;
}
