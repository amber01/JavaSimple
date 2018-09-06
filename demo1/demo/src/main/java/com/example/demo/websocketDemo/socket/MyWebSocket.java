package com.example.demo.websocketDemo.socket;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 虽然@Component默认是单例模式，当时spring boot还是会为每个WebSocket连接初始化一个bean，所以这里使用
 * 静态的set保存spring boot创建的bean--MyWebSocket
 */
@ServerEndpoint(value = "/websocket/{nickname}") //websocket连接点映射
@Component
public class MyWebSocket {

    private String nickname;
    //用来存储每个客户端对应的MyWebSocket对象
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    //用来记录sessionId和该session之间的绑定关系
    private static Map<String,Session> map = new HashMap<String,Session>();
    private Session session; //当前会话的session

    public MyWebSocket(){
        System.out.println("开始初始化此控制器");
    }

    /**
     * 成功建立连接调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("nickname") String nickname){
        this.session = session;
        this.nickname = nickname;  //存储客户端输入的用户昵称
        webSocketSet.add(this); //将当前这个MyWebSocket加入到set中
        //一旦建立连接成功就给客户端发一条消息
        this.session.getAsyncRemote().sendText(nickname+"上线了,我的频道号:"+session.getId() + "-->当前在线人数为:"+webSocketSet.size());
        map.put(session.getId(),session);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this); //将当前这个MyWebSocket从set中移除
        map.remove(session.getId());
    }

    /**
     * 收到客户端消息后的调用方法
     */
    @OnMessage
    public  void onMessage(String message,Session session) {
        //message 不是普通的string，而是我们定义的SocketMsg json字符串。
        try {
            //message是一个json字符串 {"msg":"AASDD","toUser":"","type":0}
            //转json对象。将传递的JSON字符串转换成SocketMsg对象。
            //map json to SocketMsg
            SocketMsg socketMsg = new ObjectMapper().readValue(message,SocketMsg.class);

            if (socketMsg.getType() == 1){ //单聊
                //单聊：需要找到发送者和接受者即可
                socketMsg.setFromUser(session.getId()); //发送者
                //socketMsg.getToUser(toUser); //这个是由客户端进行设置
                //map里面存储的是键值对，key:session的id  value：Session对象
                Session fromSession = map.get((socketMsg.getFromUser())); //根据用户的sessionid取出Session对象
                Session toSession = map.get(socketMsg.getToUser());

                if (toSession != null){
                    //发送者和接受者都显示当前发送的消息
                    fromSession.getAsyncRemote().sendText(nickname+":"+socketMsg.getMsg());
                    toSession.getAsyncRemote().sendText(nickname+":"+socketMsg.getMsg());
                }else{
                    fromSession.getAsyncRemote().sendText("系统消息：对方不在线或你你输入的频道号有误");
                }

            }else { //群聊
                //群发消息给客户端
                sendInfo(socketMsg,this.nickname);
            }

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    public static void sendInfo(SocketMsg socketMsg,String nickname){
        for (MyWebSocket item:webSocketSet){
            //发送消息
            item.session.getAsyncRemote().sendText(nickname + ":" + socketMsg.getMsg());
        }
    }
}
