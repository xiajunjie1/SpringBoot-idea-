package com.maker.test;

import com.maker.StartSpringBoot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StartSpringBoot.class)
public class MailTest {
/*
    @Autowired
    private JavaMailSender mailSender;//Mail模块提供的邮件发送工具类
    @Test
    public void send(){
        SimpleMailMessage message=new SimpleMailMessage();//建立简单的邮件结构
        message.setFrom("953668865@qq.com");
        message.setTo("953668865@qq.com");
        message.setSubject("Hello World");
        message.setText("这是一封基于SpringBoot Mail模块的测试邮件");
       this.mailSender.send(message);


    }*/
}
