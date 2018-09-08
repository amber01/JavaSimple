package com.mmail.controller.portal;

import com.mmail.common.Const;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;
import com.mmail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:13 2018/9/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     */
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody  //数据返回的时候，自动通过spring boot插件Jackson，让我的返回值自动序列化成json
    public  ServerResponse<User> login(String username, String password,HttpSession session){

        ServerResponse<User> response = iUserService.login(username,password);

        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do",method = RequestMethod.GET)
    @ResponseBody
    public  ServerResponse<String> login(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public  ServerResponse<String> register(User user){

        ServerResponse<String> response = iUserService.register(user);

        return response;
    }

    //校验email和用户名存在，虽然在注册的时候有检验，目的是防止恶意用户通过接口调用我们的注册接口，在注册的时候当输入完用户名点击
    //下一个input框的时候，要调用一下校验接口，然后在前台实时反馈
    //type:user type:email
    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    @RequestMapping(value = "get_user_info.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User>getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);

        if (user != null){
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
    }

    //把密码提示问题返回给客户
    public ServerResponse<String>forgetGetQuestion(String username){
        return null;
    }

}
