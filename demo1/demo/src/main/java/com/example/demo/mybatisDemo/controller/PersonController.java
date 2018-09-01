package com.example.demo.mybatisDemo.controller;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.mapper.PersonMapper;
import com.example.demo.mybatisDemo.service.PersonService;
import com.example.demo.showJsonDemo.model.ResponseDataInfo;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    //根据条件查询
    @RequestMapping("/findAll1")
    public ResponseDataInfo findAll1(@Param("name")String name, @Param("email")String email){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "查询成功";
        response.data = personService.findAll1(name,email);

        return response;
    }

    //根据条件查询
    @RequestMapping("/findAll2")
    public ResponseDataInfo findAl2(@Param("name")String name, @Param("email")String email){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "查询成功";
        response.data = personService.findAll1(name,email);

        return response;
    }

    //根据传list的方式来查找
    @RequestMapping("/findAll3")
    public ResponseDataInfo findAll3(Integer[] id){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "查询成功";
        response.data = personService.findAll3(Arrays.asList(id));

        return response;
    }

    //http://127.0.0.1:8080/userInfo/findAll3?id=2,3,5
    @RequestMapping("/updatePerson")
    public ResponseDataInfo updatePerson(Person person){

        System.out.print("update finish!");
        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "查询成功";
        response.data = person;
        personService.updatePerson(person);

        return response;
    }

}
