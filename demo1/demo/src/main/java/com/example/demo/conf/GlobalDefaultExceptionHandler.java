package com.example.demo.conf;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 1. 新建一个雷GloablDefaultExceptionHandler
 * 2. 在class注解上@ControllerAdvice
 * 3. 在方法上注解上@ExceptionHandler(value = Exception.class)，具体代码如下：
 * 4. 如果返回的是View，方法的返回值是ModelAndView
 * 5. 如果返回的还是String或者是Json数据，那么需要方法上添加@ResponseBody注解
 *
 *
 * @ Author     ：tank
 * @ Date       ：Created in 18:59 2018/8/29
 * @ Description：全局捕获异常
 * @ Modified By：
 * @Version:
 */

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public String defaultExceptionHandler(HttpServletRequest req,Exception e){
        e.getMessage();

        return e.getMessage();//"对不起，服务器繁忙，请稍后再试 ";
    }
}
