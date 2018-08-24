package com.example.demo.mysqlDemo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 16:46 2018/8/24
 * @ Description：创建一个实体类
 * @ Modified By：
 * @Version:
 * 如何持久化呢？
 * 1. 使用@Entity进行实体类的持久化操作，当JPA检测到我们的实体类当中有@Entity注解的时候，就会栽数据库中生成对应的表结构信息。
 *
 * 如何制定主键以及主键的生成策略？
 * 1. 使用@Id来指定主键
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat {

    /***
     * 使用@Id指定主键
     * 使用@代码@GeneratedValue(strategy = GenerationType.AUTO)指定主键的生成策略，mysql默认是自增长
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; //主键

    private String catName; //姓名

    private  int catAge; //年龄

}
