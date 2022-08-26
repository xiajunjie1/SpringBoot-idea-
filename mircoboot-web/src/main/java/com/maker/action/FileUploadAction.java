package com.maker.action;

import com.maker.common.action_abs.AbstractBaseAction;
import com.maker.vo.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理上传文件的功能的Action，本质上是整合的SpringMVC上传文件的功能
 *  启用并设置上传文件的配置在application.yml中进行配置
 *
 *  使用curl命令，上传文件请求，对该Action进行测试
 *      curl -X POST -F "file=@D:\Hello1.png" -F "title=myname" -F "content=xiajunjie" -F "pubdate=2022-08-05" http://localhost:8080/form/upload
 *  可以得到如下返回结果：
 *      {"filename":"file","filesize":410772,"message":{"title":"myname","pubdate":"2022-08-04T16:00:00.000+00:00","content":"xiajunjie"},"fileOriginalFilename":"Hello1.png","fileContentType":"image/png"}
 *   已经获取到了文件的信息，那么说明获取文件成功
 *
 * */
@RestController
@RequestMapping(value="/form/*")
public class FileUploadAction extends AbstractBaseAction {
    @PostMapping("upload")
    public Object uploadHandler(Message message, MultipartFile file)throws Exception{
        Map<String,Object> maps=new HashMap<>();
        maps.put("message",message);//普通对象
        maps.put("filename",file.getName());//临时文件名称
        maps.put("fileOriginalFilename",file.getOriginalFilename());
        maps.put("fileContentType",file.getContentType());
        maps.put("filesize",file.getSize());

        return maps;
    }
}
