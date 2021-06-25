package com.isstech.web.controller.communityUnit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.isstech.common.annotation.Log;
import com.isstech.common.constant.Constant;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.InMessage;
import com.isstech.common.core.domain.entity.SysUser;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.task.SaticScheduleTask;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.BaseEventRecord;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import com.isstech.isstechadmin.service.IBaseEventInfoService;
import com.isstech.isstechadmin.service.IBaseEventRecordService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.web.controller.common.AlarmController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事件详情Controller
 *
 * @author yzp
 * @date 2021-03-30
 */
@Api(tags = "BaseEventInfo", description = "事件详情")
@RestController
@RequestMapping("/property_company/base_event_info")
public class BaseEventInfoController extends BaseController {
    @Autowired
    private IBaseEventInfoService baseEventInfoService;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    @Autowired
    private IBaseEventRecordService baseEventRecordService;
    @Autowired
    private AlarmController alarmController;

    /**
     * 查询事件详情列表
     */
    @ApiOperation("查询事件详情列表")
    @GetMapping("/list")
    public TableDataInfo list(BaseEventInfo baseEventInfo, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventInfo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            baseEventInfo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        }
        startPage();
        List<BaseEventInfo> list = baseEventInfoService.selectBaseEventInfoList(baseEventInfo);
        return getDataTable(list.stream()
                .sorted(Comparator.comparing(BaseEventInfo::getCreateTime).reversed())
                .collect(Collectors.toList()));
    }

    @ApiOperation("查询事件")
    @GetMapping("/queryEventInfoList")
    public TableDataInfo queryEventInfoList(BaseEventInfo baseEventInfo, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventInfo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            baseEventInfo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        }
        startPage();
        List<BaseEventInfo> list = baseEventInfoService.queryEventsWithoutConditions(baseEventInfo);
        return getDataTable(list.stream()
                .sorted(Comparator.comparing(BaseEventInfo::getCreateTime).reversed())
                .collect(Collectors.toList()));
    }

    /**
     * 查询告警数量统计 不同状态的数量
     */
    @ApiOperation("查询告警数量统计")
    @GetMapping("/listStatusCount")
    public AjaxResult listStatusCount(BaseEventInfo baseEventInfo, HttpServletRequest request) {
        Map<String, Object> map = baseEventInfoService.selectListStatusCount();
        return AjaxResult.success(map);
    }

    /**
     * 导出事件详情列表
     */
    @ApiOperation("导出事件详情列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:info:export')")
    @Log(title = "事件详情", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseEventInfo baseEventInfo) {
        List<BaseEventInfo> list = baseEventInfoService.selectBaseEventInfoList(baseEventInfo);
        ExcelUtil<BaseEventInfo> util = new ExcelUtil<BaseEventInfo>(BaseEventInfo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取事件详情详细信息
     */
    @ApiOperation("获取事件详情详细信息")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(baseEventInfoService.selectBaseEventInfoById(id));
    }

    /**
     * 新增事件详情
     */
    @ApiOperation("新增事件详情")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:info:add')")
    @Log(title = "事件详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseEventInfo baseEventInfo, HttpServletRequest request) {
        System.out.println(baseEventInfo);
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventInfo.setCreateBy(SecurityUtils.getLoginUser().getNickName());
        }
        baseEventInfoService.insertBaseEventInfo(baseEventInfo);

        BaseEventRecord baseEventRecord = new BaseEventRecord();
        baseEventRecord.setEventId(baseEventInfo.getId());
        baseEventRecord.setEventStatus(baseEventInfo.getEventStatus());
        baseEventRecord.setEventStatusSon(baseEventInfo.getEventStatus());
        baseEventRecord.setEventId(baseEventInfo.getId());
        baseEventRecord.setReportBy(baseEventInfo.getCreateBy());
        Date nowDate = DateUtils.getNowDate();
        baseEventRecord.setCreateTime(nowDate);
        baseEventRecord.setReportingTime(nowDate);
        baseEventRecord.setUpdateTime(nowDate);
        baseEventRecord.setEventDegree(baseEventInfo.getEventDegree());

        Optional.ofNullable(baseEventInfo.getEventName()).ifPresent(f -> baseEventRecord.setEventName(f));
        Optional.ofNullable(baseEventInfo.getAddress()).ifPresent(f -> baseEventRecord.setAddress(f));
        Optional.ofNullable(baseEventInfo.getFileId()).ifPresent(f -> baseEventRecord.setEventInfoFileId(f));
        Optional.ofNullable(baseEventInfo.getCommunityId()).ifPresent(f -> baseEventRecord.setCommunityId(f));
        Optional.ofNullable(baseEventInfo.getCommunityChildId()).ifPresent(f -> baseEventRecord.setCommunityChildId(f));
        Optional.ofNullable(baseEventInfo.getEventType()).ifPresent(f -> baseEventRecord.setEventType(f));
        Optional.ofNullable(baseEventInfo.getContent()).ifPresent(f -> baseEventRecord.setEventContent(f));

        return toAjax(baseEventRecordService.insertBaseEventRecord(baseEventRecord));
    }

    /**
     * 修改事件详情
     */
    @ApiOperation("修改事件详情")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:info:edit')")
    @Log(title = "事件详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseEventInfo baseEventInfo, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventInfo.setUpdateBy(SecurityUtils.getUsername());
        }
        return toAjax(baseEventInfoService.updateBaseEventInfo(baseEventInfo));
    }

    /**
     * 删除事件详情
     */
    @ApiOperation("删除事件详情")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:info:remove')")
    @Log(title = "事件详情", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        Arrays.asList(ids).stream().forEach(v -> {
            BaseEventRecord baseEventRecord = new BaseEventRecord();
            baseEventRecord.setEventId(v);
            List<BaseEventRecord> baseEventRecords = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
            if (baseEventRecords.size() > 0) {
                List<String> collect = baseEventRecords.stream().map(BaseEventRecord::getId).collect(Collectors.toList());
                System.out.println(Arrays.asList(collect));
                baseEventRecordService.deleteBaseEventRecordByIds(collect.toArray(new String[collect.size()]));
            }
        });
        return toAjax(baseEventInfoService.deleteBaseEventInfoByIds(ids));
    }

    /**
     * 软删除事件详情
     */
    @ApiOperation("软删除事件详情")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:info:remove')")
    @Log(title = "事件详情", businessType = BusinessType.DELETE)
    @PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids) {
        Arrays.asList(ids).stream().forEach(v -> {
            BaseEventRecord baseEventRecord = new BaseEventRecord();
            baseEventRecord.setEventId(v);
            List<BaseEventRecord> baseEventRecords = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
            if (baseEventRecords.size() > 0) {
                List<String> collect = baseEventRecords.stream().map(BaseEventRecord::getId).collect(Collectors.toList());
                baseEventRecordService.updateBaseEventRecordByIds(collect.toArray(new String[collect.size()]));
            }


        });
        return toAjax(baseEventInfoService.updateBaseEventInfoByIds(ids));
    }

    /**
     * 处理事件
     */
    @PostMapping("/handleEvents")
    public AjaxResult handleEvents(@RequestBody BaseEventInfo baseEventInfo) {
        String dealWithType = baseEventInfo.getDealWithType();
        baseEventInfo.setEventStatus("处理中");
        if ("1".equals(dealWithType)) {
            baseEventInfo.setCreateBy(SecurityUtils.getUsername());
            baseEventInfoService.updateBaseEventInfo(baseEventInfo);
            //正式环境推送到1+4
            return alarmController.alertPush(baseEventInfo);
            //测试环境因没有办法推送到1+4，所以使用挡板直接返回成功信息
//            return AjaxResult.success("{status:1,Info:'成功'}");
        } else if ("2".equals(dealWithType)) {
            return toAjax(baseEventInfoService.updateBaseEventInfo(baseEventInfo));
        } else {
            return AjaxResult.error("dealWithType is error");
        }
    }

    /**
     * 查询处理结果事件
     */
    @PostMapping("/handleResultEvents")
    public AjaxResult handleResultEvents(@RequestBody BaseEventInfo baseEventInfo) {
        baseEventInfo.setDealWithType("1");
        List<BaseEventInfo> oneAndFourList = baseEventInfoService.selectBaseEventInfoList(baseEventInfo);
        if (StringUtils.isNotEmpty(oneAndFourList)) {
            oneAndFourList.stream().forEach(item -> {
                //查询1+4平台处理结果，正式环境解开注释，测试环境无法查询1+4
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("casecode", item.getId());
                jsonObject.put("rqstsource", null);
                jsonObject.put("rqsttitle", null);
                jsonObject.put("pageindex", null);
                jsonObject.put("pagesize", null);
                String params = jsonObject.toJSONString();
                AjaxResult fromOneFour = alarmController.getFromOneFour(params);
                String resultStr = (String) fromOneFour.get("data");
                JSONObject result = JSONObject.parseObject(resultStr);
                JSONArray data = result.getJSONArray("data");
                JSONObject resultD = (JSONObject) data.get(0);
                //当前步骤
                String lasthandlestep = resultD.getString("lasthandlestep");
                item.setEventStatus(lasthandlestep);
                baseEventInfoService.updateBaseEventInfo(item);
            });
        }
        baseEventInfo.setDealWithType("2");
        List<BaseEventInfo> localList = baseEventInfoService.selectBaseEventInfoList(baseEventInfo);
        oneAndFourList.addAll(localList);
        return AjaxResult.success(oneAndFourList);
    }

    /**
     * 测试环境查询1+4工单状态
     */
    @PostMapping("/getFromOneFourTest")
    public AjaxResult getFromOneFour(@RequestBody String params) {

        return AjaxResult.success("操作成功", JSONArray.parse("[{'rowguid':'lzzsWwJN-AIfa-b9iy-qWRq-NIYF48pD','lasthandlestep':'移动端上报','rqstcontent':'网格员巡查辖区内防违控违情况','rqstsource':'APP上报','rqsttitle':'防违控了','accordtype':'事件类-城市管理-村庄环境-私搭乱建大棚','rqstname':'黄涛','registertime':'2021-04-26','rqstaddress':'扬州市邗江区杨庙镇赵庄村东南方向170米正鼎智能东南约130米','rqstnumber':'19975067101','urgentlevel':'一般','reportFile':['http://172.21.127.3/yzs/rest/attachAction/getContent?isCommondto=true&attachGuid=520c9dbd-98dd-4412-bde3-f2c6d834250d'],'handleFile':['http://172.21.127.3/yzs/rest/attachAction/getContent?isCommondto=true&attachGuid=30dc1596-e5a8-4a3e-a7a4-513f1ab6a32e'],'checkFile':[]}]"));
    }

    /**
     * 测试环境查询1+4工单状态
     */
    @PostMapping("/pushWebSocket")
    public AjaxResult pushWebSocket(@RequestBody String json) {
        InMessage message = new InMessage();
        message.setSendType(Constant.SEND_TYPE_SENDMESSAGE);
        message.setContent(json);
        String msag = JSONObject.toJSONString(message);
        if (SaticScheduleTask.webSocketClient != null && SaticScheduleTask.webSocketClient.isOpen()) {
            SaticScheduleTask.webSocketClient.send(msag);
        } else {
            SaticScheduleTask.KeepAlive();
            SaticScheduleTask.webSocketClient.send(msag);
        }
        return AjaxResult.success();
    }


    /**
     * 修改事件状态并增加一条处置流程
     *
     * @param baseEventInfo
     * @return
     */
    @PostMapping("/updateEventsStatus")
    public AjaxResult updateEventsStatus(@RequestBody BaseEventInfo baseEventInfo) {
        return baseEventInfoService.updateBaseEvents(baseEventInfo);
    }

    /**
     * 事件统计
     *
     * @return
     */
    @PostMapping("/countEvent")
    public List<GroupByVo> countEvent() {
        return baseEventInfoService.countEvent();
    }

    //@Scheduled(cron = "*/5 * * * * ?")

    /**
     * 凌晨1点执行查询1+4所有工单信息
     * 并覆盖式替换
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @GetMapping("/dataFlushTask")
    public void saveAllData() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageindex", 1);
        jsonObject.put("pagesize", 100000);
        String params = jsonObject.toJSONString();
        AjaxResult fromOneFour = alarmController.getFromOneFour(params);
        String resultStr = JSONObject.toJSONString(fromOneFour.get("data"));
        JSONArray data = JSON.parseArray(resultStr);

        List<BaseEventInfo> list = new LinkedList<>();
        for (Object el : data) {
            BaseEventInfo changeElement = JSONObject.parseObject(JSONObject.toJSONString(el))
                    .toJavaObject(BaseEventInfo.class);
            list.add(changeElement);
        }

        baseEventInfoService.saveAllData(list);
    }


    /**
     * 获取事件各个类型的统计数据
     *
     * @return
     */
    @GetMapping(value = "/getAnalysisData")
    public AjaxResult getAnalysisData() {
        return AjaxResult.success(baseEventInfoService.getAnalysisData());
    }

    /**
     * 查询办理人（系统用户）
     */
    @GetMapping(value = "/getSysUserData")
    public TableDataInfo getSysUserData(String communityId, String communityChildId) {
        List<SysUser> list = baseEventInfoService.getSysUserData(communityId, communityChildId);
        return getDataTable(list);
        //return baseEventInfoService.getSysUserData(communityId,communityChildId);
    }

    /**
     * 获取事件分布数据
     *
     * @param baseEventInfo
     * @return
     */
    @PostMapping(value = "/getEventDistribution")
    public AjaxResult sumDataGroupByDivision(@RequestBody BaseEventInfo baseEventInfo) {
        return AjaxResult.success(baseEventInfoService.sumDataGroupByDivision(baseEventInfo));
    }

    @GetMapping(value = "/getHandleAndConclude")
    public AjaxResult getHandleAndConclude(BaseEventInfo baseEventInfo) {
        return AjaxResult.success(baseEventInfoService.getHandleAndConclude(baseEventInfo));
    }

    /**
     * 获取1+4数据
     * @param baseEventInfo
     * @return
     */
    @GetMapping(value = "/getEventData")
    public AjaxResult selectBaseEventInfoDataList(BaseEventInfo baseEventInfo) {
        startPage();
        return AjaxResult.success(baseEventInfoService.selectBaseEventInfoDataList(baseEventInfo));
    }




}
