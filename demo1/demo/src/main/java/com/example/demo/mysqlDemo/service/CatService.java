package com.example.demo.mysqlDemo.service;
import org.springframework.stereotype.Repository;
import com.example.demo.mysqlDemo.bean.Cat;
import com.example.demo.mysqlDemo.dao.CatDao;
import com.example.demo.mysqlDemo.repository.CatRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:01 2018/8/24
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@Service
public class CatService {

    @Resource
    private CatRepository catRepository;

    @Resource
    private CatDao catDao;

    /***
     *  save,update,dalete 方法需要绑定事物
     *
     * @使用@Transactional进行事务的绑定
     *
     */

    //保存数据
    @Transactional  //事务绑定
    public void save(Cat cat){
        catRepository.save(cat);
    }

    //删除数据
    @Transactional
    public void  delete(int id){
        catRepository.deleteById(id);
    }

    //查询数据
    public Iterable<Cat> getAll(){
        return catRepository.findAll();
    }

    //删除全部数据
    @Transactional  //事务绑定
    public void deleteAll(){
        catRepository.deleteAll();
    }

    //通过jdbcTemplate技术。输入名称来返回对应数据
    public Cat selectByCatName(String cateName){
        return catDao.selectByCatName(cateName);
    }
}
