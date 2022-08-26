package com.maker.advice;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalDataPreHandle {
    //设置参数前缀
    @InitBinder("human")
    public void human(WebDataBinder binder){
        binder.setFieldDefaultPrefix("human.");
    }

    @InitBinder("job")
    public void job(WebDataBinder binder){
        binder.setFieldDefaultPrefix("job.");
    }
}
