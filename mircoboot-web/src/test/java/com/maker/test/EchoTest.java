package com.maker.test;

import com.maker.StartSpringBoot;
import com.maker.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.rmi.MarshalException;

@ExtendWith(SpringExtension.class)//采用Junit5
@WebAppConfiguration//启动Web运行环境
@SpringBootTest(classes =StartSpringBoot.class)//配置程序启动类
public class EchoTest {
    @Autowired
    private MessageService messageService;

    @Test
    public void echoTest(){
        try{
            messageService.echo("测试");
            System.err.println("测试方法运行...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
