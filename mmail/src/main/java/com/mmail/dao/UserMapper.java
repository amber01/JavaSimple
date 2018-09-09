package com.mmail.dao;

import com.mmail.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    //这个是针对哪一个key有变化的时候，针对性的更新这个key就可以了
    int updateByPrimaryKeySelective(User record);

    //这个是全部都重新更新一遍
    int updateByPrimaryKey(User record);

    //检测用户名密码对不对
    int checkUsername(@Param("username") String username);

    //验证邮箱是否存在
    int checkEmail(@Param("email") String email);

    //登录成功后返回user对象
    User selectLogin(@Param("username") String username, @Param("password") String password);

    //获取用户问题
    String selectQuestionByUsername(@Param("username") String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername (@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password, @Param("userId") Integer userId);

    int checkEmailByUserId(@Param("email") String email, @Param("userId") Integer userId);
}