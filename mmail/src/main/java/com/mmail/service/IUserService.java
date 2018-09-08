package com.mmail.service;

import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;

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
}