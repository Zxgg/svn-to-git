package com.isstech;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @Description web容器中进行部署
 * @Author yzp
 * @Date 2020/12/14 10:56
 */
public class IsstechServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(IsstechApplication.class);
    }
}
