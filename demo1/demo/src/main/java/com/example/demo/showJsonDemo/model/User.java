package com.example.demo.showJsonDemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data  //自动生成get和set方法。前提是要导入lombok插件
@NoArgsConstructor  //构造无惨
@AllArgsConstructor //构造有参

public class User {

    private  int id;
    private String username;
    private String password;

    /***
     * 如果我们不想返回createdDate这个字段，可以让设置序列化属性
     * serialize:是否需要序列化属性
     */
    //@JSONField(serialize = false)
    private Date createdDate;

}
