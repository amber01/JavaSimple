package com.example.demo.mybatisDemo.controller;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.service.PersonService;
import com.example.demo.showJsonDemo.model.ResponseDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;



import java.util.Arrays;
import java.util.logging.Logger;

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

    private Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    PersonService personService;

    //********************myBatis的使用**********************//
    @RequestMapping(value = "/findAll/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public ResponseDataInfo findAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {

//
//        logger.trace("I am trace log.");
//        logger.debug("I am debug log.");
        logger.info("I am info log.");
//        logger.warn("I am warn log.");
  //      logger.error("I am error log.{}", new Date());

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

        System.out.println("1. 调用controller方法，开始获取数据,id="+id);
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
    //当要更新数据库里面的数据的时候，需要清理一下redis的缓存数据，不然访问还是访问到redis中的数据
    @CachePut(value = "person")
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

    //http://127.0.0.1:8080/userInfo/findAll3?id=2,3,5
    @RequestMapping("/save")
    public ResponseDataInfo save(Person person){

        System.out.print("update finish!");
        ResponseDataInfo response = new ResponseDataInfo();
        response.code = personService.save(person);;
        response.message = "查询成功";
        response.data = person;
        return response;
    }

    //http://127.0.0.1:8080/userInfo/deleteCache?id=5
    //根据key来清除redis的缓存
    @RequestMapping("/deleteCache")
    public ResponseDataInfo deleteCache(int id){

        ResponseDataInfo response = new ResponseDataInfo();
        response.code = 200;
        response.message = "redis缓存清除成功";
        response.data = null;
        personService.deleteCache(id);
        return response;
    }
}
