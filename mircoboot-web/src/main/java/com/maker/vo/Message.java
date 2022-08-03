package com.maker.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
    private String title;//字符串参数
   //@XmlElement
    private Date pubdate;//日期的参数
    //@XmlElement
    private String content;
}
