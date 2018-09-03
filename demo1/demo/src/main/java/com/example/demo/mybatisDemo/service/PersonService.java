package com.example.demo.mybatisDemo.service;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.mapper.PersonMapper;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:11 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */


public interface PersonService {

    public List<Person> findAll(int pageNum, int pageSize);

    public List<Person> findById(int id);

    public int deleteById(int id);

    public List<Person> findAll1(String name,String email);

    public List<Person> findAll2(String name,String email);

    public void updatePerson(Person person);

    //根据传list的方式来查找
    public List<Person> findAll3(List<Integer>list);

    public int save(Person person);
}