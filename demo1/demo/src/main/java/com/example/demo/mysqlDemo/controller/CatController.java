package com.example.demo.mysqlDemo.controller;

import com.example.demo.mysqlDemo.bean.Cat;
import com.example.demo.mysqlDemo.service.CatService;
import com.example.demo.showJsonDemo.model.ResponseDataInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:00 2018/8/24
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    private CatService catService;

    @RequestMapping("/save")
    public String save(){
        //catService.save(new Cat(1,"mayday",17));
        Cat cat = new Cat();
        cat.setCatAge(10);
        cat.setCatName("mayday");
        catService.save(cat);

        return "save ok.";
    }

//    @RequestMapping(value = "register",method = RequestMethod.POST)
//    public String register(@RequestParam("name")String name, @RequestParam("age")int age)
//    {
//        Cat cat = new Cat();
//        cat.setCatAge(age);
//        cat.setCatName(name);
//        catService.save(cat);
//        System.out.println("testRequestMapping");
//        return "注册成功";
//    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public ResponseDataInfo register(@RequestParam("name")String name, @RequestParam("age")int age) throws IOException
    {
        Cat cat = new Cat();
        cat.setCatAge(age);
        cat.setCatName(name);
        catService.save(cat);
        System.out.println("testRequestMapping");

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "注册成功";
        response.data = cat;
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonString = objectMapper.writeValueAsString(response);

        return response;
    }

    @RequestMapping("/delete")
    public String delete(){
        catService.delete(1);
        return "delete ok.";
    }

//    @RequestMapping("/getAll")
//    public Iterable<Cat> getAll(){
//        return catService.getAll();
//    }

    @RequestMapping("/getAll")
    public ResponseDataInfo getAll(){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "数据获取完成";
        response.data = catService.getAll();

        return response;
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        catService.deleteAll();
        return "delete all ok.";
    }

    //通过resfut风格来查找并返回数据
    @RequestMapping("/selectByCatName")
    public ResponseDataInfo selectByCatName (String cateName){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "数据获取完成";
        response.data = catService.selectByCatName(cateName);

        return  response;
    }
}
