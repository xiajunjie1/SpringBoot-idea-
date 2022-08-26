package com.maker.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Member {
    private String name;
    private Integer age;
    private Date birthday;
    private Double salary;


}
