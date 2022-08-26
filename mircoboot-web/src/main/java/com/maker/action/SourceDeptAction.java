package com.maker.action;

import com.maker.vo.source_Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/sdept/*",produces = "application/json;charset=utf-8")
public class SourceDeptAction {
    @Autowired
    private source_Dept dept;
    @RequestMapping("show")
    public Object show(){
        return this.dept;
    }
}
