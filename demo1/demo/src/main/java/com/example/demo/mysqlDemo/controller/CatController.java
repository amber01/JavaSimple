package com.example.demo.mysqlDemo.controller;

import com.example.demo.mysqlDemo.bean.Cat;
import com.example.demo.mysqlDemo.service.CatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
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

    @RequestMapping(value = "register",method = RequestMethod.POST)
    public String register(@RequestParam("name")String name, @RequestParam("age")int age)
    {
        Cat cat = new Cat();
        cat.setCatAge(age);
        cat.setCatName(name);
        catService.save(cat);

        return "注册成功";
    }
    @RequestMapping("/delete")
    public String delete(){
        catService.delete(1);
        return "delete ok.";
    }

    @RequestMapping("/getAll")
    public Iterable<Cat> getAll(){
        return catService.getAll();
    }

    @RequestMapping("/deleteAll")
    public String deleteAll(){
        catService.deleteAll();
        return "delete all ok.";
    }
}
