package com.example.demo.mybatisDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 10:58 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Person {

    private int id;
    private String name;
    private int age;
    private String email;
    private Date createDate;

}
