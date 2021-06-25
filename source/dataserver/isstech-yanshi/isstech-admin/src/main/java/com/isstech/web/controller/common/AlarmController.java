package com.isstech.web.controller.common;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.constant.Constant;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.InMessage;
import com.isstech.common.utils.DateUtils;
import com.isstech.framework.manager.AsyncManager;
import com.isstech.framework.manager.factory.AsyncFactory;
import com.isstech.framework.web.fastdfs.FastDFSClient;
import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.dto.PushMessageDTO;
import com.isstech.isstechadmin.service.IBaseEventInfoService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @Description
 * @Author yzp
 * @Date 2020/12/14 15:23
 */
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping("/alert")
public class AlarmController extends BaseController {

    @Value(value = "${ONE_FOUR_PUSH}")
    private String oneFourPush;
    @Value(value = "${ONE_FOUR_GET}")
    private String oneFourGet;
    @Autowired
    private IBaseEventInfoService eventInfoService;
    private static FastDFSClient fastDFSClient = new FastDFSClient();

    /**
     * 告警信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "alertPushNew")
    public AjaxResult alertPushNew(@RequestBody(required = false) String json) {
        System.out.println("=========alertPushNew=============jsonObject.toString()============================");
        System.out.println(json);
        AsyncManager.me().execute(AsyncFactory.faceAlarm(json));
        return AjaxResult.success();
    }


    /**
     * 接收并发布单个告警信息
     *
     * @param json
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "alertPush")
    public AjaxResult alertPush(@RequestBody(required = false) String json) {
        System.out.println("=======================alertPushList===========================");
        System.out.println("=========alertPushList=============jsonObject.toString()============================");
        System.out.println(json);
        System.out.println("==========alertPushList============jsonObject.toJSONString()============================");
        JSONArray jsonArray = JSONArray.parseArray(json);
        System.out.println(jsonArray.toJSONString());
        try {
            if (jsonArray != null && jsonArray.size() > 0) {
                System.out.println("==================+++++++++++++++++++++++++++++++++++++++++++++++++++" + jsonArray.size());
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONObject alarm = jsonObject.getJSONObject("alarm");
                    JSONObject rule = alarm.getJSONObject("rule");
                    Integer ruleType = Integer.parseInt(rule.getString("type"));
                    InMessage message = new InMessage();
                    message.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
                    switch (ruleType) {
                        //人脸告警
                        case Constant.FACE_ALARM:
                            message.setChannel(Constant.CHANNEL_FACE_ALERT);
                            break;
                        default:
                            return AjaxResult.success(jsonObject);
                    }
//                    message.setContent(dataArray.toJSONString());
//                    String msag = JSONObject.toJSONString(message);
//                    if (SaticScheduleTask.webSocketClient != null && SaticScheduleTask.webSocketClient.isOpen()) {
//                        SaticScheduleTask.webSocketClient.send(msag);
//                    } else {
//                        SaticScheduleTask.KeepAlive();
//                        SaticScheduleTask.webSocketClient.send(msag);
//                    }
                    return AjaxResult.success();
                }
                //处理抓拍 （抓拍只有人脸和车辆，ruleType=0,根据alarmType
//                JSONArray dataArray = jsonObject.getJSONArray("data");
//                if (dataArray != null) {
//                    JSONObject commonJson = dataArray.getJSONObject(0).getJSONObject("commonInfo");
//                    InMessage message = new InMessage();
//                    message.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
//                    if (commonJson != null) {
//                        int alarmType = commonJson.getIntValue("alarmType");
//                        switch (alarmType) {
//                            //人脸告警
//                            case Constants.ALARM_FACE:
//                                message.setChannel(Constant.CHANNEL_FACE_ALERT_METADATA);
//                                break;
//                            //车牌识别
//                            case Constant.ALARM_VEHICLE:
//                                message.setChannel(Constant.CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION_METADATA);
//                                break;
//
//                            //车辆识别
//                            case Constant.ALARM_GPU_VEHICLE:
//                                message.setChannel(Constant.CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION_METADATA);
//                                break;
//                            default:
//                                return Resp.success(jsonObject);
//                        }
//                    }
//                    message.setContent(dataArray.toJSONString());
//                    String msag = JSONObject.toJSONString(message);
//                    if (SaticScheduleTask.webSocketClient != null && SaticScheduleTask.webSocketClient.isOpen()) {
//                        SaticScheduleTask.webSocketClient.send(msag);
//                    } else {
//                        SaticScheduleTask.KeepAlive();
//                        SaticScheduleTask.webSocketClient.send(msag);
//                    }
//                    return AjaxResult.success();
//                }
            }
        } catch (Exception ex) {
            logger.error("IntelligentAnalysisController.alertPush error!", ex);
        }
        return AjaxResult.error();
    }


    /**
     * 网格系统向1+4平台推送消息
     *
     * @param baseEventInfo
     */
    @RequestMapping("/pushToOneFour")
    public AjaxResult alertPush(@RequestBody(required = false) BaseEventInfo baseEventInfo) {

        if (ObjectUtils.isEmpty(baseEventInfo) ||
                StringUtils.isEmpty(baseEventInfo.getId()) ||
                StringUtils.isEmpty(baseEventInfo.getEventName()) ||
                StringUtils.isEmpty(baseEventInfo.getContent()) ||
                StringUtils.isEmpty(baseEventInfo.getEventType())
        ) {
            return AjaxResult.error("params is null");
        }

        // gridCode 321002004013001 表示1+4文昌社区
        // caseCode RT + 9 位随机数字符串
        PushMessageDTO messageInfo = PushMessageDTO.builder()
                .gisx("1.2323232")
                .gisy("2.344234")
                .gridCode("321002004013001")
                .rowGuid(RandomStringUtils.randomAlphanumeric(10))
                .caseCode(baseEventInfo.getId())
                .rqstName(baseEventInfo.getCreateBy())
                .rqstTime(new Date().toString())
                .rqstNumber(baseEventInfo.getCreatebyPhone())
                .rqstTitle(baseEventInfo.getEventName())
                .notes(baseEventInfo.getContent())
                .rqstEventType(get(baseEventInfo.getEventType()))
                .reportMedia(baseEventInfo.getFileId())
                .reportMedia("")
                .build();

        System.out.println(JSONObject.toJSONString(messageInfo));
        String result = HttpRequest.post(oneFourPush)
                .body(JSONObject.toJSONString(messageInfo))
                .timeout(2000)
                .execute()
                .body();

        return AjaxResult.success(result);
    }

    /**
     * 从1+4平台获取数据
     * params 1+4 平台要求的查询参数
     * {
     * "params":{
     * "casecode":"APP202104265916",//工单编号
     * "rqstsource":"",//数据来源
     * "rqstEventType":"",//事件分类
     * "rqsttitle":"",//工单事件标题
     * "pageindex":"1",//分页页码，不传默认1
     * "pagesize":"" //分页每页页数，默认200
     * <p>
     * }
     * }
     *
     * @return AjaxResult
     */
    @PostMapping("/getFromOneFour")
    public AjaxResult getFromOneFour(@RequestBody String params) {
        if (StringUtils.isEmpty(params)) {
            return AjaxResult.error("params is null");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("params", params);
        String result = HttpRequest.post(oneFourGet)
                .form(jsonObject)
                .execute()
                .body();
        List list = new ArrayList();
        JSONObject jsonObj = JSONObject.parseObject(result);
        if ("1".equals(jsonObj.getString("status"))) {
            JSONArray data = jsonObj.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject json = data.getJSONObject(i);
                BaseEventInfo baseEventInfo = new BaseEventInfo();
                String severity = json.getString("severity");
                baseEventInfo.setEventDegree(severity);
                String lasthandlestep = json.getString("lasthandlestep");
                baseEventInfo.setEventStatus(lasthandlestep);
                String eventTitle = json.getString("eventTitle");
                baseEventInfo.setEventName(eventTitle);
                //来源
                String eventSource = json.getString("eventSource");
                baseEventInfo.setDealWithType(eventSource);
                //处置人
                String petitioner = json.getString("petitioner");
                baseEventInfo.setHandleBy(petitioner);
                // 1+4诉求人
                baseEventInfo.setPetitioner(petitioner);
                // 1+4处置类型
                baseEventInfo.setDisposition(json.getString("disposition"));
                // 1+4检查类别
                baseEventInfo.setConfidence(json.getString("checkType"));
                // 1+4检查对象
                baseEventInfo.setUpdateBy(json.getString("checkObj"));
                // 1+4诉求人电话
                baseEventInfo.setCreatebyPhone(json.getString("petitionerPhone"));
                //上报时间
                String reportTime = json.getString("reportTime");
                baseEventInfo.setReportingTime(DateUtils.parseDate(reportTime));

                // 文昌社区id
                baseEventInfo.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                baseEventInfo.setDelFlag("0");

                JSONArray beforeDisposal = json.getJSONArray("beforeDisposal");
                JSONArray afterDisposal = json.getJSONArray("afterDisposal");
                if (!beforeDisposal.isEmpty()) {
                    String fileId = beforeDisposal.get(0).toString();
                    fileId = "http://172.21.15.54:7801" + fastDFSClient.uploadImageSample(fileId);
                    baseEventInfo.setFileId(fileId);
                }
//                System.out.println();
                if (!CollectionUtils.isEmpty(afterDisposal)) {
                    String fileId = afterDisposal.get(0).toString();
                    fileId = "http://172.21.15.54:7801" + fastDFSClient.uploadImageSample(fileId);
                    baseEventInfo.setAlertFile(fileId);
                }

                // 上报人
                String reportPerson = json.getString("reportPerson");
                baseEventInfo.setHandleBy(reportPerson);
                String content = json.getString("content");
                baseEventInfo.setId(json.getString("eventNumber"));
                baseEventInfo.setContent(content);
                String division = json.getString("division");
                if (StringUtils.isEmpty(division)) {
                    baseEventInfo.setDivision("第一网格");
                } else {
                    if (division.contains("-")) {
                        baseEventInfo.setDivision(division.split("-")[2]);
                    } else {
                        baseEventInfo.setDivision(division);
                    }
                }
                JSONArray handlesteparray = json.getJSONArray("handlesteparray");
                baseEventInfo.setRemarks(handlesteparray.toJSONString());
                //类型
                String eventType = json.getString("eventType");

                if (StringUtils.isEmpty(eventType)) {
                    baseEventInfo.setEventType("其他分类");
                } else {
                    if (eventType.contains("-")) {
//                        String[] split = eventType.split("-");
//                        if (split[1].equals("事件")) {
//                            baseEventInfo.setEventType("民生服务");
//                        } else if (split[1].equals("部件")) {
//                            baseEventInfo.setEventType("社会治安");
//                        } else {
//                            baseEventInfo.setEventType(split[1]);
//                        }
                        if (eventType.contains("部件")) {
                            baseEventInfo.setEventType("社会治安");
                        } else if (eventType.equals("事件")) {
                            baseEventInfo.setEventType("民生服务");
                        }

                    } else {
                        baseEventInfo.setEventType(eventType);
                    }
                }
                if (!StringUtils.isEmpty(eventTitle)) {
                    list.add(baseEventInfo);
                }

            }
        }
        return AjaxResult.success("操作成功", changeEventSource(list));
    }

    /**
     * @param list 1+4元数据转换后集合
     * @return
     */
    public List<BaseEventInfo> changeEventSource(List<BaseEventInfo> list) {
        List<BaseEventInfo> result = eventInfoService.selectBaseEventInfoList(new BaseEventInfo());

        return list.stream().map(el -> {
                    List<BaseEventInfo> filterResult = result.stream()
                            .filter(ele -> ele.getId().equals(el.getId()))
                            .collect(Collectors.toList());
                    if (!CollectionUtils.isEmpty(filterResult) && filterResult.size() == 1) {
                        el.setDealWithType(filterResult.get(0).getDealWithType().equals("1") ?
                                "智能化事件" : "人工处理");
                    } else if (filterResult.size() == 1) {
                        logger.error("1+4 eventNumber" + el.getId()
                                + " Corresponding to multiple database primary keys");
                    }
                    return el;
                }
        ).collect(Collectors.toList());
    }

    public AjaxResult getFromOneFourBackData(@RequestBody String params) {
        if (StringUtils.isEmpty(params)) {
            return AjaxResult.error("params is null");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("params", params);
        String result = HttpRequest.post(oneFourGet)
                .form(jsonObject)
                .execute()
                .body();
        List list = new ArrayList();
        JSONObject jsonObj = JSONObject.parseObject(result);
        if ("1".equals(jsonObj.getString("status"))) {
            JSONArray data = jsonObj.getJSONArray("data");
            for (int i = 0; i < data.size(); i++) {
                JSONObject json = data.getJSONObject(i);
                BaseEventInfo baseEventInfo = new BaseEventInfo();
                String severity = json.getString("severity");
                baseEventInfo.setEventDegree(severity);
                String lasthandlestep = json.getString("lasthandlestep");
                baseEventInfo.setEventStatus(lasthandlestep);
                String eventTitle = json.getString("eventTitle");
                baseEventInfo.setEventName(eventTitle);
                //来源
                String eventSource = json.getString("eventSource");
                baseEventInfo.setDealWithType(eventSource);
                //处置人
                String petitioner = json.getString("petitioner");
                baseEventInfo.setHandleBy(petitioner);

                //上报时间
                String reportTime = json.getString("reportTime");
                baseEventInfo.setReportingTime(DateUtils.parseDate(reportTime));

                JSONArray beforeDisposal = json.getJSONArray("beforeDisposal");
                if (!beforeDisposal.isEmpty()) {
                    String fileId = beforeDisposal.get(0).toString();
                    baseEventInfo.setFileId(fileId);
                }

                // 上报人
                String reportPerson = json.getString("reportPerson");
                baseEventInfo.setHandleBy(reportPerson);
                String content = json.getString("content");
                baseEventInfo.setId(json.getString("eventNumber"));
                baseEventInfo.setContent(content);
                String division = json.getString("division");
                if (StringUtils.isEmpty(division)) {
                    baseEventInfo.setDivision("第一网格");
                } else {
                    if (division.contains("-")) {
                        baseEventInfo.setDivision(division.split("-")[2]);
                    } else {
                        baseEventInfo.setDivision(division);
                    }
                }
                JSONArray handlesteparray = json.getJSONArray("handlesteparray");
                baseEventInfo.setRemarks(handlesteparray.toJSONString());
                //类型
                String eventType = json.getString("eventType");
                if (StringUtils.isEmpty(eventType)) {
                    baseEventInfo.setEventType("其他分类");
                } else {
                    if (eventType.contains("-")) {
                        String[] split = eventType.split("-");
                        baseEventInfo.setEventType(split[1]);
                    } else {
                        baseEventInfo.setEventType(eventType);
                    }
                }
                list.add(baseEventInfo);
            }
        }
        return AjaxResult.success("操作成功", changeEventSource(list));
    }


    public static String get(String key) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("社会治安", "0104");
        map.put("纠纷调解", "0105");
        map.put("专项活动", "0106");
        map.put("公共安全", "0101");
        map.put("环境保护", "0103");
        map.put("城市管理", "0102");
        map.put("民生服务", "0107");
        if (map.get(key) != null) return map.get(key).toString();
        return "0000";
    }


    public static void main(String[] args) {
        String hh = new SimpleDateFormat("HH").format(new Date());
        System.out.println(hh);
        java.sql.Time d1 = new java.sql.Time(Long.parseLong(hh));
        java.sql.Time d2 = new java.sql.Time(23);
        int i = d1.compareTo(d2);
        System.out.println(i > 0);
    }

}
