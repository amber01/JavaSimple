package com.mmail.dao;

import com.mmail.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    Order selectByUserIdAndOrderNO(@Param("orderNo") Long orderNo, @Param("userId") Integer userId);

    Order selectByOrderNo(@Param("orderNo") Long orderNo);

    List<Order>selectByUserId(Integer userId);

    List<Order>selectAllOrder();
}