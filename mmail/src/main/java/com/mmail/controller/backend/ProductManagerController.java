package com.mmail.controller.backend;

import com.mmail.common.Const;
import com.mmail.common.ResponseCode;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.Product;
import com.mmail.pojo.User;
import com.mmail.service.IProductService;
import com.mmail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("manager/product")
public class ProductManagerController {

    @Autowired
    IUserService iUserService;
    IProductService iProductService;

    //保存商品
    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    public ServerResponse productSave(HttpSession session, Product product){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        //判断是否是管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            //增加产品的业务逻辑
            return iProductService.saveOrUpdateProduct(product);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    //更新产品销售状态
    @RequestMapping(value = "set_sale_status.do",method = RequestMethod.POST)
    public ServerResponse setSaleStatus(HttpSession session, Integer productId,Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        //判断是否是管理员权限
        if (iUserService.checkAdminRole(user).isSuccess()){
            //增加产品的业务逻辑
            return iProductService.setSaleStatus(productId,status);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }
}