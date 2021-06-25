package com.isstech.security.websocket;

import com.isstech.security.websocket.config.TopicPathConfig;
import com.isstech.security.websocket.model.OutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @Author yuren
 * @Date 2020/11/17 17:16
 * @Version 1.0
 */

@Service
public class WebSocketService {
    @Autowired
    private SimpMessagingTemplate template;

    public void test(String params) {
        template.convertAndSend(TopicPathConfig.PATH_STR_CAMERA_TEST, new OutMessage(params));
        System.out.println("test:" + params);
    }

    public void alertSend(String params) {
        template.convertAndSend(TopicPathConfig.PATH_STR_MAIN_INDEX, new OutMessage(params));
        System.out.println(params);
    }

    public void sendMessageInfo(@NotNull String topic, String messageInfo) {
        template.convertAndSend(topic, new OutMessage(messageInfo));
        System.out.println(messageInfo);
    }
}
