package com.isstech.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description 配置
 * @Author yzp
 * @Date 2020/12/17 15:40
 */
@Component
@Data
public class Config {

    @Autowired
    private Environment env;
    //websocket 连接地址
    public static String WEBSOCKET_URL;
    public static String PROXY_VCM;


    @PostConstruct
    public void redaConfig() {
        WEBSOCKET_URL = env.getProperty("websocket-url");
        PROXY_VCM = env.getProperty("proxy_vcm");
    }

}
