package com.mmail.controller.portal;

import com.mmail.common.Const;
import com.mmail.common.ResponseCode;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;
import com.mmail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>forgetGetQuestion(String username){

        return iUserService.selectQuestion(username);
    }

    //修改密码的时候校验该用户的问题答案是不是正确的。如果是正确的就返回一个token，然后用户再根据这个token去设置新密码
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>forgetCheckAnswer(String username,String question,String answer){

        return iUserService.checkAnswer(username,question,answer);
    }

    //找回密码，并修改该密码
    @RequestMapping(value = "forget_rest_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword (String username,String passwordNew,String forgetToken){
        return iUserService.forgetRestPassword(username,passwordNew,forgetToken);
    }

    //登录状态的修改密码
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session,String passwordOld, String passwordNew){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return  iUserService.resetPassword(passwordOld,passwordNew,user);
    }

    //更新用户信息
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> update_information(HttpSession session,User user){
        //这里传session的目的是，判断用户有没有登录，只有登录的状态下才允许更新
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        //用户修改的信息，不会有user id的。所以需要从session中获取到user id
        user.setId(currentUser.getId());
        ServerResponse response = iUserService.update_information(user);
        if (response.isSuccess()){//更新成功之后还要更新session
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return iUserService.update_information(user);
    }

    //获取用户信息
    @RequestMapping(value = "get_inforomation.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User>get_inforomation(HttpSession session){

        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"需要未登录，需要强制登录");
        }
        return iUserService.getInformation(currentUser.getId());
    }
}
