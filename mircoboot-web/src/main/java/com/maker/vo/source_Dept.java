package com.maker.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 进行对象注入的测试
 * */
@Data
@ConfigurationProperties(prefix = "object")//注入application.yml中key为object下的属性
@Component
public class source_Dept {
    private Long deptno;
    private String deptname;
    private source_Company company;
    private List<source_Emp> emps;

}
