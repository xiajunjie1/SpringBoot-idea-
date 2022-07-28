package com.maker.com.maker.action;

import com.maker.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg/")
public class MessageAction {
    @Autowired
    @Qualifier("msgservice")//该类在Spring配置文件中进行过定义
    private MessageService imessage;
    //设置日志对象
    private static final Logger LOGGER= LoggerFactory.getLogger(MessageAction.class);

    @ResponseBody
    //不加会乱码
    @RequestMapping(value="echo",produces ="application/json;charset=utf-8")
    public String echo(String msg){
        //输出日志信息
        LOGGER.info("接收的msg为{}",msg);
        try{
            imessage.echo(msg);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "<h2>【Echo2】SpringBoot return info:"+msg+"</h2>";
    }
}
