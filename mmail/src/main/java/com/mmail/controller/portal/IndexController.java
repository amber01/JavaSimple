package com.mmail.controller.portal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/templates")  //这里对应sources目录下的templates目录
public class IndexController {

    //http://localhost:8080/templates/index
    @RequestMapping("index")
    public String index(){
        return "index";
    }


}