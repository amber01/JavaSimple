package com.mmail.controller.portal;

import com.github.pagehelper.PageInfo;
import com.mmail.common.ServerResponse;
import com.mmail.service.IProductService;
import com.mmail.vo.ProductDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 11:40 2018/9/12
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService iProductService;

    //用户获取商品详情
    @RequestMapping("detail.do")
    public ServerResponse<ProductDetailVO>detail(Integer productId){
        return iProductService.getProductDetail(productId);
    }

    //required = false 表示keyword和categoryId不是必传的。
    //用户获取商品列表
    @RequestMapping("list.do")
    public ServerResponse<PageInfo>list(@RequestParam(value = "keyword",required = false)String keyword,
                                        @RequestParam(value = "categoryId",required = false)Integer categoryId,
                                        @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "20") int pageSize,
                                        @RequestParam(value = "orderBy",defaultValue = "") String orderBy){

        return iProductService.getProductByKeywordCategory(keyword, categoryId, pageNum, pageSize, orderBy);
    }
}
