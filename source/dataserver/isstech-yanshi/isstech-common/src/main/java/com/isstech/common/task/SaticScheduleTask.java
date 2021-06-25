package com.isstech.common.task;


import com.isstech.common.config.Config;
import com.isstech.common.task.util.MsgWebSocketClient;
import com.isstech.common.task.util.WebClientEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description 定时任务
 * @Author yzp
 * @Date 2020/12/17 15:23
 */
@Slf4j
@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {


    public Logger logger = LoggerFactory.getLogger(this.getClass());
    public static MsgWebSocketClient webSocketClient = null;

    /**
     * 定时检测断线重连
     * 第一次延迟2秒后开始执行，之后每10s执行一次
     */
    @Scheduled(initialDelay = 2000, fixedRate = 10000)
    public static void KeepAlive() {
        try {
            if (webSocketClient == null || !webSocketClient.isOpen()) {
                webSocketClient = WebClientEnum.CLIENT.initClient(new MsgWebSocketClient(Config.WEBSOCKET_URL));
            }
        } catch (Exception e) {
            log.error("websocket connection error！", e.getMessage());
        }
    }
}
