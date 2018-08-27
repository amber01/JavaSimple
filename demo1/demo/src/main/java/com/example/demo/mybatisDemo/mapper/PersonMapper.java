package com.example.demo.mybatisDemo.mapper;

import com.example.demo.mybatisDemo.entity.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 11:00 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Mapper
public interface PersonMapper {

    public List<Person> findAll();

    public List<Person> findById(int id);

    public int deleteById(int id);

}
