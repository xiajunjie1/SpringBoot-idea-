package com.maker.action;

import com.maker.vo.Prehandler_Human;
import com.maker.vo.Prehandler_Job;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prehandle/*")
public class PrehandleAction {
    @RequestMapping("add")
    //在参数中添加@ModelAttribute注解，进行参数属性前缀的设置
    public Object add(@ModelAttribute("human") Prehandler_Human human, @ModelAttribute("job") Prehandler_Job job){
        Map<String,Object> map=new HashMap<>();
        //如果不进行处理，传入两个同名参数name，那么最后两个实例中的name属性会是一个字符串化后的数组
        //要想解决这些问题，就必须通过全局数据预处理，设置一些前缀，同样在advice包中创建一个GlobalDataPreHandle类
        map.put("human",human);
        map.put("job",job);
        return map;
        //对参数进行了设置后，请求：http://localhost:8080/prehandle/add?human.name=xia&age=25&job.name=Engineer&salary=1234.56
        //返回结果如下：{"job":{"name":"Engineer","salary":1234.56},"human":{"name":"xia","age":25}}
    }
}
