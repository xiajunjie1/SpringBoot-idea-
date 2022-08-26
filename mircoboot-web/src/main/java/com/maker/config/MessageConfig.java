package com.maker.config;

import com.maker.vo.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    @Bean("bindmsg")
    public Message getMessage(){
        Message msg=new Message();
        msg.setTitle("Jayj");
        msg.setContent("这是一个固定的响应内容");
        return msg;
    }
}
