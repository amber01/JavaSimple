package com.example.demo.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.filter.RequestContextFilter;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:23 2018/9/5
 * @ Description：jersey 是基于Java的一个轻量级RESTful风格的Web Services框架
 * @ Modified By：
 * @Version:
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig(){
        register(RequestContextFilter.class);
        //指定扫描的包路径
        packages("com.example.demo.testDemo.rest");
    }
}
