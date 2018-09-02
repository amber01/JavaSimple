package com.example.demo.mybatisDemo.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {

    /**
     *  在@controller方法执行之前就会执行，如果返回为false就不会再往前执行任务了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.print("MyInterceptor-->preHandle");
        return true;  //通过返回值true和false来控制是否继续执行。true：继续执行。false：停止执行
    }

    /**
     *  在@controller方法执行之后，视图渲染之前执行
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.print("MyInterceptor-->postHandle");
    }

    /**
     *  在处理结束之后执行。最后一步。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.print("MyInterceptor-->afterCompletion");
    }
}
