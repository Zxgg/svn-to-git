package com.isstech.web.controller.communityEvent;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.mapper.BasePassAnalysisTaskMapper;
import com.isstech.web.service.DataProcessingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.enums.BusinessType;
import com.isstech.isstechadmin.domain.TTaskVehicleRelation;
import com.isstech.isstechadmin.service.ITTaskVehicleRelationService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

import javax.annotation.PostConstruct;

/**
 * 车辆对应关系任务 Controller
 *
 * @author yzp
 * @date 2020-12-24
 */
@Api(tags ="TTaskVehicleRelation", description = "车辆对应关系任务 ")
@RestController
@RequestMapping("/taskVehicle/relation")
public class TTaskVehicleRelationController extends BaseController
{
    @Autowired
    private ITTaskVehicleRelationService tTaskVehicleRelationService;
    @Autowired
    private BasePassAnalysisTaskMapper basePassAnalysisTaskMapper;
    @Autowired
    private DataProcessingService dataProcessingService;

    /**
     * 查询车辆对应关系任务 列表
     */
    @ApiOperation("查询车辆对应关系任务 列表")
    @GetMapping("/list")
    public TableDataInfo list(TTaskVehicleRelation tTaskVehicleRelation)
    {
        startPage();
        List<TTaskVehicleRelation> list = tTaskVehicleRelationService.selectTTaskVehicleRelationList(tTaskVehicleRelation);
        return getDataTable(list);
    }

    /**
     * 导出车辆对应关系任务 列表
     */
    @ApiOperation("导出车辆对应关系任务 列表")
    @PreAuthorize("@ss.hasPermi('taskVehicle:relation:export')")
    @Log(title = "车辆对应关系任务 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TTaskVehicleRelation tTaskVehicleRelation)
    {
        List<TTaskVehicleRelation> list = tTaskVehicleRelationService.selectTTaskVehicleRelationList(tTaskVehicleRelation);
        ExcelUtil<TTaskVehicleRelation> util = new ExcelUtil<TTaskVehicleRelation>(TTaskVehicleRelation.class);
        return util.exportExcel(list, "relation");
    }

    /**
     * 获取车辆对应关系任务 详细信息
     */
    @ApiOperation("获取车辆对应关系任务 详细信息")
    @PreAuthorize("@ss.hasPermi('taskVehicle:relation:query')")
    @GetMapping(value = "/{plateId}")
    public AjaxResult getInfo(@PathVariable("plateId") String plateId)
    {
        return AjaxResult.success(tTaskVehicleRelationService.selectTTaskVehicleRelationById(plateId));
    }

    /**
     * 新增车辆对应关系任务
     */
    @ApiOperation("新增车辆对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('taskVehicle:relation:add')")
    @Log(title = "车辆对应关系任务 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TTaskVehicleRelation tTaskVehicleRelation)
    {
        return toAjax(tTaskVehicleRelationService.insertTTaskVehicleRelation(tTaskVehicleRelation));
    }

    /**
     * 修改车辆对应关系任务
     */
    @ApiOperation("修改车辆对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('taskVehicle:relation:edit')")
    @Log(title = "车辆对应关系任务 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TTaskVehicleRelation tTaskVehicleRelation)
    {
        return toAjax(tTaskVehicleRelationService.updateTTaskVehicleRelation(tTaskVehicleRelation));
    }

    /**
     * 删除车辆对应关系任务
     */
    @ApiOperation("删除车辆对应关系任务 ")
    @PreAuthorize("@ss.hasPermi('taskVehicle:relation:remove')")
    @Log(title = "车辆对应关系任务 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{plateIds}")
    public AjaxResult remove(@PathVariable String[] plateIds)
    {
        return toAjax(tTaskVehicleRelationService.deleteTTaskVehicleRelationByIds(plateIds));
    }

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init()
    {
        //车辆黑名单
        List<Map<String, String>> maps = basePassAnalysisTaskMapper.querySuspectIdsByRoster03();
        dataProcessingService.sycVehicle(maps,"02");
    }

}
