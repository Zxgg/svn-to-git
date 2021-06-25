package com.isstech.common.core.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description websocket信息
 * @Author yzp
 * @Date 2020/12/17 10:35
 */
@Data
public class InMessage implements Serializable{

    private static final long serialVersionUID = 8957107006902627635L;

    /**
     * 发送消息类型
     */
    private String sendType;

    /**
     * 频道
     */
    private String channel;

    /**
     * 内容
     */
    private String content;

}
