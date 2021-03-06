package com.example.demo.mybatisDemo.mapper;

import com.example.demo.mybatisDemo.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 11:00 2018/8/27
 * @ Description：
 * @ Modified By：
 * @Version:
 */

public interface PersonMapper {

    /**
     * 除了通过在sources文件下创建一个personMapper.xml作为映射，
     * 还可以通过@Select方式来注释(annotation)。
     */
    //@Select("select *from person")
    public List<Person> selectAll();

    //@Select("select * from person where id=#{id}")
    public List<Person> findById(int id);

    //@Select("delete from person where id=#{id}")
    public int deleteById(int id);

    public List<Person> selectAll1(String name, String email);

    public List<Person> selectAll2(String name,String email);

    public void updatePerson(Person person);

    //根据传list的方式来查找
    public List<Person> selectAll3(List<Integer>list);

    public int save(Person person);
}