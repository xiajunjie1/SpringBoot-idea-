package com.maker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/**
 * Thymeleaf的环境配置
 *  例如：所有的模板文件需要保存在资源目录下的templates目录下，否则是无法找到视图的
 *  在SpringBoot之中，所有的操作结构都是可以进行自定义配置的
 *
 *  配置环境：
 *      1.创建application.yml文件
 *      可以根据application.yml中的参考来进行配置
 *
 *  Thymeleaf静态资源文件
 *      在Thymeleaf中，所有的静态资源文件都保存在static目录下
 *      在templates下，使用@{/css/mystyle.css}的形式引用对应的静态资源文件
 *      所以涉及到Thymeleaf中操作的属性，前面均需要加上th:
 *
 *  Thymeleaf资源文件的读取（国际化）
 *      1.在资源文件夹下创建相应的properties文件，在application.yml配置资源文件的位置
 *      2.在templates下创建message_i18n.html页面，进行资源文件中的key值的读取
 *      3.在config包下创建Thymeleaf的配置类，进行当前环境的设定（实际上是一个Interceptor的注册配置）
 *      4.设置一个参数lang作为国际化环境参数，请求的时候带上该参数
 *      5.请求地址http://localhost:8080/thymeleaf/i18n?lang=en_US
 *
 *
 *   Thymeleaf环境对象
 *      即Thymeleaf的内置对象，为了与JSP中的环境对象进行区分
 *      在templates中创建message_attribute.html
 *
 *   Thymeleaf包含指令
 *      th:replace：使用标签进行替换，但是原始宿主的标签还会存在，包含标签不会出现；
 *      th:include：直接进行包含，原始的宿主会消失，保留包含的标签
 *
 *
 *   Thymeleaf数据处理对象
 *      #strings    字符串工具类
 *      #lists      List工具了
 *      #arrays     Array工具类
 *      #sets
 *      #maps
 *
 *
 *
 * */
@SpringBootApplication
public class starter extends SpringBootServletInitializer {

    public static void main(String[] args){
        SpringApplication.run(starter.class);
    }
}
