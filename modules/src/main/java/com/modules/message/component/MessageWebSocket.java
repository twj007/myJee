package com.modules.message.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 **@project: myJee
 **@description:
 **@Author: twj
 **@Date: 2019/12/31
 * 现在替换为 stomp协议，不需要实现socket
 *
 **/
@Component
@ServerEndpoint("/websocket/{userId}")
@Slf4j
public class MessageWebSocket {

    private Session session;

    private static CopyOnWriteArraySet<MessageWebSocket> webSockets =new CopyOnWriteArraySet<>();
    private static Map<String,Session> sessionPool = new HashMap<String,Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId")String userId) {
        try {
            this.session = session;
            webSockets.add(this);
            sessionPool.put(userId, session);
            log.info("【web socket消息】有新的连接，总数为:"+webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnClose
    public void onClose() {
        try {
            webSockets.remove(this);
            log.info("【web socket消息】连接断开，总数为:"+webSockets.size());
        } catch (Exception e) {
        }
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【web socket消息】收到客户端消息:"+message);
    }

    /*** 此为广播消息
     *
     * @param message
     */
    public void sendAllMessage(String message) {
        log.info("【websocket消息】广播消息:"+message);
        for(MessageWebSocket webSocket : webSockets) {
            try {
                if(webSocket.session.isOpen()) {
                    webSocket.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** 此为单点消息
     *
     * @param userId
     * @param message
     */
    public void sendOneMessage(String userId, String message) {
        Session session = sessionPool.get(userId);
        doSendBySession(session, message);
    }

    /** 此为单点消息(多人)
     *
     * @param userIds
     * @param message
     */
    public void sendMoreMessage(String[] userIds, String message) {
        for(String userId : userIds) {
            Session session = sessionPool.get(userId);
            doSendBySession(session, message);
        }

    }

    private void doSendBySession(Session session, String message){
        if (session != null&&session.isOpen()) {
            try {
                log.info("【web socket消息】 单点消息:"+message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
