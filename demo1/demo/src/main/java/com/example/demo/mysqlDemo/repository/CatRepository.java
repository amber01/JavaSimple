package com.example.demo.mysqlDemo.repository;

import com.example.demo.mysqlDemo.bean.Cat;
import org.springframework.data.repository.CrudRepository;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:14 2018/8/24
 * @ Description：Repository一般都是用interface，CrudRepository是用于数据持久化的操作
 * @ Modified By：
 * @Version:
 *
 * CrudRepository<Cat,Integer>，这里需要两个参数，Cat是实体类名称，Interge是实体类Cat的id的类型
 *
 */
public interface CatRepository extends CrudRepository<Cat,Integer> {


}
