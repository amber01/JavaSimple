package com.mmail.service.impl;

import com.mmail.common.Const;
import com.mmail.common.ServerResponse;
import com.mmail.dao.UserMapper;
import com.mmail.pojo.User;
import com.mmail.service.IUserService;
import com.mmail.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:23 2018/9/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@Service("iUserService")  //注入的属性名字iUserService
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        // 密码登录MD5
        String md5Password = MD5Util.MD5Encode(password,"utf8");;

        //检测用户名和密码对不对？
        User user = userMapper.selectLogin(username,md5Password);

        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }

        //密码正确，处理返回值密码。把密码置空，目的是不反悔给前端
        //user.setPassword(StringUtils.EMPTY);
        user.setPassword(null);

        return ServerResponse.createBySuccess("登录成功",user);
    }

    @Override
    public ServerResponse<String> register(User user){

        ServerResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

//        //注册的时候校验一下用户名和邮箱是否存在
//        int resultCount = userMapper.checkUsername(user.getUsername());
//        if (resultCount > 0){
//            return ServerResponse.createByErrorMessage("用户名已存在");
//        }
//
//        resultCount = userMapper.checkEmail(user.getEmail());
//        if (resultCount > 0){
//            return ServerResponse.createByErrorMessage("邮箱已存在");
//        }

        //设置默认角色为普通用户
        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5Encode(user.getPassword(),"utf8"));
        //最后把对象插入到数据库中
        int resultCount = userMapper.insert(user);
        if (resultCount == 0){ //插入失败的情况下
            return  ServerResponse.createByErrorMessage("注册失败");
        }

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<String> checkValid(String str,String type){
        if (StringUtils.isNoneBlank(type)){ //判断type是不是空的才开始校验
            if (Const.USERNAME.equals(type)){  //校验用户名
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }

            if (Const.EMAIL.equals(type)){ //校验邮箱
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0){
                    return ServerResponse.createByErrorMessage("邮箱已存在");
                }
            }

        }else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
