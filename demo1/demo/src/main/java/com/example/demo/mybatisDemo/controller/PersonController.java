package com.example.demo.mybatisDemo.controller;

import com.example.demo.mybatisDemo.mapper.PersonMapper;
import com.example.demo.mybatisDemo.service.PersonService;
import com.example.demo.showJsonDemo.model.ResponseDataInfo;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

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

    @Autowired
    PersonService personService;

    //********************myBatis的使用**********************//
    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public ResponseDataInfo findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "数据获取完成";
        response.data = personService.findAll(pageNum,pageSize);

        return response;
    }

    @RequestMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") Integer id)
    {
            ResponseDataInfo response = new ResponseDataInfo();
            response.code = 200;
            response.message = "数据删除成功";
            personService.deleteById(id);

            return personService.deleteById(id);
    }

    //RESTful风格，在浏览器中输入查询的id，然后再返回
    @RequestMapping("/findById/{id}")
    public ResponseDataInfo findById(@PathVariable("id") int id)
    {
        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "查询成功";
        response.data = personService.findById(id);

        return response;
    }
}
