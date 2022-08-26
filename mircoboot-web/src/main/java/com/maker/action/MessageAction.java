package com.maker.action;

import com.maker.common.action_abs.AbstractBaseAction;
import com.maker.service.MessageService;
import com.maker.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;//slf4j标准
import org.slf4j.LoggerFactory;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController//@Controller+@ResponseBody
@RequestMapping("/msg/")
@Validated
@Slf4j
public class MessageAction extends AbstractBaseAction {
    @Autowired
    @Qualifier("msgservice")//该类在Spring配置文件中进行过定义
    private MessageService imessage;
    //设置日志对象
    //private static final Logger LOGGER= LoggerFactory.getLogger(MessageAction.class);

    //@ResponseBody
    //不加会乱码
    @RequestMapping(value="echo",produces ="application/json;charset=utf-8")
   // @RequestMapping(value="echo",produces ="application/xml;charset=utf-8")//返回xml数据
    public Object echo(@Validated Message msg){
        //输出日志信息
        //LOGGER.info("接收的msg为{}",msg);//使用slf4j时，可以直接使用占位符
        try{
            imessage.echo("test data");
        }catch(Exception e){
            e.printStackTrace();
        }
        return msg;
    }
    @RequestMapping(value="cal",produces ="application/json;charset=utf-8")
    public Integer cal(int x,int y){
        return x/y;
    }

    @RequestMapping("get")
    public Object get(@NotNull @Length(min=3,max = 10) String id){
        //接收id参数，id不能为空，且长度在3-10之间
        //这个时候可以在类上面加上@Validator注解，使此处参数上的注解生效
        return "【jay】"+id;
    }

    @RequestMapping("logout")
    public Object logOut(String info){
        Map<String,String> params=new HashMap<>();
       // LOGGER.info("获取到的信息是{}",info);
        log.error("获取到的信息是{}",info);
        log.warn("获取到的信息是{}",info);
        log.info("获取到的信息是{}",info);//使用Slf4j注解后，会生成一个log对象，简化日志处理
        log.debug("获取到的信息是{}",info);
        log.trace("获取到的信息是{}",info);
        params.put("information",info);
        return params;
    }
}
