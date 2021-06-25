package com.isstech.common.constant;

/**
 * @Description 告警相关-通用常量信息
 * @Author yzp
 * @Date 2020/12/16 19:09
 */
public class Constant
{
    //alarmType
    //行为分析
    public static final int ALARM_ACTION = 0;
    //普通车牌识别 -车牌识别
    public static final int ALARM_VEHICLE = 1;
    //人脸识别 - 人脸告警
    public static final int ALARM_FACE = 4;
    //车辆GPU识别 - 车辆识别
    public static final int ALARM_GPU_VEHICLE = 8;

    //rule-type
    public static final int FACE_ALARM=16;//人脸告警
    public static final int VEHICLE_RECOGNITION=17;//车牌识别

    //人脸标签，数据订阅并获取告警，可以订阅人脸标签命中或不命中推送
    public static final int FACE_AREA=0;


    //WebSocket发送消息类型
    public static final String SEND_TYPE_SENDMESSAGE = "sendMessage"; //发送消息
    public static final String SEND_TYPE_SUBSCRIBE = "subscribe"; //订阅频道

    //WebSocket订阅频道
    public static final String LIVE_DATA_SUBSCRIPTION = "liveDataSubscription"; //创建实时数据订阅

    //告警频道
    public static final String CHANNEL_FACE_ALERT = "PersonFaceAlarmEvent"; //人脸告警
    public static final String CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION= "IsstechVehicleAlarmEvent"; //行为分析--车辆识别
    public static final String CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION_METADATA = "VehicleRecongnitionMetadata"; // 车牌识别
}
