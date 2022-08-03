package com.maker.action;

import com.maker.common.action_abs.AbstractBaseAction;
import com.maker.service.MessageService;
import com.maker.vo.Message;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController//@Controller+@ResponseBody
@RequestMapping("/msg/")
public class MessageAction extends AbstractBaseAction {
    @Autowired
    @Qualifier("msgservice")//该类在Spring配置文件中进行过定义
    private MessageService imessage;
    //设置日志对象
   // private static final Logger LOGGER= LoggerFactory.getLogger(MessageAction.class);

    //@ResponseBody
    //不加会乱码
    @RequestMapping(value="echo",produces ="application/json;charset=utf-8")
   // @RequestMapping(value="echo",produces ="application/xml;charset=utf-8")//返回xml数据
    public Object echo(Message msg){
        //输出日志信息
       // LOGGER.info("接收的msg为{}",msg);
        try{
            imessage.echo("test data");
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }
}
