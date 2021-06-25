package com.isstech.common.task.util;

/**
 * @Description
 * @Author yzp
 * @Date 2020/12/17 15:33
 */
public interface WebSocketServiceWeb {
    /**
     * 群发
     * @param message
     */

    public void groupSending(String message);


    /**
     * 指定发送
     * @param name
     * @param message
     */

    public void appointSending(String name, String message);

    /**
     * 处理接收消息
     * @param message
     */
    public void dealReceiveMessages(String message);
}
