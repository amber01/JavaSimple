package com.mmail.service;

import com.github.pagehelper.PageInfo;
import com.mmall.vo.OrderVo;
import com.mmail.common.ServerResponse;

import java.util.Map;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:54 2018/9/13
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallback(Map<String,String> params);
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
    ServerResponse createOrder(Integer userId, Integer shippingId);
    ServerResponse<String> cancel (Integer userId, Long orderNo);
    ServerResponse getOrderCartProduct(Integer userId);
    ServerResponse<OrderVo> getOrderDetail(Integer userId, Long orderNo);
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);
    ServerResponse<PageInfo> manageList(int pageNum, int pageSize);
    ServerResponse<OrderVo> manageDetail(Long orderNo);
    ServerResponse<PageInfo>manageSearch(Long orderNo,int pageNum,int pageSize);
    ServerResponse manageSendGoods(Long orderNo);
}
