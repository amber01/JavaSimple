package com.example.demo.showJsonDemo.controller;


import com.example.demo.showJsonDemo.model.Login;
import com.example.demo.showJsonDemo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("user")
public class UserController {

    /**
     * Spring Boot默认使用的json解析框架是jackson，已经帮我们做了
     * @return
     */
    @RequestMapping("getUser")
    public User getUser(){
        User user = new User(10080,"mayday","123456",new Date());

        return user;
    }


    @RequestMapping("loginInfo")
    public Login loginInfo(){
        Login login = new Login();

        login.setLoginAddress("杭州市滨江区");
        login.setLoginCity("杭州");
        login.setLoginDate("2018年08月23日11:49:13");

        return login;
    }
}
