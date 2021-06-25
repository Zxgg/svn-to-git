package com.isstech.common.task.util;

import com.alibaba.fastjson.JSONObject;
import com.isstech.common.constant.Constant;
import com.isstech.common.core.domain.InMessage;
import org.springframework.util.ObjectUtils;

/**
 * @author lichong<br />
 * @Description: <br/>
 * @date 2019/9/9  17:54<br/>
 */

public enum WebClientEnum {

    CLIENT;

    private static MsgWebSocketClient socketClient = null;

    public static MsgWebSocketClient initClient(MsgWebSocketClient client) throws InterruptedException {
        socketClient = client;
        int resendTime = 3;
        int i = 0;
        if (!ObjectUtils.isEmpty(socketClient)) {
            socketClient.connect();
            InMessage sendMessage = new InMessage();
            sendMessage.setSendType(Constant.SEND_TYPE_SUBSCRIBE);
            sendMessage.setChannel(Constant.LIVE_DATA_SUBSCRIPTION);
            String message = JSONObject.toJSONString(sendMessage);
            while (i < resendTime) {
                if (socketClient.isOpen()) {
                    socketClient.send(message);
                    break;
                } else {
                    Thread.sleep(2000);
                    i++;
                }

            }
        }
        return client;
        /*boolean flag = true;
         *//*int i=1000;*//*
        while(flag) {
            socketClient.send("pingpong");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }*/
    }

}

