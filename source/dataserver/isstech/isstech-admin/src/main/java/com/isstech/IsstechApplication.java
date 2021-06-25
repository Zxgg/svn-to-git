package com.isstech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Description 启动程序
 * @Author yzp
 * @Date 2020/12/1 14:33
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class IsstechApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(IsstechApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  isstech 启动成功   ლ(´ڡ`ლ)ﾞ  \n" );
    }
}
