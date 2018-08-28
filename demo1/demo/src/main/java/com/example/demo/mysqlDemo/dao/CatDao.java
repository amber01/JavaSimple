package com.example.demo.mysqlDemo.dao;

import com.example.demo.mysqlDemo.bean.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:40 2018/8/25
 * @ Description：@Repository注解，标注这是一个持久化操作对象,标注数据访问组件，即DAO组件(repository-------仓库; 贮藏室，容器。)
 *                JdbcTemplate:主要是用来操作数据库的，通过写SQL语句来操作。
 *                @Repository是spring框架中的注解；@Repository用于标注数据访问组件，即DAO组件；
 * @ Modified By：
 * @Version:
 */

@Repository
public class CatDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cat selectByCatName(String cateName){
        /**
         * 1. 定义一个Sql语句
         * 2. 定义一个RowMapper
         * 3. 执行查找方法.
         */

        String sql = "select *from cat where cat_name=?";
        RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<>(Cat.class);
        Cat cat = jdbcTemplate.queryForObject(sql, new Object[]{cateName}, rowMapper);//jdbcTemplate.queryForObject(sql, new Object[]{cateName}, rowMapper);

//        String sql = "select *from cat where cat_name=?";
//        RowMapper<Cat> rowMapper = new BeanPropertyRowMapper<>(Cat.class);
//        List<Cat> cat = (List)jdbcTemplate.queryForObject(sql, new Object[]{cateName}, rowMapper);
//
//
//        System.out.print("list"+ cat);

        return cat;
    }
}
