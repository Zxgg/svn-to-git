package com.isstech.isstechadmin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.entity.SysUser;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.spring.SpringUtils;
import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.BaseEventRecord;
import com.isstech.isstechadmin.domain.dto.EventCountDTO;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import com.isstech.isstechadmin.mapper.BaseEventInfoDataMapper;
import com.isstech.isstechadmin.mapper.BaseEventInfoMapper;
import com.isstech.isstechadmin.mapper.BaseEventRecordMapper;
import com.isstech.isstechadmin.mapper.SysUserMapper;
import com.isstech.isstechadmin.service.IBaseEventInfoService;
import com.isstech.security.websocket.WebSocketService;
import com.isstech.security.websocket.config.TopicPathConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 事件详情Service业务层处理
 *
 * @author yzp
 * @date 2021-03-30
 */
@Service
public class BaseEventInfoServiceImpl implements IBaseEventInfoService {
    @Autowired
    private BaseEventInfoMapper baseEventInfoMapper;
    @Autowired
    private BaseEventRecordMapper baseEventRecordMapper;
    @Autowired
    private BaseEventInfoDataMapper baseEventInfoDataMapper;
    @Autowired
    private SysUserMapper sysUserMapper;


    /**
     * 查询事件详情
     *
     * @param id 事件详情ID
     * @return 事件详情
     */
    @Override
    public BaseEventInfo selectBaseEventInfoById(String id) {
        return baseEventInfoMapper.selectBaseEventInfoById(id);
    }

    /**
     * 查询事件详情列表
     *
     * @param baseEventInfo 事件详情
     * @return 事件详情
     */
    @Override
    public List<BaseEventInfo> selectBaseEventInfoList(BaseEventInfo baseEventInfo) {
        return baseEventInfoMapper.selectBaseEventInfoList(baseEventInfo);
    }

    @Override
    public List<BaseEventInfo> queryEventsWithoutConditions(BaseEventInfo baseEventInfo) {
        if (ObjectUtils.isEmpty(baseEventInfo)) {
            return null;
        } else if (baseEventInfo.getCommunityId().isEmpty()) {
            return null;
        } else if (baseEventInfo.getDealWithType().isEmpty()) {
            return null;
        } else if (baseEventInfo.getEventStatus().isEmpty()) {
            return null;
        }

        List<BaseEventInfo> list = baseEventInfoMapper.queryEventsInfo(baseEventInfo);
        return list;
    }

    public List<BaseEventInfo> formatListForEventStatus(List<BaseEventInfo> list) {
        return list.stream().map(el -> {
            switch (el.getEventStatus()) {
                case "1":
                    el.setEventStatus("待受理");
                    break;
                case "2":
                    el.setEventStatus("受理");
                    break;
                case "3":
                    el.setEventStatus("代办结");
                    break;
                case "4":
                    el.setEventStatus("办结");
                    break;
                case "5":
                    el.setEventStatus("归档");
                    break;
            }
            return el;
        }).collect(Collectors.toList());
    }

    /**
     * 查询不同告警状态的统计数值
     */
    @Override
    public Map<String, Object> selectListStatusCount() {
        List<Map<String, Object>> list = baseEventInfoMapper.selectListStatusCount();
        Map<String, Object> mapResult = new HashMap<String, Object>();
        int total = 0;
        for (Map<String, Object> map : list) {
            if (map.get("type").toString().equalsIgnoreCase("0")) {
                mapResult.put("typeDclNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("1")) {
                mapResult.put("typeSbNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("2")) {
                mapResult.put("typeZclNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("待处理")) {
                mapResult.put("dclNum", map.get("num"));
            } else if (map.get("type").toString().equalsIgnoreCase("处理中")) {
                mapResult.put("clzNum", map.get("num"));
            } else {
            }
        }
        //状态(1-待受理，2-受理，3-待办结，4-办结，5-归档）   处理类型（1-智能化事件，2-人工处理）
        /*for (Map<String, Object> map : list) {
            if (map.get("type").toString().equalsIgnoreCase("1")) {
                mapResult.put("typeDslNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("2")) {
                mapResult.put("typeSlNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("3")) {
                mapResult.put("typeDbjNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            }  else if (map.get("type").toString().equalsIgnoreCase("4")) {
                mapResult.put("typeBjNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            }  else if (map.get("type").toString().equalsIgnoreCase("5")) {
                mapResult.put("typeGdNum", map.get("num"));
                total += Integer.parseInt(map.get("num") != null ? map.get("num").toString() : "0");
            } else if (map.get("type").toString().equalsIgnoreCase("待处理")) {
                mapResult.put("dclNum", map.get("num"));
            } else if (map.get("type").toString().equalsIgnoreCase("处理中")) {
                mapResult.put("clzNum", map.get("num"));
            } else {
            }
        }*/

        mapResult.put("allNum", total);
        return mapResult;
    }

    /**
     * 新增事件详情
     *
     * @param baseEventInfo 事件详情
     * @return 结果
     */
    @Override
    public int insertBaseEventInfo(BaseEventInfo baseEventInfo) {
        if (StringUtils.isEmpty(baseEventInfo.getId())) {
            baseEventInfo.setId(baseEventInfoMapper.selectUuid());
        }
        baseEventInfo.setCreateTime(DateUtils.getNowDate());
        baseEventInfo.setUpdateTime(DateUtils.getNowDate());
        baseEventInfo.setDelFlag("0");

        return baseEventInfoMapper.insertBaseEventInfo(baseEventInfo);
    }

    /**
     * 修改事件详情
     *
     * @param baseEventInfo 事件详情
     * @return 结果
     */
    @Override
    public int updateBaseEventInfo(BaseEventInfo baseEventInfo) {

        baseEventInfo.setUpdateTime(DateUtils.getNowDate());
        return baseEventInfoMapper.updateBaseEventInfo(baseEventInfo);
    }

    /**
     * 批量删除事件详情
     *
     * @param ids 需要删除的事件详情ID
     * @return 结果
     */
    @Override
    public int deleteBaseEventInfoByIds(String[] ids) {
        return baseEventInfoMapper.deleteBaseEventInfoByIds(ids);
    }

    /**
     * 批量软删除事件详情
     *
     * @param ids 需要删除的事件详情ID
     * @return 结果
     */
    @Override
    public int updateBaseEventInfoByIds(String[] ids) {
        return baseEventInfoMapper.updateBaseEventInfoByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult updateBaseEvents(BaseEventInfo baseEventInfo) {
        if (StringUtils.isEmpty(baseEventInfo.getId())) {
            return AjaxResult.error("The current event not exists");
        }
        if (StringUtils.isEmpty(baseEventInfo.getEventStatus())) {
            return AjaxResult.error("The current status not exists");
        }

        BaseEventInfo eventInfo = baseEventInfoMapper.selectBaseEventInfoById(baseEventInfo.getId());
        if (ObjectUtils.isEmpty(eventInfo)) {
            return AjaxResult.error("The current process not exists");
        }
        if (eventInfo.getEventStatus().equals(baseEventInfo.getEventStatus())) {
            return AjaxResult.error("The current status already exists");
        }

        Date dateNow = new Date();

        // // TODO: 2021/5/12 消除多余代码
        switch (baseEventInfo.getEventStatus()) {
            case "1":
                BaseEventRecord insertObject = new BaseEventRecord();
                insertObject.setUpdateTime(dateNow);
                insertObject.setId(baseEventRecordMapper.selectUuid());
                insertObject.setEventId(eventInfo.getId());
                insertObject.setEventName(eventInfo.getEventName());
                insertObject.setEventContent(eventInfo.getContent());
                insertObject.setCreateTime(dateNow);
                insertObject.setEventStatus(baseEventInfo.getEventStatus());
                insertObject.setUpdateTime(dateNow);
                insertObject.setDelFlag("0");
                insertObject.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                insertObject.setCommunityChildId("85c77c31-563b-4795-b1c2-596cc394caba");
                insertObject.setReportBy("admin");
                insertObject.setHandleBy(baseEventInfo.getHandleBy());
                insertObject.setHandleById(baseEventInfo.getHandleById());
                insertObject.setEventStatusSon("1");
                baseEventRecordMapper.insertBaseEventRecord(insertObject);

                insertObject.setId(baseEventRecordMapper.selectUuid());
                insertObject.setEventStatusSon("2");
                insertObject.setDelFlag("1");
                baseEventRecordMapper.insertBaseEventRecord(insertObject);
                break;
            case "2":
                BaseEventRecord insertObjectTow = new BaseEventRecord();
                insertObjectTow.setUpdateTime(dateNow);
                insertObjectTow.setId(baseEventRecordMapper.selectUuid());
                insertObjectTow.setEventId(eventInfo.getId());
                insertObjectTow.setEventName(eventInfo.getEventName());
                insertObjectTow.setEventContent(eventInfo.getContent());
                insertObjectTow.setCreateTime(dateNow);
                insertObjectTow.setEventStatus(baseEventInfo.getEventStatus());
                insertObjectTow.setEventStatusSon("3");
                insertObjectTow.setUpdateTime(dateNow);
                insertObjectTow.setDelFlag("0");
                insertObjectTow.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                insertObjectTow.setCommunityChildId("85c77c31-563b-4795-b1c2-596cc394caba");
                insertObjectTow.setReportBy(baseEventInfo.getHandleBy());
                insertObjectTow.setHandleBy(baseEventInfo.getHandleBy());
                insertObjectTow.setHandleById(baseEventInfo.getHandleById());
                baseEventRecordMapper.insertBaseEventRecord(insertObjectTow);

                insertObjectTow.setId(baseEventRecordMapper.selectUuid());
                insertObjectTow.setEventStatusSon("4");
                insertObjectTow.setDelFlag("1");
                baseEventRecordMapper.insertBaseEventRecord(insertObjectTow);
                break;
            case "3":
                BaseEventRecord insertObjectTowThree = new BaseEventRecord();
                insertObjectTowThree.setUpdateTime(dateNow);
                insertObjectTowThree.setId(baseEventRecordMapper.selectUuid());
                insertObjectTowThree.setEventId(eventInfo.getId());
                insertObjectTowThree.setEventName(eventInfo.getEventName());
                insertObjectTowThree.setEventContent(eventInfo.getContent());
                insertObjectTowThree.setCreateTime(dateNow);
                insertObjectTowThree.setEventInfoFileId(baseEventInfo.getFileId());
                insertObjectTowThree.setEventStatus(baseEventInfo.getEventStatus());
                insertObjectTowThree.setEventStatusSon("5");
                insertObjectTowThree.setUpdateTime(dateNow);
                insertObjectTowThree.setDelFlag("0");
                insertObjectTowThree.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                insertObjectTowThree.setCommunityChildId("85c77c31-563b-4795-b1c2-596cc394caba");
                insertObjectTowThree.setReportBy("admin");
                insertObjectTowThree.setHandleBy(baseEventInfo.getHandleBy());
                insertObjectTowThree.setHandleById(baseEventInfo.getHandleById());
                baseEventRecordMapper.insertBaseEventRecord(insertObjectTowThree);

                insertObjectTowThree.setId(baseEventRecordMapper.selectUuid());
                insertObjectTowThree.setEventStatusSon("6");
                insertObjectTowThree.setDelFlag("1");
                baseEventRecordMapper.insertBaseEventRecord(insertObjectTowThree);
                break;
            case "4":
                BaseEventRecord insertObjectTowFour = new BaseEventRecord();
                insertObjectTowFour.setUpdateTime(dateNow);
                insertObjectTowFour.setId(baseEventRecordMapper.selectUuid());
                insertObjectTowFour.setEventId(eventInfo.getId());
                insertObjectTowFour.setEventName(eventInfo.getEventName());
                insertObjectTowFour.setEventContent(eventInfo.getContent());
                insertObjectTowFour.setCreateTime(dateNow);
                insertObjectTowFour.setEventStatus(baseEventInfo.getEventStatus());
                insertObjectTowFour.setEventStatusSon("7");
                insertObjectTowFour.setUpdateTime(dateNow);
                insertObjectTowFour.setDelFlag("0");
                insertObjectTowFour.setCommunityId("0355931e-a70b-11eb-a21e-0050569ba051");
                insertObjectTowFour.setCommunityChildId("85c77c31-563b-4795-b1c2-596cc394caba");
                insertObjectTowFour.setReportBy("admin");
                insertObjectTowFour.setHandleBy(baseEventInfo.getHandleBy());
                insertObjectTowFour.setHandleById(baseEventInfo.getHandleById());
                baseEventRecordMapper.insertBaseEventRecord(insertObjectTowFour);
                break;
            case "-1":
                // 误报
                baseEventInfo.setDelFlag("1");
                baseEventInfo.setEventStatus("-1");
                baseEventInfo.setUpdateTime(dateNow);
                baseEventInfoMapper.updateBaseEventInfo(baseEventInfo);
                return AjaxResult.success();
            case "9":
                // todo 上报流程
                break;
            default:
                break;
        }

        baseEventInfo.setUpdateTime(dateNow);
        int eventResult = baseEventInfoMapper.updateBaseEventInfo(baseEventInfo);

        if (eventResult != 1) {
            return AjaxResult.error("update event info error");
        }

        SpringUtils.getBean(WebSocketService.class).sendMessageInfo(TopicPathConfig.SEND_STATUS_CHANGE_MESSAGE,
                JSONObject.toJSONString(baseEventInfo));

        return AjaxResult.success();
    }

    @Override
    public List<GroupByVo> countEvent() {
        return baseEventInfoMapper.countEvent();
    }

    /**
     * 保存所有1+4数据到事件信息备份表
     * base_event_info_data
     *
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAllData(List<BaseEventInfo> list) throws Exception {

        baseEventInfoDataMapper.deleteAll();
        try {

            for (BaseEventInfo el : list) {
                el.setId(UUID.randomUUID().toString());
                baseEventInfoDataMapper.insertBaseEventInfo(el);
            }
        } catch (Exception e) {
            throw new Exception("save data error");
        } finally {

        }
    }

    @Override
    public AjaxResult getAnalysisData() {
        return AjaxResult.success(baseEventInfoDataMapper.analysisData());
    }

    /**
     * 删除事件详情信息
     *
     * @param id 事件详情ID
     * @return 结果
     */
    @Override
    public int deleteBaseEventInfoById(String id) {
        return baseEventInfoMapper.deleteBaseEventInfoById(id);
    }

    @Override
    public List<SysUser> getSysUserData(String communityId, String communityChildId) {
        List<SysUser> list = sysUserMapper.getSysUserData(communityId, communityChildId);
        return list;
    }

    /**
     * 根据社区id，按网格统计每个网格的数据
     *
     * @param baseEventInfo
     */
    @Override
    public List<EventCountDTO> sumDataGroupByDivision(BaseEventInfo baseEventInfo) {
        return baseEventInfoDataMapper.sumDataGroupByDivision(baseEventInfo);
    }

    /**
     * 获取1+4累计受理和累计办结
     *
     * @param baseEventInfo
     * @return
     */
    @Override
    public EventCountDTO getHandleAndConclude(BaseEventInfo baseEventInfo) {
        return baseEventInfoDataMapper.getHandleAndConclude(baseEventInfo);
    }

    /**
     * 查询1+4备份事件数据
     *
     * @param baseEventInfo
     * @return
     */
    @Override
    public List<BaseEventInfo> selectBaseEventInfoDataList(BaseEventInfo baseEventInfo) {
        return baseEventInfoDataMapper.selectBaseEventInfoList(baseEventInfo);
    }
}
