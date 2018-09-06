package com.example.demo.websocketDemo.socket;

import lombok.Data;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 13:25 2018/9/6
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@Data
public class SocketMsg {
    private int type; //聊天类型：0单聊；1群聊；
    private String fromUser; //发送者
    private String toUser; //接受者，session.getId();
    private String msg;  //消息
}
