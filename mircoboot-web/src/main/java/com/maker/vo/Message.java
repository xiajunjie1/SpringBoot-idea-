package com.maker.vo;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
/**
 * 希望以xml的形式返回数据，可以引入Jackson组件中xml的支持，修改dependencies.gradle文件
 *  implementation group: 'com.fasterxml.jackson.dataformat', name: 'jackson-dataformat-xml', version: '2.12.5'
 *  implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.12.5'
 *  implementation group: 'com.fasterxml.jackson.core', name: 'jackson-annotations', version: '2.12.5'
 *
 *  需要返回xml数据，需要对Vo类进行注解标识
 * */
@Data
//@XmlRootElement//根节点
public class Message {
   // @XmlElement//xml元素
    @NotBlank(//message="title字段不可为空"
            message = "{message.title.notblank.error}"
    )//【数据验证】该字段参数不能为为空
    private String title;//字符串参数
   //@XmlElement
    @NotNull(//message="pubdate字段不可为空"
    message="{message.pubdate.notblank.error}")//Date参数不可以使用NotBlank，因为NotBlank是空字符串的意思
    private Date pubdate;//日期的参数
    //@XmlElement
    @NotBlank(//message = "content字段不可为空"
    )
    private String content;
    @Email(//message = "email参数的格式错误"
             message="{message.email.email.error}")//【数据验证】格式必须为Email的格式
    @NotBlank(//message="email字段不可为空"
            message="{message.email.notblank.error}"
    )
    private String email;
    @Digits(integer = 1,fraction = 0,message = "level字段为一位整数")//1位的整数，0位的小数
    private Integer level;//消息级别

}
