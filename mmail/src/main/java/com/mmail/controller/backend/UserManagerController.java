package com.mmail.controller.backend;

import com.mmail.common.Const;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;
import com.mmail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("manager/user")
public class UserManagerController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse<User>login(String username, String password, HttpSession session){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()){
            User user = response.getData();

            if (user.getRole() == Const.Role.ROLE_ADMIN){ //管理员账户
                //保存用户的session
                session.setAttribute(Const.CURRENT_USER,user);
                return ServerResponse.createBySuccess("登录成功",user);
            }
            return ServerResponse.createByErrorMessage("非管理员账户无权限");
        }
        return response;
    }
}
