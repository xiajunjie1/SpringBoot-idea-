package com.maker;//该包下所有子包的类会被自动扫描

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
/**
 *  SpringBoot项目的热加载
 *      需要在项目之中引入一个“devtools”的依赖库
 *      在父项目的build.gradle中，对该插件进行依赖的配置
 *      idea中配置【File】-【settings】- 【Build, Execution, Deployment】-【Compiler】-【Build Project Automatically】
 *      在idea（旧版本）中进行配置注册，按下组合键【Ctrl+Shift+Alt+/】，注册，勾选compiler.automake.allow.when.app.running
 *      在idea（新版本）中，【File】 - 【Settings】 - 【Advanced Settings】
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
 *
 *
 *  Spring boot 属性定义与注入
 *      在Spring的开发框架当中，为了便于bean的配置支持，可以基于properties资源文件实现相关内容的定义
 *      随后在程序之中基于SpEL表达式进行内容的配置，而现在使用到了Springboot，可以使用application.yml
 *      文件实现同样的属性定义
 *
 *      在SpringBoot中并不提倡xml文件的配置形式，所以所有的相关配置可以在"src/resources/application.yml"
 *      中实现配置
 *          YAML属于一种特定结构的文本描述，这种结构一般会出现在软件项目的配置上
 *          在SpringBoot中用到了该类文件，但是需要注意的是，即使使用的是application.properties也是可以实现相同功能的
 *
 *
 *    SpringBoot项目的打包
 *      在SpringBoot中，提供了一个BootJar的任务，让我们简单设置后即可对SpringBoot的模块进行打包
 *      SpringBoot项目可以直接通过jar文件运行。
 *      测试项目打包，进入到mircoboot-web模块的build.gradle文件进行BootJar任务的配置
 *      执行BootJar任务后，会在工作目录(E:\IdeaProject\Microboot\mircoboot-web\build\libs)得到相应的jar文件
 *      利用 java -jar 文件名即可运行SpringBoot项目
 *
 *    调整JVM的运行参数
 *      所有的SpringBoot打包过后都肯定要通过JVM来运行，这样的话就一定涉及到JVM内存调整策略问题
 *      在通过命令行运行SpringBoot项目的时候，添加上JVM的运行参数
 *      java -jar -Xms8g -Xss256K -Xlog:gc -XX:+UseG1GC -Xmx8g 包名.jar
 *      以上命令是在Springboot项目打包运行时进行jvm参数的设置，也可以在开发时，对项目设置JVM参数
 *
 *      点击上方工具条打开Run/Debug Configuration ,添加VM option
 *      将参数：-Xms8g -Xss256K -Xlog:gc -XX:+UseG1GC -Xmx8g添加上去
 *
 *    SpringBoot修改WEB环境
 *      在实际的项目开发过程中，SpringBoot程序是内嵌了Tomcat容器，这样就提供了相关服务端的配置项
 *      开发者可以根据自己的需要来修改application.yml配置文件，实现WEB配置环境的处理
 *
 *      只要在项目中引入了spring-boot-starter-web依赖库，就可以直接进行Tomcat相关配置定义
 *      所以在application.yml中修改的时候，也会自动的提供随笔提示功能
 *      SpringBoot同样支持application.properties配置相应的web环境，如果properties文件和yml文件同时存在
 *      那么properties文件优先生效
 *      从大部分的实际应用来说，会使用*.yml文件，但是在做一些配置描述的时候会习惯的使用"properties"结构的key名称
 *
 *    SpringBoot项目打包war文件
 *      1.mircoboot-web子模块，修改build.gradle文件，添加war插件
 *      2.如果说，现在打算以war文件的形式来运行当前项目，那么就需要修改当前项目的启动类，这个类必须继承'org.springframework.boot.web.servlet.support.SpringBootServletInitializer'这个父类
 *      3.通过Gradle提供的任务进行打包处理(bootWar),执行完毕后会生成一个war包
 *      4.直接部署到Tomcat对应的webapps目录中
 *
 *    SpringBoot获取WEB内置对象
 *      Servlet内置对象：ServletRequest、ServletResponse、ServletContext、ServletConfig、HttpSession
 *      按照原始的基本概念来讲，如果获取了ServletRequest对象，那么就相当于获取到了ServletContext、ServletConfig、HttpSession
 *      同时基于SpringMVC的运行机制，可以直接在控制层获取到相应的内置对象
 *
 *    SpringBoot整合Web过滤器
 *      1.在filter包中定义MessageFilter
 *      2.在SpringBoot中进行过滤器注解的扫描处理，修改SpringBoot的启动类
 *
 *   以上做法为SpringBoot中较为基础的整合Filter的做法，如果有多个Filter，
 *   并且过滤的路径为同一个路径时，这时候就会涉及到一个过滤器执行顺序的问题了
 *   由于此Filter是在SpringBoot内嵌的容器中执行的，所以没有办法通过过滤器的名字来进行Filter的顺序执行
 *   这种时候可以通过创建一个Filter的配置类，来进行Filter的配置
 *   在config包下，创建一个filter配置类
 *
 *   SpringBoot整合Listener
 *      在SpringBoot中由于其是在内部进行了Tomcat的容器的启动，所以如果要使用
 *      监听器，也需要进行一定的配置来完成
 *
 *   SpringBoot整合拦截器
 *      编写好拦截器
 *      在config包下定义拦截器配置类
 *
 *
 *   AOP拦截器
 *      AOP拦截的最大特点在于可以直接进行指定类结构的拦截处理
 *      1.导入Spring Boot Starter AOP相关依赖
 *          implementation group: 'org.springframework.boot', name: 'spring-boot-starter-aop', version: '2.4.5'
 *      2.在aspect包中编写切面类
 *      3.在test模块中运行EchoTest，调用Service进行测试
 *
 *
 *   整合Mail邮件服务
 *      1.引入spring-boot-starter-mail依赖
 *      2.在application.yml中配置邮箱相关信息
 *      3.在测试类中，直接使用mail模块提供的bean组件，实现邮件的发送
 *
 *   SpringBoot全局错误页
 *      在SpringBoot中已经提供了相应的全局错误页，如果是使用的一个SpringBoot程序
 *      实现了所有Web程序的开发，这种配置模式是可以忍受的，但如果要结合当前流行的前后端分离设计
 *      此时数据返回的类型就不合适了，最佳的做法应该还是返回JSON数据信息
 *
 *      为了便于程序的管理，创建一个基于Restful风格的响应数据内容，同时基于Action来进行数据的响应
 *      1.在action包下，创建ErrorPageAction类
 *      2.在config下创建ErrorPage的配置类
 *
 *   SpringBoot全局异常处理
 *      除了错误页的处理形式，另一种处理形式就是异常处理
 *      进行异常处理的响应，也要考虑使用Rest风格返回信息
 *      可以使用ControllerAdvice的方法，用面向切面的方式来进行异常
 *
 *
 *   SpringBoot全局数据绑定
 *      @ControllerAdvice,此注解是实现全局设置的关键注解
 *      如果说现在采用的是前后端分离的技术，而这个前后端分离技术之中是需要考虑到数据最终的返回结构的
 *      因为除了所需要真正响应的数据之外，有可能还会附加一些其他的辅助性数据，例如每次都需要返回HTTP状态码
 *      或者是一些SessionID，认证数据等等，那么在这样的情况下，如果每一次响应都重复的编写，那么会非常的繁琐
 *      观察一种重复配置的响应结构数据：
 *          1.在action包中创建一个GlobalDataAction控制器
 *          2.在advice包中创建一个GlobalDataAdvice类
 *          3.在Action的方法中，添加上Model类参数
 *       在以后进行标准程序开发的时候，不可能后端随意返回数据，除了真正所需要返回的数据以外，也一定会有大量的附加信息
 *       而这些附加信息就可以通过以上的数据绑定操作来完成。
 *
 *
 *    SpringBoot全局数据预处理
 *      在SpringMVC中可以自动的将接收到的参数，转为Vo中的属性内容，如果现在有一种特殊情况
 *      有两个不同的类，但是类中有两个相同的属性，这两个对象中的属性还要同时接收
 *      在Action中创建PrehandleAction
 *
 *
 *    SpringBoot数据验证
 *       如果想要保证用户发送的请求可以得到正确的处理，那么需要对请求的参数进行验证，
 *       如果用户发送的请求数据符合既定的验证规则，那么则可交由Action正确处理，反之则
 *       应该进行错误信息的提示。而为了简化开发者数据校验的处理操作，可以直接使用“JSR 303：Bean Validation规范”
 *       实现具体的数据验证操作
 *       SpringBoot支持JSR303的验证规范，所以一旦验证出现了错误，则应该交由统一的异常处理机制
 *       进行标准化数据结构的响应，而且这个时候就不再通过具体的程序来进行验证处理了，可以直接利用JSR303
 *       在接收参数上实现请求参数的验证
 *          1.在web子模块中添加一个依赖库：
 *              implementation 'org.hibernate.validator:hibernate-validator:6.2.0.Final'
 *          2.修改dependencies.gradle,添加hibernate验证的依赖
 *        实际上JRS303是一系列的注解所组成的，也就是说在整个程序中会通过注解的形式来进行各种验证规则的配置
 *        那么这些注解只要引入了"hibernate-validator"依赖之后就可以直接使用了
 *           3.实现具体的验证操作，直接在接收的参数类型上使用，找到vo包下的Message类，进行注解配置
 *           4.在MessageAction，echo方法的参数列表出加上@validate注解，启用验证
 *           5.调用MessageAction控制器进行验证
 *
 *        如果没有使用vo类，那么又该如何实现以上的验证呢？
 *          1.可以对单个参数进行验证注解的定义，参考MessageAction中的get方法
 *
 *         错误信息的定义
 *          1.修改Message的程序类，在此类当中加入错误信息的显示
 *          2.在对应的错误注解当中，添加message属性，此属性就是显示的错误信息
 *
 *        如果将所有的错误信息都写到注解当中，如果后期需要修改，那又该如何呢？那么这个时候
 *        可以考虑利用资源文件的方式进行配置，资源文件是有明确定义要求的，文件名称必须是“ValidationMessages.properties"
 *          1.进行资源文件的配置
 *          2.进行资源文件的引用
 *
 *        自定义验证器
 *          1.自定义注解类
 *          2.自定义验证处理类（此类必须实现ConstraintValidator<自定义注解,Object>接口)
 *          3.在注解类中绑定自定义验证处理类
 *
 *      Thymeleaf编程
 *          SpringBoot中，如果不基于前后端分离的模式进行编程，那么前端页面可以和之前一样
 *          采用 *.jsp的形式进行开发，而SpringBoot打包运行的方式有两种，一种是jar包，通过
 *          java -jar的命令来运行，还有一种是打包成war包，放到对应的web服务器下进行运行
 *          如果是采用war包部署，那么jsp文件会被服务器转为*.java文件之后再进行编译成为*.class文件
 *          但是如果采用jar包部署，那么jar文件无法保存JSP文件生成的*.class文件，那么jsp的*.class文件保存在哪呢？
 *          基于以上的情况，在SpringBoot中抛弃了原生的jsp方式而采用了Thymeleaf的方式
 *
 *          Thymeleaf支持html和jsp中相应的标记，而且不会生成class临时文件，所以它是可以在jar包的模式下运行的。
 *          Thymeleaf是一种单容器的解决方案，如果项目是采用前后端分离，全部采用rest方式进行展示，那么Thymeleaf的操作
 *          就没有意义了
 *
 *          整合Thymeleaf
 *              1.创建子模块【microboot-thymeleaf】
 *              2.修改mircoboot下的build.gradle,增加project("mircoboot-thymeleaf")
 *              3.添加依赖Spring-boot-start-thymeleaf
 *              4.想要使用Thymeleaf操作需要提供有一个模板的保存路径，假设本次的保存路径为"src/main/view",修改子模块的build.gradle文件
 *
 *      Actuator监控
 *          通过Actuator可以实现一系列监控机制
 *          在实际项目开发中，利用微服务可以有效的进行项目结构拆分，将不同功能的微服务部署到不同的物理主机之中
 *          这样当某一个微服务出现问题时就很难进行准确的定位，所以在SpringBoot中提供了Actuator组件来实现每一个
 *          微服务的状态监控，而想要使用该组件，只需要在项目中引入“spring-boot-starter-actuator”依赖库即可
 *
 *          1.引入Actuator监控组件，修改Mircoboot项目的build.gradle,在mircoboot-web模块依赖处添加： implementation('org.springframework.boot:spring-boot-starter-actuator');
 *          2.启动web子模块的程序，观察输出信息
 *              Exposing 2 endpoint(s) beneath base path '/actuator'
 *              当前项目已经正常启动了，那么就可以用给定的路径正常访问，访问：http://localhost:8080/actuator
 *              获取的信息可以看actuator_info文档
 *              获取到的信息相对是比较少的，因为默认情况下，为了安全并没有开启此类的全部数据
 *              如果希望可以开启全部的actuator监控，那么就需要修改application.yml文件
 *           3.修改application.yml文件，开启相应的监控
 *
 *        Actuator接口访问
 *          一旦项目中引入Actuator组件后就会自动启动一些相应的监控接口
 *          这些接口的数据全部是以rest的方式进行返回的
 *          具体接口的访问，可以参考actuator_info2.json中的接口地址
 *
 *
 *        heapdump信息
 *          在整个JVM进程中，所有的对象都是保存在堆内存之中的（为方便实现GC处理
 *          ，也提供有年轻代，老年代），SpringBoot程序一旦进行了部署，那么就必须可以
 *          及时获取相关的SpringBoot中的JVM的堆内存使用信息。
 *
 *          如果直接访问/heapdump路径，那么会获得一个完整的离线数据，即会自动下载下来一个文件
 *          此文件里面的内容都是二进制的数据信息，这种文件是不可能通过文本工具打开的，只能通过特定的工具打开
 *          在JDK1.9之前，一直都存在一个叫做visualvm的工具
 *
 *        info接口
 *          在整个Actuator中，对于微服务的数据信息获取主要依靠的是'/info'接口，通过此接口可以直接对外公布
 *          系统之中微服务的主要功能。为了可以明确告诉使用者每一个微服务的作用，就可以自定义“/info"微服务信息。
 *          在application.yml中配置info
 *          配置完成后，访问/actuator/info接口，即可得到配置的信息
 *
 *          如果说现在所有的info都要通过application.yml来进行直接配置，那么最终很难进行修改
 *          因为一旦修改就需要重新进行项目的打包以及部署，所有的配置项都是固定的，最佳的做法是可以
 *          直接通过数据库来进行存储，这样就要有一个特定的程序来实现数据库信息的读取，所以为了解决这样的
 *          问题，在SpringBoot中专门提供了一个info的创建器
 *          mircoboot-web中创建actuator包，在此包下面创建info创建器类，此类需要实现InfoContributor接口
 *          通过此类添加的info属性和通过application.yml定义的info属性不冲突，所以application.yml文件可以
 *          用来定义一些公共的项目信息，而此类可以用在各个微服务模块中，添加各自模块的信息
 *
 *
 *        health服务信息
 *          在微服务项目中，会造成大量的服务节点，但是现在必须清楚的知道某一个节点的存活状态
 *          这个时候就需要进行健康状态获取，默认情况下，没有引入任何第三方依赖时，可以获取到的
 *          信息一定是处于健康状态的
 *
 *          需要动态的获取到此服务的信息，来判断微服务是否正常
 *          在SpringBoot的Actuator中提供了一个健康状态检测的接口标准:HealthIndicator接口
 *
 *        远程关闭
 *          当引入了Actuator后，都会提供一个专属的端点来进行“刹车”操作
 *          在引入接口后，会提供一个/shutdown的接口，知道调用了这个接口，就可以远程关闭
 *          微服务
 *
 *          /shutdown接口的功能默认是没有开启的
 *          需要在application.yml中手动开启该接口，才可以使用它
 *
 *
 *  Lombok日志注解
 *      只要是使用开发框架，那么一般都会存在日志问题。使用日志可以更加方便的问题定位（日志本身也不是万能的，也是有一些设计上的缺陷的）
 *      Tomcat中的日志问题
 *          在Tomcat安装成功后实际都会存在大量的后台输出日志（目录logs），这些日志若长年不去维护
 *          最终就会导致你的磁盘空间衰竭，所以常规做法是使用一个定时任务来实现清理操作
 *       对于当前的SpringBoot来讲，所有的程序是在启动的时候进行日志输出的，例如：
 *          启动SpringBoot程序
 *       以上的日志都是SpringBoot内置的系统输出，但是在实际的项目当中也需要针对业务上的处理进行一些日志上的记录
 *       那么这种环境按照传统规矩就需要引入SLF4J标准，在使用Log4J、Logback实现具体的日志操作。
 *
 *       传统的日志处理
 *          1.找到MessageAction
 *          2.获取LOGGER实例对象
 *
 *       利用lombok注解实现日志输出
 *          在MessageAction类上，添加@Slf4j注解
 *
 *  SpringBoot日志配置
 *      日志级别：
 *          ERROR、WARN，INFO，DEBUG，TRACE
 *          可以在application.yml中进行日志级别的配置
 *
 *      将日志输出到文件中：
 *          修改application.yml文件
 *          配置完成后，会在microboot项目目录下生成一个日志文件夹，里面会存放日志文件
 *
 *      整合Logback日志配置文件
 *          SpringBoot所提供的的application.yml方式实现日志管理的配置
 *          它的保存文件能力是很弱的，因为它会将所有的日志保存在一个文件当中，
 *          随着日积月累这个日志文件会变得相当庞大，所以常规的做法是将日志结构进行拆分，
 *          可以按照日期进行保存
 *          SLF4J是一个日志标准，而log4j和logback是日志的实现，如果想要实现更好的日志管理
 *          最好就采用原生配置方式进行处理，本次将试用Logback组件进行配置
 *
 *          SpringBoot中，Logback日志文件名上有要求，必须使用“logback-spring.xml"文件名称进行定义
 *          1.在resources目录中创建logback-spring.xml文件
 *          2.注释掉application.yml中日志的配置
 *          3.进行项目的访问
 *
 *        动态修改日志级别
 *          如果在进行系统日志配置的时候，设置的级别过低，那么会造成日志文件攀升的情况
 *          如果设置的级别过高，很多问题又不太容易发现
 *          如果想要进行日志级别的调整，常规的做法就是进行项目的修改，随后重新打包上传
 *          所以在这样的生产环境的要求下，就希望程序可以自动进行日志级别的调整
 *          在SpringBoot1.5以后，就可以通过Actuator来进行日志级别的修改
 *          访问接口：
 *              curl -X POST http://localhost:8080/actuator/loggers/com.maker.action.MessageAction -H "Content-Type:application/json" --data "{\"configuredLevel\": \"DEBUG\"}"
 *          此时以上Action的日志级别被改为了DEBUG，此接口操作针对的是在application.yml中配置的日志管理
 *
 *
 *        MDC全链路追踪
 *          如果现在有几十万个用户并发进行访问，这个时候你如何可以分辨出一个用户访问的控制层
 *          以及业务层日志呢？所以对于传统的日志记录来讲最大的实现技术问题在于：日志仅仅能够
 *          记录某一层的访问，但是却无法追踪一个用户的完整操作日志记录，所以即便出现了问题，
 *          即便你的项目已经存在有日志，那么你也很难解决具体的错误问题，所以这时候你就需要提供有
 *          一个全链路追踪
 *
 *          在进行日志输出的时候，可以添加一个RequestID到输出内容当中，这个RequestID可以是uuid也可以是用户的设备号
 *          以此来标记不同的请求
 *          1.创建一个MDC的拦截器
 *          2.注册拦截器
 *          3.重新访问项目
 *
 *
 *        Spring提供了SpringTask来实现简单的任务调度，传统的Java定时任务的开发提供的只是一个间隔任务
 *        但是却没有CRON任务支持，而SpringTask相比较QuartZ组件实际上会更加简单，原因在于其Spring深度融合
 *        1.创建一个任务的调度处理类，这个类中将创建两种不同的任务：间隔任务、CRON任务
 *        2.创建task包，并且创建任务类
 *
 *
 *
 *
 *
 *
 *
 * */
@SpringBootApplication//标注该类未一个SpringBoot的启动类，代替了@EnableAutoConfiguration 注解
@ImportResource(locations = {"classpath:META-INF/spring/beans.xml"})//导入Spring的配置文件
//由于需要使用到监听器，但是过滤器又可以通过配置类进行配置，在这种情况下，如果处理不当，可能会导致过滤器被重复注册
//为了解决以上的情况，可以指定需要扫描的basePackage
@ServletComponentScan("com.maker.listener")//扫描Servlet相关注解，即可以扫描到Filter，如果单独用配置类来配置filter，那么不需要使用该注解
public class StartSpringBoot extends SpringBootServletInitializer {
    public static void main(String[] args){

        SpringApplication.run(StartSpringBoot.class);
    }
}
