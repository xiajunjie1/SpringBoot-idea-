package com.maker.action;

import com.maker.service.LoggerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/logger/*")
public class LoggerAction {
    @Autowired
    private LoggerService loggerService;
    @RequestMapping("echo")
    public String echo(String msg){
        log.info("【控制层】日志输出：{}",msg);
        loggerService.echo(msg);
        return msg;
    }


}
