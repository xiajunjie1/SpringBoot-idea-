package com.maker.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class MdcInterceptor implements HandlerInterceptor {
    /*
        此名字来源于logback-spring.xml中的
    *    <substitutionProperty name="logging.pattern.console"
                          value="%clr(%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr([%X{requestId}]) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%ewtpc}"/>

    <!-- 定义日志文件输出匹配格式 -->

    <substitutionProperty name="logging.pattern.file"
                          value="%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} %clr([%X{requestId}]) ${PID:- } --- [%t] %-40.40logger{39} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%ewtpc}"/>
    * */
    private static final String REQUEST_ID="requestId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {//请求之前进行MDC的绑定
        String forward=request.getHeader("x-Forwarded-For");
        String clientId=request.getRemoteAddr();
        String uuid= UUID.randomUUID().toString();
        log.info("【MDC】操作记录开始，requestId={}",uuid);
        log.info("【MDC】信息记录，clientId={},forward={}",clientId,forward);
        MDC.put(REQUEST_ID,uuid);//绑定MDC数据
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {//请求之后进行MDC的清除
       String uuid=MDC.get(REQUEST_ID);
       log.info("【MDC】的操作结束,requestId={}",uuid);
       MDC.remove(REQUEST_ID);

    }
}
