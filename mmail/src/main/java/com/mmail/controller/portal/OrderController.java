package com.mmail.controller.portal;

import com.mmail.common.Const;
import com.mmail.common.ResponseCode;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 17:50 2018/9/13
 * @ Description：处理订单信息
 * @ Modified By：
 * @Version:
 */

@RestController
@RequestMapping("order")
public class OrderController {


    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }

        //拿到上线文
        String path = request.getSession().getServletContext().getRealPath("upload");

        return null;
    }
}
