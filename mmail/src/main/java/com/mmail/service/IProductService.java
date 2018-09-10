package com.mmail.service;

import com.mmail.common.ServerResponse;
import com.mmail.pojo.Product;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String>setSaleStatus(Integer productId,Integer status);
}
