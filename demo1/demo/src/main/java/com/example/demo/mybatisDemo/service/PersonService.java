package com.example.demo.mybatisDemo.service;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.mapper.PersonMapper;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:11 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Service
public class PersonService {

    @Source
    private PersonMapper personMapper;

    public List<Person> findAll()
    {
        return personMapper.findAll();
    }

    public List<Person> findById(int id)
    {
        return personMapper.findById(id);
    }

    public int deleteById(int id)
    {
        return deleteById(id);
    }
}
