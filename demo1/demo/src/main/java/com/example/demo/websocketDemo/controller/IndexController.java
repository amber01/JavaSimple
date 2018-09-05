package com.example.demo.websocketDemo.controller;

import com.example.demo.websocketDemo.socket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/templates")  //这里对应sources目录下的templates目录
public class IndexController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @RequestMapping(value="/pushVideoListToWeb",method= RequestMethod.POST,consumes = "application/json")
    public @ResponseBody Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param) {
        Map<String,Object> result =new HashMap<String,Object>();

        MyWebSocket.sendInfo("有新客户呼入,sltAccountId");

        return result;
    }

}
