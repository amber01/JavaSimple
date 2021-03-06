package com.example.demo.mybatisDemo.service;

import com.example.demo.mybatisDemo.entity.Person;
import com.example.demo.mybatisDemo.mapper.PersonMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    //到service层对应的方法中来添加需要添加的缓存
    @Override
    @Cacheable(value = "person")
    public List<Person> findById(int id)
    {
        System.out.println("2.(未使用缓存)-->进入到了findById,id="+id);
        return personMapper.findById(id);
    }

    @Override
    public int deleteById(int id)
    {
        return personMapper.deleteById(id);
    }

    @Override
    public List<Person> findAll1(String name,String email)
    {
        return personMapper.selectAll1(name,email);
    }

    @Override
    public void updatePerson(Person person)
    {
        personMapper.updatePerson(person);
    }

    @Override
    public List<Person> findAll2(String name,String email)
    {
        return personMapper.selectAll2(name,email);
    }

    @Override
    //根据传list的方式来查找
    public List<Person> findAll3(List<Integer>list)
    {
        return personMapper.selectAll3(list);
    }

    @Override
    public int save(Person person)
    {
        return personMapper.save(person);
    }

    //根据key来清除redis的缓存
    @Override
    @CacheEvict(value = "person")
    public void deleteCache(int id){
        System.out.println("缓存清除成功");
    }
}
