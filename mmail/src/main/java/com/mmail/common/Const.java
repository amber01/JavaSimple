package com.mmail.common;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 14:26 2018/9/8
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public class Const {
    //保存当前用户的session，作为session的key
    public static final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";
    public static final String USERNAME = "username";

    public static final String PASSWORD_SALT = "@#$%$$@@@DFERR121swseMD5";  //md5的混淆

    //通过内部的接口类，来把我们的常量分组。好处是没有枚举那么重，但是它又起到分组的作用，而且里面也是常量
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1; //管理员
    }
}
