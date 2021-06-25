package com.isstech.web.controller.communityEvent;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.StringUtils;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.TPersonEpidemicState;
import com.isstech.isstechadmin.service.ITPersonEpidemicStateService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 疫情信息收集Controller
 *
 * @author yzp
 * @date 2021-01-15
 */
@Api(tags ="TPersonEpidemicState", description = "疫情信息收集")
@RestController
@RequestMapping("/epidemic/person_epidemic_state_record")
public class TPersonEpidemicStateController extends BaseController
{
    @Autowired
    private ITPersonEpidemicStateService tPersonEpidemicStateService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询疫情信息收集列表
     */
    @ApiOperation("查询疫情信息收集列表")
    //@PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPersonEpidemicState tPersonEpidemicState)
    {
        tPersonEpidemicState.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tPersonEpidemicState.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TPersonEpidemicState> list = tPersonEpidemicStateService.selectTPersonEpidemicStateList(tPersonEpidemicState);
        return getDataTable(list);
    }

    /**
     * 导出疫情信息收集列表
     */
    @ApiOperation("导出疫情信息收集列表")
    @PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:export')")
    @Log(title = "疫情信息收集", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TPersonEpidemicState tPersonEpidemicState)
    {
        List<TPersonEpidemicState> list = tPersonEpidemicStateService.selectTPersonEpidemicStateList(tPersonEpidemicState);
        list.stream().forEach(item ->{
            if(StringUtils.isNotEmpty(item.getLeaveLocal())&&StringUtils.isNotEmpty(item.getLeaveInformation())&&"是".equals(item.getLeaveLocal())){
                String leaveInformationArray = item.getLeaveInformation();
                JSONArray parse = JSONArray.parseArray(leaveInformationArray);
                StringBuffer leaveInformation = new StringBuffer();
                parse.forEach(arr ->{
                    JSONObject map = (JSONObject) arr;
                    leaveInformation.append(map.get("arriveCityArea")+" ");
                    leaveInformation.append(map.get("arriveData")+" ");
                    leaveInformation.append(map.get("leaveData")+" ");
                    leaveInformation.append(map.get("arriveTransportationTypes")+" ");
                    leaveInformation.append(map.get("arriveTransportationValues")+";");
                });
                item.setLeaveInformation(leaveInformation.toString());
            }
        });
        ExcelUtil<TPersonEpidemicState> util = new ExcelUtil<TPersonEpidemicState>(TPersonEpidemicState.class);
        return util.exportExcel(list, "person_epidemic_state_record");
    }

    /**
     * 获取疫情信息收集详细信息
     */
    @ApiOperation("获取疫情信息收集详细信息")
    @PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tPersonEpidemicStateService.selectTPersonEpidemicStateById(id));
    }

    /**
     * 新增疫情信息收集-外部扫码收集
     */
    @ApiOperation("新增疫情信息收集")
    //@PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:add')")
    @Log(title = "疫情信息收集", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult addFromOut(@RequestBody TPersonEpidemicState tPersonEpidemicState)
    {
        return toAjax(tPersonEpidemicStateService.insertTPersonEpidemicState(tPersonEpidemicState));
    }

    /**
     * 新增疫情信息收集
     */
    @ApiOperation("新增疫情信息收集")
    //@PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:add')")
    @Log(title = "疫情信息收集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPersonEpidemicState tPersonEpidemicState)
    {
        return toAjax(tPersonEpidemicStateService.insertTPersonEpidemicState(tPersonEpidemicState));
    }

    /**
     * 修改疫情信息收集
     */
    @ApiOperation("修改疫情信息收集")
    @PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:edit')")
    @Log(title = "疫情信息收集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPersonEpidemicState tPersonEpidemicState)
    {
        return toAjax(tPersonEpidemicStateService.updateTPersonEpidemicState(tPersonEpidemicState));
    }

    /**
     * 删除疫情信息收集
     */
    @ApiOperation("删除疫情信息收集")
    @PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:remove')")
    @Log(title = "疫情信息收集", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tPersonEpidemicStateService.deleteTPersonEpidemicStateByIds(ids));
    }

    /**
     * 软删除疫情信息收集
     */
    @ApiOperation("软删除疫情信息收集")
    @PreAuthorize("@ss.hasPermi('epidemic:person_epidemic_state_record:remove')")
    @Log(title = "疫情信息收集", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tPersonEpidemicStateService.updateTPersonEpidemicStateByIds(ids));
    }
}
