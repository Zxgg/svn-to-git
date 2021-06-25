package com.isstech.common.task.util;

import com.alibaba.fastjson.JSON;
import com.isstech.common.constant.Constant;
import com.isstech.common.core.domain.InMessage;
import org.springframework.stereotype.Component;

/**
 * @author lichong<br />
 * @Description: websocket客户端<br/>
 * @date 2019/8/26  11:14<br/>
 */

@Component
public class ScoketClient implements WebSocketServiceWeb {

    /*@Autowired
    private WebSocketClient webSocketClient;*/

    @Override
    public void groupSending(String message) {
        /*webSocketClient.send(message);*/
    }

    @Override
    public void appointSending(String name, String message) {
        // 这里指定发送的规则由服务端决定参数格式
        //webSocketClient.send("TOUSER"+name+";"+message);
    }

    @Override
    public void dealReceiveMessages(String message) {
        InMessage inMessage = JSON.toJavaObject(JSON.parseObject(message), InMessage.class);
        if(Constant.LIVE_DATA_SUBSCRIPTION.equals(inMessage.getChannel())){
//            String url = Config.FACE_HOST + VcmConstant.SINGLE_SUBSCRIPTION;
//            Resp resp = HttpClientUtil.doPostJson(url, inMessage.getContent(), Config.FACE_SERVICE_NAME);
//            JSONObject jsonObject = JSONObject.parseObject(inMessage.getContent());
//            String taskId = "";
//            if(!StringUtils.isEmpty(jsonObject.getString("taskId"))){
//                taskId = jsonObject.getString("taskId");
//                JSONObject json = new JSONObject();
//                json.put("taskId",taskId);
//                resp.setData(json);
//            }
//            InMessage msg = new InMessage();
//            msg.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
//            msg.setChannel(Constant.LIVE_DATA_SUBSCRIPTION);
//            msg.setContent(JSON.toJSONString(resp));
//            String jsonMsg = JSON.toJSONString(msg);
//            if (SaticScheduleTask.webSocketClient != null && SaticScheduleTask.webSocketClient.isOpen()) {
//                SaticScheduleTask.webSocketClient.send(jsonMsg);
//            } else {
//                SaticScheduleTask.KeepAlive();
//                SaticScheduleTask.webSocketClient.send(jsonMsg);
//            }
//            if("200".equals(resp.getStatus())){
//
//            }
        }
    }
}
