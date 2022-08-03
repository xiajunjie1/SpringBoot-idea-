package com.maker.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
/**
 * 在对应的属性上，加上Excel的相关注解
 * */
@Data
public class MessageExcel {
    @Excel(name = "消息标题"//列名
             ,orderNum = "0",//列顺序
            width = 30//列宽度
    )
    private String title;
    @Excel(name="消息内容",orderNum = "1",width = 50)
    private String content;
    @Excel(name="发送时间",orderNum = "2",width = 30)
    private Date pubdate;
}
