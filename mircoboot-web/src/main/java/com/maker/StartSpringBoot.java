package com.maker;//该包下所有子包的类会被自动扫描

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
/**
 *  SpringBoot项目的热加载
 *      需要在项目之中引入一个“devtools”的依赖库
 *      在父项目的build.gradle中，对该插件进行依赖的配置
 *      idea中配置【File】-【settings】- 【Build, Execution, Deployment】-【Compiler】-【Build Project Automatically】
 *      在idea（旧版本）中进行配置注册，按下组合键【Ctrl+Shift+Alt+/】，注册，勾选compiler.automake.allow.when.app.running
 *      在iidea（新版本）中，【File】 - 【Settings】 - 【Advanced Settings】
 *      重新启动idea工具，即可实现自动的热部署
 *
 *      实际上所谓的热部署的本质是将整个类加载器进行了拆分，在没有引入“devtools”工具的时候，所有系统类和用户的程序类都使用同一个类加载器
 *      此时实际上就是程序类的加载器进行重新工作，重新启动，这样启动要比整个项目重新启动，速度更快
 *
 *      在项目发布的时候，该功能就意义不大了，可以直接在父项目中，将对应的依赖进行注释或者删除
 *
 *   SpringBoot中提供了一个“Spring-boot-start-test”，但是springboot中，
 *   该依赖库默认的是使用的Junit4版本，而对于当前的应用环境已经开始广泛普及Junit5了
 *   如果想要对当前项目进行Junit测试，则需要引入一些相关的测试组件，本次要引入的测试组件内容如下：
 *      Spring Boot Starter Test
 *      JUnit Jupiter API
 *      JUnit Vintage Engine
 *      JUnit Jupiter Engine
 *      Junit Platform Launcher
 *      junit-bom
 *
 *    修改父项目中的dependencies.gradle文件
 * */
@SpringBootApplication//标注该类未一个SpringBoot的启动类，代替了@EnableAutoConfiguration 注解
@ImportResource(locations = {"classpath:META-INF/spring/beans.xml"})//导入Spring的配置文件
public class StartSpringBoot {
    public static void main(String[] args){

        SpringApplication.run(StartSpringBoot.class);
    }
}