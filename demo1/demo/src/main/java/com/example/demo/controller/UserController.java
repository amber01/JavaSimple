package com.example.demo.controller;


import com.example.demo.model.Login;
import com.example.demo.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping("getUser")
    public User getUser(){
        User user = new User(10080,"mayday","123456");

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
