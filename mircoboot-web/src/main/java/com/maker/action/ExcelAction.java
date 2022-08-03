package com.maker.action;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.maker.vo.MessageExcel;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 返回Excel数据
 *  在Java的开发领域当中有一个著名的POI开源组件，这个组件的功能是非常强大的，而且是专门可以处理Excel文件
 *  在SpringBoot之中，为了便于用户生成Excel文件，所以提供了"easypoi-spring-boot-starter"依赖库
 *      implementation group: 'cn.afterturn', name: 'easypoi-spring-boot-starter', version: '4.4.0'
 *  在dependencies.gradle中定义此插件
 *  修改build.gradle
 * */
@Controller
@RequestMapping("/excel/")
public class ExcelAction {
    @GetMapping("create")
    public void create(HttpServletResponse response)throws Exception{
        response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");//此类型可以在tomcat的web.xml中找到
        response.setHeader("Content-Disposition","attachment;filename=jay.xlsx");
        String[] titles={"xia","jun","jie"};
        String[] content={"这是第一条内容","这是第二条内容","这是第三条内容"};
        List<MessageExcel> list=new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            MessageExcel message=new MessageExcel();
            message.setTitle(titles[i]);
            message.setContent(content[i]);
            message.setPubdate(new Date(new Date().getTime()+i*10000));
            list.add(message);
        }
    //excel文件的配置
        ExportParams params=new ExportParams("jay消息管理","最新消息", ExcelType.XSSF);//设置Excel的标题、工作簿名，和Excel类型
        Workbook book=new XSSFWorkbook();//创建工作簿
        new ExcelExportService().createSheet(book,params,MessageExcel.class,list);//在数据导出
        book.write(response.getOutputStream());//将导出的数据输出到response流当中，返回客户端
    }
}
