package com.isstech.isstechadmin.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 数据同步配置
 * @Author dyf
 * @Date 2021/01/29 13:45
 */
@Configuration
public class DataSynchronizationConfig {

    /**同步开关*/
    @Value("${dataSynchronization.isSwitch}")
    public static boolean isSwitch;

    /**同步上级ip*/
    @Value("${dataSynchronization.synIp}")
    public static String synIp;

    /**本部门唯一code*/
    @Value("${dataSynchronization.synCode}")
    public static String synCode;

}
