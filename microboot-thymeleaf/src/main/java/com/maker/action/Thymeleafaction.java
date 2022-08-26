package com.maker.action;

import com.maker.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping("/thymeleaf/*")
public class Thymeleafaction {
    @RequestMapping("view")
    public ModelAndView view(){
        ModelAndView mav=new ModelAndView("message_show");//Springboot thymeleaf的视图解析器，默认前缀为/templates/，后缀为.html
        mav.addObject("message","xiajunjie");
        mav.addObject("title","xia");
        mav.addObject("con","hatejob");
        return mav;
    }
    @RequestMapping("path")
    public String path(){
        return "static_show";
    }

    @RequestMapping("i18n")
    public String i18n(){
        return "message_i18n";
    }

    @RequestMapping("attribute")
    public String attribute(HttpServletRequest request){
      request.setAttribute("message","【Request】JayjTest");
      request.getSession().setAttribute("message","【Session】JayjTest");
      request.getServletContext().setAttribute("message","【Application】JayjTest");
      return "message_attribute";
    }

    @RequestMapping("obj")
    public ModelAndView showObj() throws Exception{
      ModelAndView mav=new ModelAndView();
      mav.setViewName("message_object");
        Member member=new Member("xia",22,
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1996-01-31 10:10:10"),
                888.66);
        mav.addObject("member",member);
        return mav;

    }

    @RequestMapping("logic")
    public ModelAndView showLogic() throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.setViewName("message_logic");
        Member member=new Member("xia",16,
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1996-01-31 10:10:10"),
                888.66);
        mav.addObject("member",member);
        return mav;

    }
    @RequestMapping("list")
    public ModelAndView showlist() throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.setViewName("message_loop");
        List<Member> mlist=new ArrayList<>();
        for(int i=0;i<10;i++){
            Member member=new Member("xia-"+i,16+i,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1996-01-31 10:10:10"),
                    888.66+i*100);
            mlist.add(member);

        }
        mav.addObject("mlist",mlist);
        return mav;
    }

    @RequestMapping("map")
    public ModelAndView showmap() throws Exception{
        ModelAndView mav=new ModelAndView();
        mav.setViewName("message_loop2");
        //Map<String,Member> maps=new HashMap<>();//无序
        Map<String,Member> maps=new LinkedHashMap<>();//有序
        for(int i=0;i<10;i++){
            Member member=new Member("xia-"+i,16+i,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1996-01-31 10:10:10"),
                    888.66+i*100);
            maps.put("jay-"+i,member);

        }
        mav.addObject("maps",maps);
        return mav;
    }
    @RequestMapping("include")
    public String include(){
        return "message_include";
    }
}
