package com.maker.actuator;

import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * info构造器
 * */
@Component
public class InfoCreator implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {

        builder.withDetail("key1","value1");//添加info信息
        builder.withDetail("key2","value2");
    }
}
