package com.maker.advice;

import com.maker.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDataAdvice {
    @Autowired
    @Qualifier("bindmsg")
    private Message msg;
    @ModelAttribute(name="bindModel")
    public Object dataBind(){
        Map<String,Object> map=new HashMap<>();//绑定一个map集合
       // map.put("title","jayj");//所有的响应都需要提供该类数据
        //map.put("content","固定的响应数据");//所有的响应都需要提供该类数据
        //为了让绑定的数据，也可以通过配置类来动态加载，在config中创建一个MessageConfig动态类
        map.put("title",msg.getTitle());
        map.put("content",msg.getContent());
        return map;

    }
}
