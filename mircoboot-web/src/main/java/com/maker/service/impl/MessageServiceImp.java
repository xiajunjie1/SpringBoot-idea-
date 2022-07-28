package com.maker.service.impl;

import com.maker.service.MessageService;
import org.springframework.stereotype.Service;
/**
 * 虽然SpringBoot可以实现自动扫包装配等功能，但是也支持导入Spring的xml配置文件方式进行配置
 *  在resources目录下创建META-INF/spring目录，并创建对应的Spring xml文件
 * */
//@Service
public class MessageServiceImp implements MessageService {


    @Override
    public void echo(String msg) throws Exception {
        System.out.println("【service】后台响应："+msg);

    }
}
