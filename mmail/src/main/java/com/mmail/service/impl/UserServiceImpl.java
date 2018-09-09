package com.mmail.service.impl;

import com.mmail.common.Const;
import com.mmail.common.ServerResponse;
import com.mmail.common.TokenCache;
import com.mmail.dao.UserMapper;
import com.mmail.pojo.User;
import com.mmail.service.IUserService;
import com.mmail.util.MD5Util;
import org.apache.catalina.Server;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.ServerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.UUID;

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
        String md5Password = MD5Util.MD5Encode(password);

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
        user.setPassword(MD5Util.MD5Encode(user.getPassword()));
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

    @Override
    public ServerResponse<String> selectQuestion(String username) {

        //获取用户名数据库是否存在
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if (validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNoneBlank(question)){
            return ServerResponse.createBySuccess("查询成功",question);
        }

        return ServerResponse.createByErrorMessage("找回密码的问题是空得");
    }

    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {

        int resultCount = userMapper.checkAnswer(username, question, answer);
        if (resultCount > 0){
            //说明问题和问题答案是这个用户的，并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken); //把token保存到cache中
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("问题答案不对");
    }

    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，需要token");
        }

        //获取用户名数据库是否存在
        ServerResponse validResponse = this.checkValid(username,Const.USERNAME);
        if (validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        //从cache中获取该用户的token。比较用户传的token是不是对的
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或者过期");
        }

        if (StringUtils.equals(forgetToken,token)){//如果两个token相等就进行更新密码操作
            String md5Password = MD5Util.MD5Encode(passwordNew);
            int resultCount = userMapper.updatePasswordByUsername(username, md5Password);
            if (resultCount > 0){
                return ServerResponse.createBySuccessMessage("密码修改成功");
            }
        }else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取token");
        }
        return ServerResponse.createByErrorMessage("密码修改失败");
    }

    @Override
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew,User user) {
        //防止横向越权，要校验一下这个用户的旧密码，一定要指定是这个用户，因为我们一会查询一个count(1)，如果不指定id，那么结果就是true啦，count>0；
        int resultCount = userMapper.checkPassword(MD5Util.MD5Encode(passwordOld),user.getId());
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        //开始设置新密码
        user.setPassword(MD5Util.MD5Encode(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return ServerResponse.createBySuccessMessage("密码更新成功");
        }
        return ServerResponse.createByErrorMessage("密码更新失败");
    }

    @Override
    public ServerResponse<User> update_information(User user) {
        //username是不能被更新的
        //email也要进行校验，校验新的email是不是已经存在，并且存在的email如果相同的话，不能是我们当前的这个用户
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if (resultCount == 1){
            ServerResponse.createByErrorMessage("邮箱已存在");
        }

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        if (StringUtils.isNoneBlank(user.getPassword())){
            return ServerResponse.createByErrorMessage("个人信息更新失败");
        }

        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if (updateCount > 0){
            return ServerResponse.createBySuccess("个人信息更新成功",user);
        }

        return ServerResponse.createByErrorMessage("个人信息更新失败");
    }

    @Override
    public ServerResponse<User> getInformation(Integer userId) {
        //先从表中查一下有没有这个用户存在
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到当前用户");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("查询成功",user);
    }
}
