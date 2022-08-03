package com.maker.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Springboot提供，请求处理器，继承ResourceHttpRequestHandler父类
 * */
@Component("videohandler")
public class VideoResourceHttpRequestHandler extends ResourceHttpRequestHandler {
    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        return new ClassPathResource("/videos/logo_animation.ogv");
    }
}
