package com.example.demo.rhymeleafDemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")  //这里对应sources目录下的templates目录
public class templatesController {

    @RequestMapping("/hello")
    public  String hello(){
        return "personView";  //这里返回对应的HTML名称
    }
}
