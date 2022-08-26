package com.maker.action;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
/**
 * 由以下的返回结构可知，两个方法中，返回的data是不同的
 * 但是返回的结构当中总有相同的title和content数据
 * 这些相同的数据就是全局数据，而这些全局信息是否需要动态加载呢
 * 所以这种固定在Action中的做法明显是不那么可取的，此时就可以做一个全局数据绑定
 * 在advice中追加一个处理操作
 * */
@RestController
@RequestMapping("/data/*")
public class GlobalDataAction {
    @RequestMapping("echo")
    public Object echo(String message, Model model){
        Map<String,Object> map=(Map<String, Object>) model.asMap().get("bindModel");//获取绑定的全局map数据
        map.put("data",message);//不同的处理有可能返回不同的处理结果
       // map.put("title","jayj");//title为附加信息，内容是固定的
        //map.put("content","固定的响应数据");
        return map;//通过map返回数据
    }
    @RequestMapping("calc")
    public Object calc(int x,int y,Model model){
        Map<String,Object> map=(Map<String, Object>) model.asMap().get("bindModel");//获取绑定的全局map数据
        map.put("data",x*y);//不同的处理有可能返回不同的处理结果
       // map.put("title","jayj");//title为附加信息，内容是固定的
       // map.put("content","固定的响应数据");
        return map;//通过map返回数据
    }
}
