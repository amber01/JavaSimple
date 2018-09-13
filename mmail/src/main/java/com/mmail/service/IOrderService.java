package com.mmail.service;

import com.mmail.common.ServerResponse;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:54 2018/9/13
 * @ Description：
 * @ Modified By：
 * @Version:
 */
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
}
