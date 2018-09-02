package com.example.demo.mybatisDemo.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动加载数据CommandLineRunner
 * 在实际应用中，我们会有在项目服务启动的时候去加载一些数据或做添加一些功能等操作。
 */

@Component
public class MyStartRunner1 implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("MyStartRunner1");

        //这里打印项目启动的时候main方法传过来的args参数
        for(String str:args){
            System.out.println(args);
        }
    }
}
