package com.example.demo.testDemo.rest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:34 2018/9/5
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Path("/userTest")
@Component
public class UserResource {

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object>getUser(){

        Map<String,Object>map = new HashMap<String,Object>();
        map.put("id","1000");
        map.put("name","liuKai");
        map.put("age","25");
        return map;
    }
}
