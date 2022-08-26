package com.maker.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCreator implements HealthIndicator {

    @Override
    public Health health() {
        int error_code=100;
        if(error_code>0){//服务异常的条件
            return Health.down().withDetail("errorCode",error_code).withException(new Exception("服务故障")).build();//返回状态异常，并设置异常信息
        }
        return Health.up().build();
    }
}
