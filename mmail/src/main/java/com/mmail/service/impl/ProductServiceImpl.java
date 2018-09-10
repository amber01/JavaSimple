package com.mmail.service.impl;

import com.mmail.common.ResponseCode;
import com.mmail.common.ServerResponse;
import com.mmail.dao.ProductMapper;
import com.mmail.pojo.Product;
import com.mmail.service.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iProductService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductMapper productMapper;

    public ServerResponse saveOrUpdateProduct(Product product){
        if (product != null){
            //判断子图是不是空的
            if (StringUtils.isNoneBlank(product.getSubImages())){
                //取第一个子图，赋值给主图用来作为封面
                //开始分割
                //将字符串转为数组
                String[] subImageArray = product.getSubImages().split(",");
                if (subImageArray.length > 0){
                    product.setMainImage(subImageArray[0]);
                }
            }

            //更新的话product肯定不能为空得
            //更新操作
            if (product.getId() != null){
                int resultCont = productMapper.updateByPrimaryKeySelective(product);
                if (resultCont > 0){
                    return ServerResponse.createBySuccess("更新产品成功");
                }
                return ServerResponse.createByErrorMessage("更新产品失败");
            }else {
                //新增
                int resultCont = productMapper.insert(product);
                if (resultCont > 0) {
                    return ServerResponse.createBySuccess("新增产品成功");
                }
                return ServerResponse.createByErrorMessage("新增产品失败");
            }
        }
        return ServerResponse.createByErrorMessage("新增或更新产品参数不正确");
    }


    public ServerResponse<String>setSaleStatus(Integer productId,Integer status){
        if (productId == null || status == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Product product = new Product();
        product.setId(productId);
        product.setStatus(status);

        int resultCount = productMapper.updateByPrimaryKeySelective(product);
        if (resultCount > 0){
            ServerResponse.createBySuccessMessage("修改产品销售状态成功");
        }

        return ServerResponse.createByErrorMessage("修改产品销售状态失败");
    }
}
