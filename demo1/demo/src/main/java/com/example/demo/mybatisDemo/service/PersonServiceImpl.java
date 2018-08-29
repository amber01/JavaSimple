package com.example.demo.mybatisDemo.service;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.mapper.PersonMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:38 2018/8/29
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@Service
@Primary
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public List<Person> findAll(int pageNum, int pageSize)
    {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum,pageSize);
        return personMapper.selectAll();
    }

    @Override
    public List<Person> findById(int id)
    {
        return personMapper.findById(id);
    }

    @Override
    public int deleteById(int id)
    {
        return personMapper.deleteById(id);
    }

}
