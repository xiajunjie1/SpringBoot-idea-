package com.maker.service.impl;

import com.maker.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggerServiceImp implements LoggerService {
    @Override
    public void echo(String msg) {
        log.info("【业务层输出】获取到的信息为{}",msg);
    }
}
