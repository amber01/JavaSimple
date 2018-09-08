package com.mmail.dao;

import com.mmail.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //检测用户名密码对不对
    int checkUsername(@Param("username") String username);

    //验证邮箱是否存在
    int checkEmail(@Param("email") String email);

    //登录成功后返回user对象
    User selectLogin(@Param("username") String username, @Param("password") String password);
}