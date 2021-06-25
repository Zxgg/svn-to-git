package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseBuildingUnit;
import com.isstech.isstechadmin.service.IBaseBuildingUnitService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
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
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 楼栋单元 只做记录用，房屋数据直接使用单元号Controller
 *
 * @author yzp
 * @date 2020-11-30
 */
@Api(tags ="BaseBuildingUnit", description = "楼栋单元 只做记录用，房屋数据直接使用单元号")
@RestController
@RequestMapping("/communityUnit/building_unit")
public class BaseBuildingUnitController extends BaseController
{
    @Autowired
    private IBaseBuildingUnitService baseBuildingUnitService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询楼栋单元 只做记录用，房屋数据直接使用单元号列表
     */
    @ApiOperation("查询楼栋单元 只做记录用，房屋数据直接使用单元号列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseBuildingUnit baseBuildingUnit)
    {
        baseBuildingUnit.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseBuildingUnit.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseBuildingUnit> list = baseBuildingUnitService.selectBaseBuildingUnitList(baseBuildingUnit);
        return getDataTable(list);
    }

    /**
     * 导出楼栋单元 只做记录用，房屋数据直接使用单元号列表
     */
    @ApiOperation("导出楼栋单元 只做记录用，房屋数据直接使用单元号列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:export')")
    @Log(title = "楼栋单元 只做记录用，房屋数据直接使用单元号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseBuildingUnit baseBuildingUnit)
    {
        List<BaseBuildingUnit> list = baseBuildingUnitService.selectBaseBuildingUnitList(baseBuildingUnit);
        ExcelUtil<BaseBuildingUnit> util = new ExcelUtil<BaseBuildingUnit>(BaseBuildingUnit.class);
        return util.exportExcel(list, "building_unit");
    }

    /**
     * 获取楼栋单元 只做记录用，房屋数据直接使用单元号详细信息
     */
    @ApiOperation("获取楼栋单元 只做记录用，房屋数据直接使用单元号详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseBuildingUnitService.selectBaseBuildingUnitById(id));
    }

    /**
     * 新增楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @ApiOperation("新增楼栋单元 只做记录用，房屋数据直接使用单元号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:add')")
    @Log(title = "楼栋单元 只做记录用，房屋数据直接使用单元号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseBuildingUnit baseBuildingUnit)
    {
        return toAjax(baseBuildingUnitService.insertBaseBuildingUnit(baseBuildingUnit));
    }

    /**
     * 修改楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @ApiOperation("修改楼栋单元 只做记录用，房屋数据直接使用单元号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:edit')")
    @Log(title = "楼栋单元 只做记录用，房屋数据直接使用单元号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseBuildingUnit baseBuildingUnit)
    {
        return toAjax(baseBuildingUnitService.updateBaseBuildingUnit(baseBuildingUnit));
    }

    /**
     * 删除楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @ApiOperation("删除楼栋单元 只做记录用，房屋数据直接使用单元号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:remove')")
    @Log(title = "楼栋单元 只做记录用，房屋数据直接使用单元号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseBuildingUnitService.deleteBaseBuildingUnitByIds(ids));
    }

    /**
     * 软删除楼栋单元 只做记录用，房屋数据直接使用单元号
     */
    @ApiOperation("删除楼栋单元 只做记录用，房屋数据直接使用单元号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_unit:remove')")
    @Log(title = "楼栋单元 只做记录用，房屋数据直接使用单元号", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseBuildingUnitService.updateBaseBuildingUnitByIds(ids));
    }
}
