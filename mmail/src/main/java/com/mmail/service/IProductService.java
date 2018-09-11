package com.mmail.service;

import com.github.pagehelper.PageInfo;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.Product;
import com.mmail.vo.ProductDetailVO;

public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse<String>setSaleStatus(Integer productId,Integer status);
    ServerResponse<ProductDetailVO> managerProductDetail(Integer productId);
    ServerResponse<PageInfo> getProductList(int pageNum, int pageSize);
    ServerResponse<PageInfo>searechProduct(String productName,Integer productId,int pageNum,int pageSize);
}
