package com.example.demo.websocketDemo.socket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 虽然@Component默认是单例模式，当时spring boot还是会为每个WebSocket连接初始化一个bean，所以这里使用
 * 静态的set保存spring boot创建的bean--MyWebSocket
 */

@ServerEndpoint(value = "/websocket") //websocket连接点映射
@Component
public class MyWebSocket {

    //用来存储每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session; //当前会话的session
    /**
     * 成功建立连接调用的方法
     */
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this); //将当前这个MyWebSocket加入到set中
        //一旦建立连接成功就给客户端发一条消息
        this.session.getAsyncRemote().sendText("恭喜你成功连接上WebSocket-->当前在线人数为:"+webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this); //将当前这个MyWebSocket从set中移除
    }

    /**
     * 收到客户端消息后的调用方法
     */
    @OnMessage
    public  void onMessage(String message,Session session){
        //群发消息给客户端
        sendInfo(message);
    }

    /**
     * 发生错误时的调用
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 群发方法
     */
    public static void sendInfo(String message){
        for (MyWebSocket item:webSocketSet){
            //发送消息
            item.session.getAsyncRemote().sendText(message);
        }
    }
}
