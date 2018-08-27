package com.example.demo.mybatisDemo.controller;

import com.example.demo.mybatisDemo.mapper.PersonMapper;
import com.example.demo.mybatisDemo.service.PersonService;
import com.example.demo.showJsonDemo.model.ResponseDataInfo;
import org.hibernate.annotations.Source;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:11 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@RestController
@RequestMapping("userInfo")
public class PersonController {

    @Source
    PersonService personService;

    //********************myBatis的使用**********************//
    @RequestMapping("/findAll")
    public ResponseDataInfo findAll(){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "数据获取完成";
        response.data = personService.findAll();

        return  response;
    }
}
