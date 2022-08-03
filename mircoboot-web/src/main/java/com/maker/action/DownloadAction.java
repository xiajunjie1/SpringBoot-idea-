package com.maker.action;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
/**
 * 下载文件响应基本模型
 *  如果日后需要通过一些判断逻辑来进行文件下载控制的时候，就可以采用该程序形式来完成了。
 *
 *
 * */
@RestController
@RequestMapping("/download/*")
public class DownloadAction {
    @GetMapping("filedownload")
    public void Filedownload(HttpServletResponse resp)throws Exception{
       resp.setContentType("application/force-download");//强制性下载
        resp.setHeader("Content-Disposition","attachment;filename=jayj.rar");
        Resource fileResource=new ClassPathResource("/files/test.rar");
        InputStream input=fileResource.getInputStream();//获取资源输入流
        byte[] data=new byte[1024];//每次读取1k
        int len=0;//读取到的数据长度
        while((len=input.read(data))!=-1){
            resp.getOutputStream().write(data,0,len);
        }
    }
}
