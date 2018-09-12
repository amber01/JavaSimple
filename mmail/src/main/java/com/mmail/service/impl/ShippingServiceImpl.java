package com.mmail.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mmail.common.ServerResponse;
import com.mmail.dao.ShippingMapper;
import com.mmail.pojo.Shipping;
import com.mmail.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("iShippingService")
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;

    //添加地址
    public ServerResponse add(Integer userId, Shipping shipping){
        if (shipping == null){
            return ServerResponse.createByErrorMessage("参数错误");
        }
        shipping.setUserId(userId);
        int resultCount = shippingMapper.insert(shipping);
        if (resultCount > 0){
            Map resultMap = new HashMap();
            resultMap.put("shippingId",shipping.getId());
            return ServerResponse.createBySuccess("添加成功",resultMap);
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    //删除地址
    public ServerResponse<String> delete(Integer userId, Integer shippingId){

        //这样删有横向越权的漏洞，只要大家都是登录的情况下都可以删除，我随便写一个shippingid就可以删除别人的
        //int resultCount = shippingMapper.deleteByPrimaryKey(shippingId)
        int resultCount = shippingMapper.deleteByShippingUserId(userId,shippingId);
        if (resultCount > 0){
            return ServerResponse.createBySuccess("删除地址成功");
        }
        return ServerResponse.createByErrorMessage("删除地址失败");
    }

    public ServerResponse update(Integer userId, Shipping shipping){
        //更新也有越权的问题
        shipping.setUserId(userId); //更新的时候要指定userID的，防止越权。如果用户传了别人的userID就可以更新别人的了
        int resultCount = shippingMapper.updateByShipping(shipping);
        if (resultCount > 0){
            return ServerResponse.createBySuccess("更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }

    public ServerResponse<Shipping> select(Integer userId, Integer shippingId){
        Shipping shipping = shippingMapper.selectByShippingUserId(userId, shippingId);
        if (shipping == null){
            return ServerResponse.createByErrorMessage("查询结果为空");
        }
        return ServerResponse.createBySuccess("查询地址成功",shipping);
        //也要判断越权问题
    }

    public ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping> shippingList = shippingMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(shippingList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
