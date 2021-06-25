package com.isstech.common.task.util;

import com.isstech.common.utils.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.ApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.NotYetConnectedException;

/**
 * @author lichong<br />
 * @Description: <br/>
 * @date 2019/9/10  9:45<br/>
 */
@Slf4j
public class MsgWebSocketClient extends WebSocketClient {

    public MsgWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        // TODO Auto-generated method stub
        log.info("[websocket] connection success！");
    }

    @Override
    public void onMessage(String message) {
        log.info("[websocket] 收到消息={}", message);
        //websocket 使用service 层
        ApplicationContext act = SpringUtils.getApplicationContext();
        WebSocketServiceWeb webSocketService=act.getBean(WebSocketServiceWeb.class);
        webSocketService.dealReceiveMessages(message);
    }

    @Override
    public void onClose(int paramInt, String message, boolean paramBoolean) {
        log.info("[websocket] Exit connection！");
    }

    @Override
    public void onError(Exception e) {
        log.error("[websocket] connection error={}", e.getMessage());
    }

    public void send(String text) throws NotYetConnectedException {
        super.send(text);
    }

}

