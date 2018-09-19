package com.example.demo.mybatisDemo.service;

import com.example.demo.mybatisDemo.entity.Menu;
import com.example.demo.mybatisDemo.mapper.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    public List<Menu> queryAll(){

        // 用户所能看到的菜单数据
        List<Menu> rootMenu = menuDao.queryAll();
        return rootMenu;
    }

}
