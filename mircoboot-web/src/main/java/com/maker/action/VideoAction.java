package com.maker.action;

import com.maker.config.VideoResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 对视频资源进行响应处理
 *
 * */
@RestController
@RequestMapping("/video/")
public class VideoAction {
    @Autowired//注入请求资源处理器类
private VideoResourceHttpRequestHandler videoHandler;
    @RequestMapping("create")
    public void create(HttpServletRequest req, HttpServletResponse resp)throws Exception{
        //响应处理视频流
        this.videoHandler.handleRequest(req,resp);
    }
}
