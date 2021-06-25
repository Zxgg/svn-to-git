package com.isstech.web.controller.communityUnit;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.BaseEventRecord;
import com.isstech.isstechadmin.service.IBaseEventInfoService;
import com.isstech.isstechadmin.service.IBaseEventRecordService;
import com.isstech.isstechadmin.service.ISysDictDataService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 事件Controller
 *
 * @author yzp
 * @date 2021-03-30
 */
@Api(tags = "BaseEventRecord", description = "事件")
@RestController
@RequestMapping("/property_company/base_event_record")
public class BaseEventRecordController extends BaseController {
    @Autowired
    private IBaseEventRecordService baseEventRecordService;
    @Autowired
    private IBaseEventInfoService baseEventInfoService;
    @Autowired
    private ISysDictDataService sysDictDataService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    @GetMapping("/getneweventrecord/{eventid}")
    public AjaxResult getnewevent(@PathVariable String eventid) {
        BaseEventRecord baseEventRecord = new BaseEventRecord();
        baseEventRecord.setEventId(eventid);
        List<BaseEventRecord> baseEventRecords = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
        if (baseEventRecords.size() > 0) {
            BaseEventInfo baseEventInfo = baseEventInfoService.selectBaseEventInfoById(baseEventRecords.get(0).getEventId());
            BaseEventRecord baseEventRecord1 = baseEventRecords.stream().sorted(Comparator.comparing(BaseEventRecord::getCreateTime).reversed()).findFirst().map(
                    v -> {
                        Optional.ofNullable(baseEventInfo.getAddress()).ifPresent(f -> v.setAddress(f));
                        Optional.ofNullable(baseEventInfo.getEventDegree()).ifPresent(f -> v.setEventDegree(f));
                        Optional.ofNullable(baseEventInfo.getEventType()).ifPresent(f -> v.setEventType(f));
                        Optional.ofNullable(baseEventInfo.getReportingTime()).ifPresent(f -> v.setReportingTime(f));
                        Optional.ofNullable(baseEventInfo.getFileId()).ifPresent(f -> v.setEventInfoFileId(f));
                        Optional.ofNullable(baseEventInfo.getHandleById()).ifPresent(f -> v.setHandleById(f));
                        Optional.ofNullable(baseEventInfo.getEventStatus()).ifPresent(f -> v.setEventStatus(f));
                        return v;
                    }).get();
            return AjaxResult.success(baseEventRecord1);
        }
        return AjaxResult.success("数据为空");
    }

    @GetMapping("/getgroupstatus/{eventid}")
    public AjaxResult getgroupstatus(@PathVariable String eventid) {
        BaseEventRecord baseEventRecord = new BaseEventRecord();
        baseEventRecord.setEventId(eventid);
        List<BaseEventRecord> baseEventRecords = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
        if (baseEventRecords.size() > 0) {
            Map<String, List<BaseEventRecord>> collect = baseEventRecords.stream().sorted(Comparator.comparing(BaseEventRecord::getCreateTime))
                    .collect(Collectors.groupingBy(BaseEventRecord::getEventStatus, LinkedHashMap::new, Collectors.toList()));
            List<BaseEventRecord> collect1 = collect.entrySet().stream().map(v -> {
                List<BaseEventRecord> value = v.getValue();
                Collections.reverse(value);
                return value.get(0);
            }).collect(Collectors.toList());
            return AjaxResult.success(collect1);
        }
        return AjaxResult.success("数据为空");
    }

    /**
     * 查询事件列表
     */
    @ApiOperation("查询事件列表")
    //@PreAuthorize("@ss.hasPermi('isstechadmin:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseEventRecord baseEventRecord, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventRecord.setAllDataAccess(dataAccessUtils.isAllDataAccess());
            baseEventRecord.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        }
        startPage();
        List<BaseEventRecord> list = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
        return getDataTable(list.stream().sorted(Comparator.comparing(BaseEventRecord::getEventStatusSon)).collect(Collectors.toList()));
    }

    /**
     * 导出事件列表
     */
    @ApiOperation("导出事件列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:record:export')")
    @Log(title = "事件", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseEventRecord baseEventRecord) {
        List<BaseEventRecord> list = baseEventRecordService.selectBaseEventRecordList(baseEventRecord);
        ExcelUtil<BaseEventRecord> util = new ExcelUtil<BaseEventRecord>(BaseEventRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取事件详细信息
     */
    @ApiOperation("获取事件详细信息")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id) {
        return AjaxResult.success(baseEventRecordService.selectBaseEventRecordById(id));
    }

    /**
     * 新增事件
     */
    @ApiOperation("新增事件")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:record:add')")
    @Log(title = "事件", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseEventRecord baseEventRecord, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventRecord.setCreateBy(SecurityUtils.getLoginUser().getUser().getNickName());
        }
        BaseEventInfo baseEventInfo = new BaseEventInfo();
        baseEventInfo.setId(baseEventRecord.getEventId());
        baseEventInfo.setEventStatus(baseEventRecord.getEventStatusSon());
        if (StringUtils.isNotEmpty(baseEventInfo.getEventType())) {
            baseEventInfo.setEventType(sysDictDataService.selectDictLabel("base_event_type",
                    baseEventInfo.getEventType()));
        }
        if (baseEventRecord.getEventStatusSon().equals("6")) {
            baseEventInfo.setEventStatus("7");
            baseEventRecord.setEventStatusSon("7");
        }
        baseEventInfoService.updateBaseEventInfo(baseEventInfo);
        BaseEventInfo baseEventInfo1 = baseEventInfoService.selectBaseEventInfoById(baseEventRecord.getEventId());
        baseEventRecord.setReportingTime(baseEventInfo1.getReportingTime());
        baseEventRecord.setEventContent(baseEventInfo1.getContent());
        baseEventRecord.setHandleBy(SecurityUtils.getLoginUser().getNickName());


        return toAjax(baseEventRecordService.insertBaseEventRecord(baseEventRecord));
    }

    public BaseEventInfo changeStatus(BaseEventInfo baseEventInfo) {
        if (baseEventInfo.getEventStatus().contains("0")) {
            // 受理
            baseEventInfo.setEventStatus("1");
        } else if (baseEventInfo.getEventStatus().contains("2")) {
            // 办结
            baseEventInfo.setEventStatus("4");
        } else if (baseEventInfo.getEventStatus().contains("3")) {
            // 归档
            baseEventInfo.setEventStatus("5");
        }
        return baseEventInfo;
    }

    /**
     * 修改事件
     */
    @ApiOperation("修改事件")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:record:edit')")
    @Log(title = "事件", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseEventRecord baseEventRecord, HttpServletRequest request) {
        if (StringUtils.isNotEmpty(request.getHeader("Authorization"))) {
            baseEventRecord.setUpdateBy(SecurityUtils.getUsername());
        }
        return toAjax(baseEventRecordService.updateBaseEventRecord(baseEventRecord));
    }

    /**
     * 删除事件
     */
    @ApiOperation("删除事件")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:record:remove')")
    @Log(title = "事件", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(baseEventRecordService.deleteBaseEventRecordByIds(ids));
    }

    /**
     * 软删除事件
     */
    @ApiOperation("软删除事件")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:record:remove')")
    @Log(title = "事件", businessType = BusinessType.DELETE)
    @PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids) {
        return toAjax(baseEventRecordService.updateBaseEventRecordByIds(ids));
    }
}
