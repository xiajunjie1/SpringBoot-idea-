package com.maker.action;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 *  SpringBoot返回PDF数据
 *      1.引入ITextPDF组件的相关依赖
 *          implementation group: 'com.itextpdf', name: 'itextpdf', version: '5.5.13'
 *          在dependencies.gradle中添加该组件的依赖,然后在mircoboot-web中引用该依赖
 *
 *      2.需要用到PDF，则必须要有字体库
 *          如果在Windows中，可以直接引用Windows的字体库，但是如果项目部署到Linux上了，那么字体库肯定是不一样的
 *          所以现在最佳的做法就是将可能用到的PDF的字体库直接放到资源文件下"src/resources/fonts下
 *          如果需要在PDF中插入图片，可以将图片放在"src/resources/images下
 * */
@Controller
@RequestMapping("/pdf/")
public class PDFAction {
    @GetMapping("create")
    public void createPDF(HttpServletResponse response)throws Exception{//接收response进行响应处理
        response.setHeader("Content-Type","application/pdf");//设置头信息
        //生成PDF文件，并设置进行下载
        response.setHeader("Content-Disposition","attachement:filename=xia.pdf");
        //使用itextpdf组件在内存中形成一份生成的PDF文件
        //创建文档，并设置页面的大小和边距
        Document doc=new Document(PageSize.A4,10,10,50,20);//该Document是itextpdf提供的类，不是w3c或自带的document
        PdfWriter.getInstance(doc,response.getOutputStream());//获取PDF的输出配置
        doc.open();//开始构建PDF文档的内容
        //利用资源匹配符进行资源路径的定义
        //此Resource类是由Spring提供的
        Resource imgResource= new ClassPathResource("/images/air.jpg");//获取图片资源
        Image img= Image.getInstance(imgResource.getFile().getAbsolutePath());
        //PDF的图片，在生成的时候，是基于坐标来进行绘制的
        img.scaleToFit(PageSize.A4.getWidth()/2,PageSize.A4.getHeight());
        //获取坐标
        float pointX=(PageSize.A4.getWidth()-img.getScaledWidth())/2;
        float pointY=(PageSize.A4.getHeight()-img.getScaledHeight());
       //绘制坐标定义
        img.setAbsolutePosition(pointX,pointY);
        //添加图片到PDF文档中
        doc.add(img);
        doc.add(new Paragraph("\n\n\n\n\n\n\n\n\n"));//图片过后换三行
        //定义字体资源
        Resource fontResource=new ClassPathResource("/fonts/simhei.ttf");
        BaseFont baseFont=BaseFont.createFont(fontResource.getFile().getAbsolutePath(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        Font font=new Font(baseFont,20,Font.NORMAL);//定义要使用的字库
        //在PDF文件上绘制文本信息
        String[] titles={"xia","jun","jie"};
        String[] content={"夏","俊","杰"};
        for(int i=0;i<titles.length;i++){
            PdfPTable table=new PdfPTable(2);//定义表格
            PdfPCell cell=new PdfPCell();//创建单元格信息
            cell.setPhrase(new Phrase(titles[i],font));
            table.addCell(cell);
            cell=new PdfPCell();
            cell.setPhrase(new Phrase(content[i],font));
            table.addCell(cell);
            doc.add(table);
        }
        doc.close();
    }
}
