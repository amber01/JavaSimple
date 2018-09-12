package com.mmail.service;

import com.github.pagehelper.PageInfo;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.Shipping;


public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);
    ServerResponse delete(Integer userId, Integer shippingId);
    ServerResponse update(Integer userId, Shipping shipping);
    ServerResponse select(Integer userId, Integer shippingId);
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
