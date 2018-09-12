package com.mmail.service;

import com.mmail.common.ServerResponse;
import com.mmail.vo.CartVo;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 14:14 2018/9/12
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> update(Integer userId, Integer productId, Integer count);
    ServerResponse<CartVo> delete(Integer userId, String productIds);
    ServerResponse<CartVo> list(Integer userId);
}
