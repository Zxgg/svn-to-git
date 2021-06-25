package com.isstech.framework.manager.factory;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.constant.Constant;
import com.isstech.common.constant.Constants;
import com.isstech.common.core.domain.InMessage;
import com.isstech.common.task.SaticScheduleTask;
import com.isstech.common.utils.*;
import com.isstech.common.utils.bean.BeanUtils;
import com.isstech.common.utils.ip.AddressUtils;
import com.isstech.common.utils.ip.IpUtils;
import com.isstech.common.utils.spring.SpringUtils;
import com.isstech.framework.web.fastdfs.FastDFSClient;
import com.isstech.isstechadmin.domain.*;
import com.isstech.isstechadmin.domain.vo.Community;
import com.isstech.isstechadmin.mapper.TTaskPersonRelationMapper;
import com.isstech.isstechadmin.mapper.TTaskVehicleRelationMapper;
import com.isstech.isstechadmin.service.*;
import com.isstech.security.websocket.WebSocketService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 异步工厂（产生任务用）
 * @Author yzp
 * @Date 2020/12/14 13:48
 */
@Slf4j
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    private static FastDFSClient fastDFSClient = new FastDFSClient();

    /**
     * 记录登陆信息
     *
     * @param username 用户名
     * @param status   状态
     * @param message  消息
     * @param args     列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message,
                                             final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIP(ip);
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(address);
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLogininfor logininfor = new SysLogininfor();
                logininfor.setUserName(username);
                logininfor.setIpaddr(ip);
                logininfor.setLoginLocation(address);
                logininfor.setBrowser(browser);
                logininfor.setOs(os);
                logininfor.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    logininfor.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    logininfor.setStatus(Constants.FAIL);
                }
                // 插入数据
                SpringUtils.getBean(ISysLogininforService.class).insertLogininfor(logininfor);
            }
        };
    }

    /**
     * 操作日志记录
     *
     * @param operLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringUtils.getBean(ISysOperLogService.class).insertSysOperLog(operLog);
            }
        };
    }

    /**
     * 维护人员关系表
     *
     * @param json
     * @param rosterType
     * @return
     */
    public static TimerTask maintainRelationPerson(String json, String rosterType) {
        return new TimerTask() {
            @Override
            public void run() {
                log.info("*********************维护人员关系表****************************");
                JSONArray jsonArray = JSONArray.parseArray(json);
                for (int i = 0; i < jsonArray.size(); i++) {
                    TTaskPersonRelation tTaskPersonRelation = new TTaskPersonRelation();
                    String peopleId = jsonArray.getJSONObject(i).getString("peopleId");
                    String credentialNumber = jsonArray.getJSONObject(i).getString("credentialNumber");
                    String repositorId = jsonArray.getJSONObject(i).getString("repositorId");
//                    JSONArray faceList = jsonArray.getJSONObject(i).getJSONArray("faceList");
//                    StringBuilder faceIds = new StringBuilder();
//                    for (int j = 0; j < faceList.size(); j++) {
//                        //下载人员图片的url
//                        JSONArray featureList = faceList.getJSONObject(j).getJSONArray("featureList");
//                        for (int k = 0; k < featureList.size(); k++) {
//                            String featureId = featureList.getJSONObject(k).getString("featureId");
//                            if (StringUtils.isNotEmpty(featureId)) faceIds.append("," + featureId);
//                        }
//                    }
//                    String faceId = faceIds.toString().replaceFirst(",", "");
                    String personId = null;
                    String communityId = null;
                    String communityChildId = null;
                    if (StringUtils.isNotEmpty(credentialNumber)) {
                        Community c = SpringUtils.getBean(IBasePersonnelInformationService.class).selectPersonIdByIdentificationNumber(credentialNumber);
                        if (c != null) {
                            communityChildId = c.getCommunityChildId();
                            communityId = c.getCommunityId();
                            personId = c.getPersonId();
                            tTaskPersonRelation.setSourceType("01");
                        }
                        Community cVisitor = SpringUtils.getBean(IBaseVisitorPersonnelInformationService.class).selectPersonIdByIdentificationNumber(credentialNumber);
                        if (c != null) {
                            communityChildId = c.getCommunityChildId();
                            communityId = c.getCommunityId();
                            personId = c.getPersonId();
                            tTaskPersonRelation.setSourceType("02");
                        }
                        String cTempVisitor = SpringUtils.getBean(IBaseTempVisitorPersonnelInformationService.class).selectPersonIdByIdentificationNumber(credentialNumber);
                        if (StringUtils.isNotEmpty(cTempVisitor)) {
                            personId = cTempVisitor;
                            tTaskPersonRelation.setSourceType("03");
                        }
                    }
                    tTaskPersonRelation.setCommunityChildId(communityChildId);
                    tTaskPersonRelation.setCommunityId(communityId);
                    tTaskPersonRelation.setPeopleId(peopleId);
                    tTaskPersonRelation.setPersonId(personId);
                    tTaskPersonRelation.setSuspectId(repositorId);
                    tTaskPersonRelation.setDelFlag("0");
//                    tTaskPersonRelation.setFaceId(faceId);
                    tTaskPersonRelation.setRosterType(rosterType);

                    TTaskPersonRelationMapper bean = SpringUtils.getBean(TTaskPersonRelationMapper.class);
                    bean.deleteTTaskPersonRelationById(peopleId);

                    if (StringUtils.isNotEmpty(personId))
                        bean.insertTTaskPersonRelation(tTaskPersonRelation);
                }

            }
        };
    }

    /**
     * 车辆关系维护
     *
     * @param dataStr
     * @param roster02
     * @param suspectId
     * @param groupId
     * @return
     */
    public static TimerTask maintainRelationVehicle(String dataStr, String roster02, String suspectId, String groupId) {
        return new TimerTask() {
            @Override
            public void run() {
                log.info("--------------车辆关系维护-------------------");
                log.info(dataStr);
                JSONArray jsonArray = JSONArray.parseArray(dataStr);
                for (int i = 0; i < jsonArray.size(); i++) {
                    //车辆名单id
                    String plateId = jsonArray.getJSONObject(i).getString("plateId");
                    //车牌号
                    String lpn = jsonArray.getJSONObject(i).getString("lpn");
                    TTaskVehicleRelation tTaskVehicleRelation = new TTaskVehicleRelation();
                    String communityChildId = null;
                    String communityId = null;
                    String vehicleId = null;
                    if (StringUtils.isNotEmpty(lpn)) {
                        BaseVehicleInformation baseVehicleInformation = SpringUtils.getBean(IBaseVehicleInformationService.class).selectVehicleByPlate(lpn);
                        if (baseVehicleInformation != null) {
                            communityChildId = baseVehicleInformation.getCommunityChildId();
                            communityId = baseVehicleInformation.getCommunityId();
                            vehicleId = baseVehicleInformation.getId();
                            tTaskVehicleRelation.setSourceType("01");
                        }
                        BaseVisitorVehicleInformation visitorVehicle = SpringUtils.getBean(IBaseVisitorVehicleInformationService.class).selectVehicleByPlate(lpn);
                        if (visitorVehicle != null) {
                            communityChildId = visitorVehicle.getTargetPersonCommunityChildId();
                            communityId = visitorVehicle.getTargetPersonCommunityId();
                            vehicleId = visitorVehicle.getId();
                            tTaskVehicleRelation.setSourceType("02");
                        }
                        String tempVisitorVehicleId = SpringUtils.getBean(IBaseTempVisitorVehicleInformationService.class).selectVehicleByPlate(lpn);
                        if (StringUtils.isNotEmpty(tempVisitorVehicleId)) {
                            vehicleId = tempVisitorVehicleId;
                            tTaskVehicleRelation.setSourceType("03");
                        }

                    }
                    tTaskVehicleRelation.setCommunityChildId(communityChildId);
                    tTaskVehicleRelation.setCommunityId(communityId);
                    tTaskVehicleRelation.setVehicleId(vehicleId);
                    tTaskVehicleRelation.setSuspectId(suspectId);
                    tTaskVehicleRelation.setPlateId(plateId);
                    tTaskVehicleRelation.setRosterType(roster02);
                    tTaskVehicleRelation.setGroupId(groupId);
                    tTaskVehicleRelation.setPlate(lpn);
                    TTaskVehicleRelationMapper bean = SpringUtils.getBean(TTaskVehicleRelationMapper.class);
                    bean.deleteTTaskVehicleRelationById(plateId);
                    if (StringUtils.isNotEmpty(vehicleId))
                        bean.insertTTaskVehicleRelation(tTaskVehicleRelation);
                }

            }
        };
    }

    /**
     * 告警数据事件处理 - 演示用
     *
     * @return
     */
    public static TimerTask faceAlarm(String json) {
        return new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonObject = JSONObject.parseObject(json);
                JSONArray dataArray = jsonObject.getJSONArray("data");
                try {
                    if (dataArray != null && dataArray.size() > 0) {
                        for (int i = 0; i < dataArray.size(); i++) {
                            JSONObject object = dataArray.getJSONObject(i);
                            JSONObject commonInfo = object.getJSONObject("commonInfo");
                            String faceId = commonInfo.getString("faceId");
                            String blacklistId = commonInfo.getString("blacklistId");
                            // 设备编码
                            String cameraId = commonInfo.getString("cameraId");
                            String suspectId = commonInfo.getString("suspectId");
                            String source = commonInfo.getString("source");
                            String blkUrl = commonInfo.getString("blkUrl");
                            blkUrl = fastDFSClient.uploadImageSample(blkUrl);
                            System.out.println("blkUrl文件服务器上的地址 ----------" + blkUrl);
                            // 告警等级
                            String alarmLevel = commonInfo.getString("alarmLevel");
                            //标记位置
                            String locationMarkAddr = commonInfo.getString("cameraName");
                            String alarmTime = commonInfo.getString("alarmTime");
                            //标记时间
                            Date locationMarkTime = DateUtils.timeStamp2Date(alarmTime);
                            JSONObject privateInfo = object.getJSONObject("privateInfo");
                            JSONObject metaData = privateInfo.getJSONObject("metaData");
                            JSONObject attributes = metaData.getJSONObject("attributes");
                            // 1 命中  2 非命中
                            String hitType = attributes.getString("alarmMatch");
                            String alarmPicGroupname = commonInfo.getString("alarmPicGroupname");

                            //相似度
                            double confidence = Double.valueOf(attributes.getString("confidence"));
                            JSONObject pic = privateInfo.getJSONObject("pic");
                            //imageUrl是华为地址,图片下载转存文件服务器,返回文件服务器图片地址
                            String imageUrl = pic.getString("imageUrl");
                            System.out.println("imageUrl是华为地址 ----------" + imageUrl);
                            imageUrl = fastDFSClient.uploadImageSample(imageUrl);
                            System.out.println("imageUrl文件服务器上的地址 ----------" + imageUrl);

                            String thumImageUrl = pic.getString("thumImageUrl");
//                            System.out.println("thumImageUrl是华为地址 ----------"+  imageUrl);
//
                            thumImageUrl = fastDFSClient.uploadImageSample(thumImageUrl);
//                            System.out.println("thumImageUrl文件服务器上的地址 ----------"+  imageUrl);
                            InMessage message = new InMessage();
                            message.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
                            Integer ruleType = commonInfo.getInteger("ruleType");
                            switch (ruleType) {
                                //人脸告警
                                case Constant.FACE_ALARM:
                                    message.setChannel(Constant.CHANNEL_FACE_ALERT);
                                    String gender = attributes.getString("gender");
                                    String ageValue = attributes.getString("ageValue");
                                    String expression = attributes.getString("expression");
                                    String eyeglass = attributes.getString("eyeglass");
                                    String mouthmask = attributes.getString("mouthmask");
                                    String age = attributes.getString("age");
                                    BaseEventInfo msgIfo = new BaseEventInfo();
                                    msgIfo.setId("RT" + RandomUtil.randomNumbers(9));
                                    msgIfo.setConfidence(confidence + "");
                                    msgIfo.setDealWithType("1");
                                    msgIfo.setEventDegree(alarmLevel); //事件程度
                                    msgIfo.setEventStatus("0");
                                    String hh = new SimpleDateFormat("HH").format(locationMarkTime);
                                    java.sql.Time d1 = new java.sql.Time(Long.parseLong(hh));
                                    java.sql.Time d2 = new java.sql.Time(22);
                                    java.sql.Time d3 = new java.sql.Time(7);
                                    int i2 = d1.compareTo(d2);
                                    int i3 = d1.compareTo(d3);
                                    String eventType = "";
                                    String content = "";
                                    String eventName = "";
                                    if (i2 > 0) {
                                        if (alarmPicGroupname.contains("关爱")) {
                                            eventType = "民生服务";
                                            eventName = "关爱人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "关爱人员" + locationMarkAddr + "超过22点晚归事件!";
                                        } else if (alarmPicGroupname.contains("黑名单")) {
                                            eventType = "社会治安";
                                            eventName = "特殊人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "特别关注人员" + locationMarkAddr + "超过22点晚归事件!";
                                        } else {
                                            eventType = "社会治安";
                                            eventName = "通行事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "通行人员" + locationMarkAddr + "超过22点晚归事件!";
                                        }
                                    } else if (i3 < 0) {
                                        if (alarmPicGroupname.contains("关爱")) {
                                            eventType = "民生服务";
                                            eventName = "关爱人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "关爱人员" + locationMarkAddr + "早上7点前早出事件!";
                                        } else if (alarmPicGroupname.contains("黑名单")) {
                                            eventType = "社会治安";
                                            eventName = "特殊人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "特别关注人员" + locationMarkAddr + "早上7点前早出事件!";
                                        } else {
                                            eventType = "社会治安";
                                            eventName = "通行事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "通行人员" + locationMarkAddr + "早上7点前早出事件!";
                                        }
                                    } else {
                                        if (alarmPicGroupname.contains("关爱")) {
                                            eventType = "民生服务";
                                            eventName = "关爱人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "关爱人员" + locationMarkAddr + "出行事件!";
                                        } else if (alarmPicGroupname.contains("黑名单")) {
                                            eventType = "社会治安";
                                            eventName = "特殊人员事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "特别关注人员" + locationMarkAddr + "出行事件!";
                                        } else {
                                            eventType = "社会治安";
                                            eventName = "通行事件" + new SimpleDateFormat("HH:mm:ss").format(locationMarkTime);
                                            content = "通行人员" + locationMarkAddr + "出行事件!";
                                        }
                                    }
                                    msgIfo.setEventName(eventName);
                                    msgIfo.setAddress(locationMarkAddr);
                                    msgIfo.setReportingTime(locationMarkTime);
                                    msgIfo.setEventType(eventType);
                                    msgIfo.setContent(content);
                                    msgIfo.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                                    msgIfo.setCommunityChildId("85c77c31-563b-4795-b1c2-596cc394caba");
                                    msgIfo.setFileId(blkUrl);
                                    msgIfo.setAlertFile(thumImageUrl);
                                    msgIfo.setHandleBy("智能上报");
                                    msgIfo.setDivision("第一网格");
                                    msgIfo.setBigPicture(imageUrl);
                                    msgIfo.setUpdateTime(new Date());
//                                    msgIfo.setCreatebyPhone("12345678901");
                                    SpringUtils.getBean(IBaseEventInfoService.class).insertBaseEventInfo(msgIfo);

                                    BaseEventRecord baseEventRecord = new BaseEventRecord();
                                    baseEventRecord.setEventId(msgIfo.getId());
                                    baseEventRecord.setEventStatus("0");
                                    baseEventRecord.setHandleBy(msgIfo.getHandleBy());
                                    baseEventRecord.setEventStatusSon("0");
                                    baseEventRecord.setEventId(msgIfo.getId());
                                    baseEventRecord.setReportBy(msgIfo.getCreateBy());
                                    Optional.ofNullable(msgIfo.getEventName()).ifPresent(f -> baseEventRecord.setEventName(f));
                                    Optional.ofNullable(msgIfo.getAddress()).ifPresent(f -> baseEventRecord.setAddress(f));
                                    Optional.ofNullable(msgIfo.getFileId()).ifPresent(f -> baseEventRecord.setEventInfoFileId(f));
                                    Optional.ofNullable(msgIfo.getCommunityId()).ifPresent(f -> baseEventRecord.setCommunityId(f));
                                    Optional.ofNullable(msgIfo.getCommunityChildId()).ifPresent(f -> baseEventRecord.setCommunityChildId(f));
                                    Optional.ofNullable(msgIfo.getEventType()).ifPresent(f -> baseEventRecord.setEventType(f));
                                    Optional.ofNullable(msgIfo.getContent()).ifPresent(f -> baseEventRecord.setEventContent(f));
                                    SpringUtils.getBean(IBaseEventRecordService.class).insertBaseEventRecord(baseEventRecord);

                                    SpringUtils.getBean(WebSocketService.class).test(JSON.toJSONString(msgIfo));
                                    log.info("success of send message to vue");
                                    break;
                                //车牌识别
                                case Constant.VEHICLE_RECOGNITION:
                                    message.setChannel(Constant.CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION_METADATA);
                                    String plate = attributes.getString("pnr");
                                    String licensedVehicle = attributes.getString("licensedVehicle");
                                    String maincall = attributes.getString("maincall");
                                    String direc = attributes.getString("direc");
                                    String obscuredPlate = attributes.getString("obscuredPlate");
                                    String faceNumber = attributes.getString("faceNumber");
                                    String ptype = attributes.getString("ptype");
                                    String pcolor = attributes.getString("pcolor");
                                    String mainbelt = attributes.getString("mainbelt");
                                    String mainsunvisor = attributes.getString("mainsunvisor");
                                    String graffiti = attributes.getString("graffiti");
                                    String color = attributes.getString("color");
                                    String year = attributes.getString("year");
//                                    String cameraName = commonInfo.getString("cameraName");
                                    BasePassVehicle basePassVehicle = new BasePassVehicle();
                                    String vehicleId = null;
                                    String vehicleSourceType = null;
                                    String vcomChildId = null;
                                    String vcomId = null;
                                    Map<String, String> vehicleMap = new HashMap<>();
                                    if (StringUtils.isNotEmpty(plate)) {
                                        vehicleMap = SpringUtils.getBean(ITTaskVehicleRelationService.class).queryVehicleByPlate(plate, suspectId);
                                        vehicleId = vehicleMap.get("vehicleId");
                                        // 来源
                                        vehicleSourceType = vehicleMap.get("sourceType");
                                        vcomChildId = vehicleMap.get("communityChildId");
                                        vcomId = vehicleMap.get("communityId");
                                    } else {
                                        vehicleMap = SpringUtils.getBean(ITTaskVehicleRelationService.class).queryVehicleByPlateId(blacklistId, suspectId);
                                        vehicleId = vehicleMap.get("vehicleId");
                                        // 来源
                                        vehicleSourceType = vehicleMap.get("sourceType");
                                        vcomChildId = vehicleMap.get("communityChildId");
                                        vcomId = vehicleMap.get("communityId");
                                    }
                                    if ("01".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_vehicle_information");
                                    } else if ("02".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_visitor_vehicle_information");
                                    } else if ("03".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_temp_visitor_vehicle_information");
                                    }
                                    basePassVehicle.setCommunityChildId(vcomChildId);
                                    basePassVehicle.setCommunityId(vcomId);
                                    basePassVehicle.setVehicleId(vehicleId);
                                    basePassVehicle.setMotorVehicleId(blacklistId);
                                    basePassVehicle.setAppearTime(new Date());
                                    //经过道路
                                    basePassVehicle.setNameOfPassedRoad(locationMarkAddr);
                                    basePassVehicle.setMarkTime(locationMarkTime);
                                    //有无车牌
                                    basePassVehicle.setHasPlate(licensedVehicle);
                                    //打电话状态
                                    basePassVehicle.setCalling(maincall);
                                    basePassVehicle.setDirection(direc);
                                    basePassVehicle.setIsCovered(obscuredPlate);
                                    basePassVehicle.setNumOfPassenger(faceNumber);
                                    basePassVehicle.setPassTime(new Date());
                                    basePassVehicle.setPlateClass(ptype);
                                    basePassVehicle.setPlateColor(pcolor);
                                    basePassVehicle.setPlateNo(plate);
                                    basePassVehicle.setSafetyBelt(mainbelt);
                                    basePassVehicle.setSunvisor(mainsunvisor);
                                    basePassVehicle.setVehicleBodyDesc(graffiti);
                                    basePassVehicle.setVehicleStyles(year);
                                    basePassVehicle.setVehicleColor(color);
                                    basePassVehicle.setStorageUrl2(imageUrl);
                                    if (basePassVehicle != null)
                                        SpringUtils.getBean(IBasePassVehicleService.class).insertBasePassVehicle(basePassVehicle);
                                    message.setContent(basePassVehicle.toString());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


    /**
     * 告警数据处理
     *
     * @return
     */
    public static TimerTask faceAlarm0(String json) {
        return new TimerTask() {
            @Override
            public void run() {
                JSONObject jsonObject = JSONObject.parseObject(json);
                JSONArray dataArray = jsonObject.getJSONArray("data");
                try {
                    if (dataArray != null && dataArray.size() > 0) {
                        for (int i = 0; i < dataArray.size(); i++) {
                            JSONObject object = dataArray.getJSONObject(i);
                            JSONObject commonInfo = object.getJSONObject("commonInfo");
//                            String faceId = commonInfo.getString("faceId");
                            String blacklistId = commonInfo.getString("blacklistId");
                            // 设备编码
                            String cameraId = commonInfo.getString("cameraId");
                            String suspectId = commonInfo.getString("suspectId");
                            String source = commonInfo.getString("source");
                            //标记位置
                            String locationMarkAddr = commonInfo.getString("cameraName");
                            String alarmTime = commonInfo.getString("alarmTime");
                            //标记时间
                            Date locationMarkTime = DateUtils.timeStamp2Date(alarmTime);

                            JSONObject privateInfo = object.getJSONObject("privateInfo");
                            JSONObject metaData = privateInfo.getJSONObject("metaData");
                            JSONObject attributes = metaData.getJSONObject("attributes");
                            // 1 命中  2 非命中
                            String hitType = attributes.getString("alarmMatch");

                            //相似度
                            double confidence = Double.valueOf(attributes.getString("confidence"));


                            JSONObject pic = privateInfo.getJSONObject("pic");
                            //imageUrl是华为地址,图片下载转存文件服务器,返回文件服务器图片地址
                            String imageUrl = pic.getString("imageUrl");
                            imageUrl = fastDFSClient.uploadImageSample(imageUrl);
                            InMessage message = new InMessage();
                            message.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
                            Integer ruleType = commonInfo.getInteger("ruleType");
                            switch (ruleType) {
                                //人脸告警
                                case Constant.FACE_ALARM:
                                    message.setChannel(Constant.CHANNEL_FACE_ALERT);
                                    String gender = attributes.getString("gender");
                                    String ageValue = attributes.getString("ageValue");
                                    String expression = attributes.getString("expression");
                                    String eyeglass = attributes.getString("eyeglass");
                                    String mouthmask = attributes.getString("mouthmask");
                                    String age = attributes.getString("age");
//                                    Map<String, String> relMap = SpringUtils.getBean(ITTaskPersonRelationService.class).queryPersonId(faceId, suspectId);
                                    Map<String, String> relMap = SpringUtils.getBean(ITTaskPersonRelationService.class).queryPersonId(blacklistId, suspectId);

                                    if (CollectionUtils.isEmpty(relMap)) {
                                        // 扬州演示环境临时代码
                                        JSONObject obj = JSONObject.parseObject(json);
                                        JSONArray arr = jsonObject.getJSONArray("data");

                                        BaseEventInfo msgIfo = new BaseEventInfo();
                                        JSONObject someInfo = (JSONObject) arr.get(0);
                                        JSONObject lsInfo = someInfo.getJSONObject("commonInfo");
                                        // BaseEventInfo baseEventInfo = new BaseEventInfo();
                                        msgIfo.setId("RT" + RandomUtil.randomNumbers(9));
                                        msgIfo.setDelFlag("0");
                                        msgIfo.setDealWithType("0");
                                        msgIfo.setEventStatus("待处理");
                                        msgIfo.setEventName(lsInfo.getString("alarmPicGroupname"));
                                        msgIfo.setAddress(lsInfo.getString("alarmPicGroupname"));
                                        msgIfo.setReportingTime(DateUtils.getNowDate());
                                        msgIfo.setEventType("智能化事件");
                                        msgIfo.setContent(lsInfo.getString("文昌花园西门道闸"));
                                        msgIfo.setCommunityId("06118283605288510105022020120714280901674");
                                        msgIfo.setCommunityChildId("06118283605288510105022020120714280901674");
                                        msgIfo.setFileId("http://172.21.15.54:7801/group1/M00/00/00/rBUPNmCJBKqALTniAACAmF0XtAM393.jpg?token=ba52480d91accbd24d67c006366b9c80&ts=1619594481");
                                        msgIfo.setCreateTime(new Date());
                                        msgIfo.setUpdateTime(new Date());
                                        msgIfo.setCreateBy("admin");
                                        msgIfo.setCreatebyPhone("12345678901");
                                        msgIfo.setContent("事件");

                                        SpringUtils.getBean(IBaseEventInfoService.class).insertBaseEventInfo(msgIfo);

                                        BaseEventRecord baseEventRecord = new BaseEventRecord();
                                        baseEventRecord.setEventId(msgIfo.getId());
                                        baseEventRecord.setEventStatus("1");
                                        baseEventRecord.setEventStatusSon("1");
                                        baseEventRecord.setEventId(msgIfo.getId());
                                        baseEventRecord.setReportBy(msgIfo.getCreateBy());
                                        Optional.ofNullable(msgIfo.getEventName()).ifPresent(f -> baseEventRecord.setEventName(f));
                                        Optional.ofNullable(msgIfo.getAddress()).ifPresent(f -> baseEventRecord.setAddress(f));
                                        Optional.ofNullable(msgIfo.getFileId()).ifPresent(f -> baseEventRecord.setEventInfoFileId(f));
                                        Optional.ofNullable(msgIfo.getCommunityId()).ifPresent(f -> baseEventRecord.setCommunityId(f));
                                        Optional.ofNullable(msgIfo.getCommunityChildId()).ifPresent(f -> baseEventRecord.setCommunityChildId(f));
                                        Optional.ofNullable(msgIfo.getEventType()).ifPresent(f -> baseEventRecord.setEventType(f));
                                        Optional.ofNullable(msgIfo.getContent()).ifPresent(f -> baseEventRecord.setEventContent(f));
                                        SpringUtils.getBean(IBaseEventRecordService.class).insertBaseEventRecord(baseEventRecord);

                                        SpringUtils.getBean(WebSocketService.class).test(JSON.toJSONString(msgIfo));
                                        log.info("success of send message to vue");
                                        return;
                                    }


                                    String personId = relMap.get("personId");
                                    //名单类型
                                    String rosterType = relMap.get("rosterType");
                                    // 来源
                                    String sourceType = relMap.get("sourceType");
                                    String communityChildId = relMap.get("communityChildId");
                                    String communityId = relMap.get("communityId");
                                    BaseBlackPersonnel baseBlackPersonnel = new BaseBlackPersonnel();
                                    BaseStrangerPersonnel baseStrangerPersonnel = new BaseStrangerPersonnel();
                                    baseStrangerPersonnel.setLocationMarkTime(locationMarkTime);
                                    baseStrangerPersonnel.setLocationMarkAddr(locationMarkAddr);
                                    baseStrangerPersonnel.setGender(gender);
                                    baseStrangerPersonnel.setAgevalue(ageValue);
                                    baseStrangerPersonnel.setExpression(expression);
                                    baseStrangerPersonnel.setEyeglass(eyeglass);
                                    baseStrangerPersonnel.setMouthmask(mouthmask);
                                    baseStrangerPersonnel.setAge(age);
                                    BasePassPersonnel basePassPersonnel = new BasePassPersonnel();
                                    basePassPersonnel.setLocationMarkTime(locationMarkTime);
                                    basePassPersonnel.setLocationMarkAddr(locationMarkAddr);
                                    basePassPersonnel.setDeviceId(cameraId);
                                    BaseAlarmPersonnel baseAlarmPersonnel = new BaseAlarmPersonnel();
                                    baseAlarmPersonnel.setLocationMarkTime(locationMarkTime);
                                    baseAlarmPersonnel.setLocationMarkAddr(locationMarkAddr);
                                    baseAlarmPersonnel.setDeviceId(cameraId);
                                    baseAlarmPersonnel.setFaceId(blacklistId);
                                    baseAlarmPersonnel.setPersonId(personId);
                                    baseAlarmPersonnel.setCommunityId(communityId);
                                    baseAlarmPersonnel.setCommunityChildId(communityChildId);
                                    baseAlarmPersonnel.setSourceId(source);
                                    baseAlarmPersonnel.setPics(imageUrl);
                                    baseAlarmPersonnel.setSimilaritydegree(confidence);
                                    baseAlarmPersonnel.setFaceAppearTime(new Date());
                                    boolean baseAlarmPersonnelFlag = false;
                                    boolean basePassPersonnelFlag = false;
                                    boolean baseStrangerPersonnelFlag = false;
                                    if ("01".equals(sourceType) && StringUtils.isNotEmpty(personId)) {
                                        BasePersonnelInformation basePersonnelInformation = SpringUtils.getBean(IBasePersonnelInformationService.class).selectBasePersonnelInformationById(personId);
                                        if ("02".equals(rosterType)) {//黑名单
                                            baseAlarmPersonnel.setName(basePersonnelInformation.getName());
                                            baseAlarmPersonnel.setUsedName(basePersonnelInformation.getOldName());
                                            baseAlarmPersonnel.setIdnumber(basePersonnelInformation.getIdentificationNumber());
                                            baseAlarmPersonnel.setGenderCode(basePersonnelInformation.getSex());
                                            baseAlarmPersonnel.setChecked(basePersonnelInformation.getChecked());
                                            baseAlarmPersonnel.setSourceTableName("base_personnel_information");
                                            baseAlarmPersonnelFlag = true;
                                        } else if ("01".equals(rosterType)) {//白名单
                                            basePassPersonnel.setFaceId(blacklistId);
                                            basePassPersonnel.setPersonId(personId);
                                            basePassPersonnel.setCommunityId(communityId);
                                            basePassPersonnel.setCommunityChildId(communityChildId);
                                            basePassPersonnel.setName(basePersonnelInformation.getName());
                                            basePassPersonnel.setUsedName(basePersonnelInformation.getOldName());
                                            basePassPersonnel.setIdnumber(basePersonnelInformation.getIdentificationNumber());
                                            basePassPersonnel.setSourceId(source);
                                            basePassPersonnel.setGenderCode(basePersonnelInformation.getSex());
                                            basePassPersonnel.setChecked(basePersonnelInformation.getChecked());
                                            basePassPersonnel.setPics(imageUrl);
                                            basePassPersonnel.setFaceAppearTime(new Date());
                                            basePassPersonnel.setSourceTableName("base_personnel_information");
                                            String caring = basePersonnelInformation.getCaring();
                                            if (StringUtils.equals(caring, "1")) { //当前人员为关爱人员
                                                //查询关爱人员类型编码
                                                BaseCaringPerson caringPerson = SpringUtils.getBean(IBaseCaringPersonService.class).getByPersonId(personId);
                                                String caringType = caringPerson.getType();
                                                basePassPersonnel.setCareType(caringType);
                                            }
                                            basePassPersonnelFlag = true;
                                        } else if ("03".equals(rosterType)) {//陌生人
                                            baseStrangerPersonnel.setFaceId(blacklistId);
                                            baseStrangerPersonnel.setPersonId(personId);
                                            baseStrangerPersonnel.setCommunityId(communityId);
                                            baseStrangerPersonnel.setCommunityChildId(communityChildId);
                                            baseStrangerPersonnel.setFaceAppearTime(new Date());
                                            baseStrangerPersonnel.setPics(imageUrl);
                                            baseStrangerPersonnel.setSourceId(source);
                                            baseStrangerPersonnel.setSourceTableName("base_personnel_information");
                                            baseStrangerPersonnelFlag = true;
                                        }
                                    } else if ("02".equals(sourceType) && StringUtils.isNotEmpty(personId)) {
                                        BaseVisitorPersonnelInformation baseVisitorPersonnelInformation = SpringUtils.getBean(IBaseVisitorPersonnelInformationService.class).selectBaseVisitorPersonnelInformationById(personId);
                                        if ("02".equals(rosterType)) {//黑名单
                                            baseAlarmPersonnel.setName(baseVisitorPersonnelInformation.getName());
                                            baseAlarmPersonnel.setIdnumber(baseVisitorPersonnelInformation.getIdentificationNumber());
                                            baseAlarmPersonnel.setGenderCode(baseVisitorPersonnelInformation.getSex());
                                            baseAlarmPersonnel.setChecked(baseVisitorPersonnelInformation.getChecked());

                                            baseAlarmPersonnel.setSourceTableName("base_visitor_personnel_information");
                                            baseAlarmPersonnelFlag = true;
                                        } else if ("01".equals(rosterType)) {//白名单
                                            basePassPersonnel.setFaceId(blacklistId);
                                            basePassPersonnel.setPersonId(personId);
                                            basePassPersonnel.setCommunityId(communityId);
                                            basePassPersonnel.setCommunityChildId(communityChildId);
                                            basePassPersonnel.setName(baseVisitorPersonnelInformation.getName());
                                            basePassPersonnel.setIdnumber(baseVisitorPersonnelInformation.getIdentificationNumber());
                                            basePassPersonnel.setSourceId(source);
                                            basePassPersonnel.setGenderCode(baseVisitorPersonnelInformation.getSex());
                                            basePassPersonnel.setChecked(baseVisitorPersonnelInformation.getChecked());
                                            basePassPersonnel.setPics(imageUrl);
                                            basePassPersonnel.setFaceAppearTime(new Date());
                                            basePassPersonnel.setSourceTableName("base_visitor_personnel_information");
                                            basePassPersonnelFlag = true;
                                        } else if ("03".equals(rosterType)) {//陌生人
                                            baseStrangerPersonnel.setFaceId(blacklistId);
                                            baseStrangerPersonnel.setPersonId(personId);
                                            baseStrangerPersonnel.setCommunityId(communityId);
                                            baseStrangerPersonnel.setCommunityChildId(communityChildId);
                                            baseStrangerPersonnel.setFaceAppearTime(new Date());
                                            baseStrangerPersonnel.setPics(imageUrl);
                                            baseStrangerPersonnel.setSourceId(source);
                                            baseStrangerPersonnel.setSourceTableName("base_visitor_personnel_information");
                                            baseStrangerPersonnelFlag = true;
                                        }
                                    } else if ("03".equals(sourceType) && StringUtils.isNotEmpty(personId)) {
                                        BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation = SpringUtils.getBean(IBaseTempVisitorPersonnelInformationService.class).selectBaseTempVisitorPersonnelInformationById(personId);
                                        if ("02".equals(rosterType)) {//黑名单
                                            baseAlarmPersonnel.setName(baseTempVisitorPersonnelInformation.getName());
                                            baseAlarmPersonnel.setIdnumber(baseTempVisitorPersonnelInformation.getIdentificationNumber());
                                            baseAlarmPersonnel.setGenderCode(baseTempVisitorPersonnelInformation.getSex());
                                            baseAlarmPersonnel.setChecked(baseTempVisitorPersonnelInformation.getChecked());

                                            baseAlarmPersonnel.setSourceTableName("base_temp_visitor_personnel_information");
                                            baseAlarmPersonnelFlag = true;
                                        } else if ("01".equals(rosterType)) {//白名单
                                            basePassPersonnel.setFaceId(blacklistId);
                                            basePassPersonnel.setPersonId(personId);
                                            basePassPersonnel.setCommunityId(communityId);
                                            basePassPersonnel.setCommunityChildId(communityChildId);
                                            basePassPersonnel.setName(baseTempVisitorPersonnelInformation.getName());
                                            basePassPersonnel.setIdnumber(baseTempVisitorPersonnelInformation.getIdentificationNumber());
                                            basePassPersonnel.setSourceId(source);
                                            basePassPersonnel.setGenderCode(baseTempVisitorPersonnelInformation.getSex());
                                            basePassPersonnel.setChecked(baseTempVisitorPersonnelInformation.getChecked());
                                            basePassPersonnel.setPics(imageUrl);
                                            basePassPersonnel.setFaceAppearTime(new Date());
                                            basePassPersonnel.setSourceTableName("base_temp_visitor_personnel_information");
                                            basePassPersonnelFlag = true;
                                        } else if ("03".equals(rosterType)) {//陌生人
                                            baseStrangerPersonnel.setFaceId(blacklistId);
                                            baseStrangerPersonnel.setPersonId(personId);
                                            baseStrangerPersonnel.setCommunityId(communityId);
                                            baseStrangerPersonnel.setCommunityChildId(communityChildId);
                                            baseStrangerPersonnel.setFaceAppearTime(new Date());
                                            baseStrangerPersonnel.setPics(imageUrl);
                                            baseStrangerPersonnel.setSourceId(source);
                                            baseStrangerPersonnel.setSourceTableName("base_temp_visitor_personnel_information");
                                            baseStrangerPersonnelFlag = true;
                                        }
                                    }
                                    if (baseAlarmPersonnelFlag) {
                                        SpringUtils.getBean(IBaseAlarmPersonnelService.class).insertBaseAlarmPersonnel(baseAlarmPersonnel);
                                        message.setContent(baseAlarmPersonnel.toString());
                                    } else if (basePassPersonnelFlag) {
                                        SpringUtils.getBean(IBasePassPersonnelService.class).insertBasePassPersonnel(basePassPersonnel);
                                        if (StringUtils.isNotEmpty(basePassPersonnel.getCareType()) && !"00".equals(basePassPersonnel.getCareType())) {
                                            BaseEventInfo baseEventInfo = new BaseEventInfo();
                                            baseEventInfo.setId("RT" + RandomUtil.randomNumbers(9));
                                            baseEventInfo.setDelFlag("0");
                                            baseEventInfo.setDealWithType("0");
                                            baseEventInfo.setEventStatus("待处理");
                                            baseEventInfo.setEventName(basePassPersonnel.getName() + ",出现在" + basePassPersonnel.getLocationMarkAddr());
                                            baseEventInfo.setAddress(basePassPersonnel.getLocationMarkAddr());
                                            baseEventInfo.setReportingTime(basePassPersonnel.getLocationMarkTime());
                                            baseEventInfo.setEventType("关爱事件");
                                            baseEventInfo.setContent("关爱人：" + basePassPersonnel.getName() + ",出现在" + basePassPersonnel.getLocationMarkAddr());
                                            baseEventInfo.setCommunityId(basePassPersonnel.getCommunityId());
                                            baseEventInfo.setCommunityChildId(basePassPersonnel.getCommunityChildId());
                                            baseEventInfo.setFileId(basePassPersonnel.getPics());
                                            SpringUtils.getBean(IBaseEventInfoService.class).insertBaseEventInfo(baseEventInfo);

                                            SpringUtils.getBean(WebSocketService.class).test(JSON.toJSONString(baseEventInfo));
                                        } else {
                                            message.setContent(basePassPersonnel.toString());
                                        }
                                    } else if (baseStrangerPersonnelFlag) {//陌生人
                                        SpringUtils.getBean(IBaseStrangerPersonnelService.class).insertBaseStrangerPersonnel(baseStrangerPersonnel);
                                        message.setContent(baseStrangerPersonnel.toString());
                                    }
                                    break;
                                //车牌识别
                                case Constant.VEHICLE_RECOGNITION:
                                    message.setChannel(Constant.CHANNEL_BEHAVIOR_ALERT_VEHICLE_RECONGNITION_METADATA);
                                    String plate = attributes.getString("pnr");
                                    String licensedVehicle = attributes.getString("licensedVehicle");
                                    String maincall = attributes.getString("maincall");
                                    String direc = attributes.getString("direc");
                                    String obscuredPlate = attributes.getString("obscuredPlate");
                                    String faceNumber = attributes.getString("faceNumber");
                                    String ptype = attributes.getString("ptype");
                                    String pcolor = attributes.getString("pcolor");
                                    String mainbelt = attributes.getString("mainbelt");
                                    String mainsunvisor = attributes.getString("mainsunvisor");
                                    String graffiti = attributes.getString("graffiti");
                                    String color = attributes.getString("color");
                                    String year = attributes.getString("year");
                                    String cameraName = commonInfo.getString("cameraName");
                                    BasePassVehicle basePassVehicle = new BasePassVehicle();
                                    String vehicleId = null;
                                    String vehicleSourceType = null;
                                    String vcomChildId = null;
                                    String vcomId = null;
                                    Map<String, String> vehicleMap = new HashMap<>();
                                    if (StringUtils.isNotEmpty(plate)) {
                                        vehicleMap = SpringUtils.getBean(ITTaskVehicleRelationService.class).queryVehicleByPlate(plate, suspectId);
                                        vehicleId = vehicleMap.get("vehicleId");
                                        // 来源
                                        vehicleSourceType = vehicleMap.get("sourceType");
                                        vcomChildId = vehicleMap.get("communityChildId");
                                        vcomId = vehicleMap.get("communityId");
                                    } else {
                                        vehicleMap = SpringUtils.getBean(ITTaskVehicleRelationService.class).queryVehicleByPlateId(blacklistId, suspectId);
                                        vehicleId = vehicleMap.get("vehicleId");
                                        // 来源
                                        vehicleSourceType = vehicleMap.get("sourceType");
                                        vcomChildId = vehicleMap.get("communityChildId");
                                        vcomId = vehicleMap.get("communityId");
                                    }
                                    if ("01".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_vehicle_information");
                                    } else if ("02".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_visitor_vehicle_information");
                                    } else if ("03".equals(vehicleSourceType) && StringUtils.isNotEmpty(vehicleId)) {
                                        basePassVehicle.setSourceTableName("base_temp_visitor_vehicle_information");
                                    }
                                    basePassVehicle.setCommunityChildId(vcomChildId);
                                    basePassVehicle.setCommunityId(vcomId);
                                    basePassVehicle.setVehicleId(vehicleId);
                                    basePassVehicle.setMotorVehicleId(blacklistId);
                                    basePassVehicle.setAppearTime(new Date());
                                    //经过道路
                                    basePassVehicle.setNameOfPassedRoad(locationMarkAddr);
                                    basePassVehicle.setMarkTime(locationMarkTime);
                                    //有无车牌
                                    basePassVehicle.setHasPlate(licensedVehicle);
                                    //打电话状态
                                    basePassVehicle.setCalling(maincall);
                                    basePassVehicle.setDirection(direc);
                                    basePassVehicle.setIsCovered(obscuredPlate);
                                    basePassVehicle.setNumOfPassenger(faceNumber);
                                    basePassVehicle.setPassTime(new Date());
                                    basePassVehicle.setPlateClass(ptype);
                                    basePassVehicle.setPlateColor(pcolor);
                                    basePassVehicle.setPlateNo(plate);
                                    basePassVehicle.setSafetyBelt(mainbelt);
                                    basePassVehicle.setSunvisor(mainsunvisor);
                                    basePassVehicle.setVehicleBodyDesc(graffiti);
                                    basePassVehicle.setVehicleStyles(year);
                                    basePassVehicle.setVehicleColor(color);
                                    basePassVehicle.setStorageUrl2(imageUrl);
                                    if (basePassVehicle != null)
                                        SpringUtils.getBean(IBasePassVehicleService.class).insertBasePassVehicle(basePassVehicle);
                                    message.setContent(basePassVehicle.toString());
                                    break;
                                default:
                                    break;
                            }

                            String msag = JSONObject.toJSONString(message);
                            /*CompletableFuture.runAsync(() -> {

                            });*/

                            /*if (SaticScheduleTask.webSocketClient != null && SaticScheduleTask.webSocketClient.isOpen()) {
                                SaticScheduleTask.webSocketClient.send(msag);
                            } else {
                                SaticScheduleTask.KeepAlive();
                                SaticScheduleTask.webSocketClient.send(msag);
                            }*/
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
    }


    public static TimerTask maintainRelationOneToFour(String oneFourGet) {
        return new TimerTask() {
            @Override
            public void run() {

                String params = "{\n" +
                        "    \"params\":{\n" +
                        "        \"casecode\":\"APP202104265916\" ,\n" +
                        "        \"rqstsource\":\"\" ,\n" +
                        "        \"rqsttitle\":\"\" ,\n" +
                        "        \"pageindex\":\"1\" ,\n" +
                        "        \"pagesize\":\"200\" \n" +
                        "    }\n" +
                        "  }";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("params", params);
                String result = HttpRequest.post(oneFourGet)
                        .form(jsonObject)
                        .execute()
                        .body();
                JSONObject jsonObj = JSONObject.parseObject(result);
                JSONArray data = jsonObj.getJSONArray("data");
                List list = new ArrayList();
                for (int i = 0; i < data.size(); i++) {
                    JSONObject json = data.getJSONObject(i);
                    String rowguid = json.getString("rowguid");
//                    OneToFour oneToFour1 = SpringUtils.getBean(IOneToFourService.class).selectOneToFourById(rowguid);
//                    if(oneToFour1 == null){
                    OneToFour oneToFour = new OneToFour();
                    oneToFour.setRowguid(rowguid);
                    String lasthandlestep = json.getString("lasthandlestep");
                    oneToFour.setLasthandlestep(lasthandlestep);
                    String rqstcontent = json.getString("rqstcontent");
                    oneToFour.setRqstcontent(rqstcontent);
                    String rqstsource = json.getString("rqstsource");
                    oneToFour.setRqstsource(rqstsource);
                    JSONArray reportFile = json.getJSONArray("reportFile");
                    oneToFour.setReportFile(reportFile.toJSONString());
                    String rqsttitle = json.getString("rqsttitle");
                    oneToFour.setRqsttitle(rqsttitle);
                    String accordType = json.getString("accordtype");
                    if (!org.springframework.util.StringUtils.isEmpty(accordType)) {
                        String[] split = accordType.split("-");
                        oneToFour.setAccordType(split[1]);
                        oneToFour.setRemark(accordType);
                    } else {
                        oneToFour.setAccordType("其他分类");
                    }
                    String rqstname = json.getString("rqstname");
                    oneToFour.setRqstname(rqstname);
                    String registertime = json.getString("registertime");
                    oneToFour.setRegistertime(registertime);
                    String rqstaddress = json.getString("rqstaddress");
                    oneToFour.setRqstaddress(rqstaddress);
                    JSONArray handleFile = json.getJSONArray("handleFile");
                    oneToFour.setHandleFile(handleFile.toJSONString());
                    String rqstnumber = json.getString("rqstnumber");
                    oneToFour.setRqstnumber(rqstnumber);
                    String urgentlevel = json.getString("urgentlevel");
                    oneToFour.setUrgentlevel(urgentlevel);
                    JSONArray checkFile = json.getJSONArray("checkFile");
                    oneToFour.setCheckFile(checkFile.toJSONString());
                    oneToFour.setDelFlag("0");
                    list.add(oneToFour);
//                    }
                }
                SpringUtils.getBean(IOneToFourService.class).insertOneToFourList(list);
            }
        };
    }


    public static void main(String[] args) {
        BaseBlackPersonnel baseBlackPersonnel = new BaseBlackPersonnel();
        BaseStrangerPersonnel baseStrangerPersonnel = new BaseStrangerPersonnel();
        BasePassPersonnel basePassPersonnel = new BasePassPersonnel();
        BaseAlarmPersonnel baseAlarmPersonnel = new BaseAlarmPersonnel();
        if (baseBlackPersonnel != null) {
            System.out.println("baseBlackPersonnel");
        }
        if (baseStrangerPersonnel != null) {
            System.out.println("baseStrangerPersonnel");
        }
        if (basePassPersonnel != null) {
            System.out.println("basePassPersonnel");
        }
        if (baseAlarmPersonnel != null) {
            System.out.println("baseAlarmPersonnel");
        }
    }
}
