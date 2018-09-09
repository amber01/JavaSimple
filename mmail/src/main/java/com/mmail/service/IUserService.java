package com.mmail.service;

import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:20 2018/9/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkValid(String str,String type);
    ServerResponse<String> selectQuestion(String username);
    ServerResponse<String> checkAnswer(String username,String question,String answer);
    ServerResponse<String> forgetRestPassword (String username,String passwordNew,String forgetToken);
    ServerResponse<String> resetPassword(String passwordOld, String passwordNew,User user);
    ServerResponse<User> update_information(User user);
    ServerResponse<User> getInformation(Integer userId);
}