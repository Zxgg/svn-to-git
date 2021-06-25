package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseBuildingFloor;
import com.isstech.isstechadmin.service.IBaseBuildingFloorService;
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
 * 楼栋楼层 只做记录用，房屋数据直接使用楼层号Controller
 *
 * @author yzp
 * @date 2020-11-30
 */
@Api(tags ="BaseBuildingFloor", description = "楼栋楼层 只做记录用，房屋数据直接使用楼层号")
@RestController
@RequestMapping("/communityUnit/building_floor")
public class BaseBuildingFloorController extends BaseController
{
    @Autowired
    private IBaseBuildingFloorService baseBuildingFloorService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询楼栋楼层 只做记录用，房屋数据直接使用楼层号列表
     */
    @ApiOperation("查询楼栋楼层 只做记录用，房屋数据直接使用楼层号列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseBuildingFloor baseBuildingFloor)
    {
        baseBuildingFloor.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseBuildingFloor.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseBuildingFloor> list = baseBuildingFloorService.selectBaseBuildingFloorList(baseBuildingFloor);
        return getDataTable(list);
    }

    /**
     * 导出楼栋楼层 只做记录用，房屋数据直接使用楼层号列表
     */
    @ApiOperation("导出楼栋楼层 只做记录用，房屋数据直接使用楼层号列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:export')")
    @Log(title = "楼栋楼层 只做记录用，房屋数据直接使用楼层号", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseBuildingFloor baseBuildingFloor)
    {
        List<BaseBuildingFloor> list = baseBuildingFloorService.selectBaseBuildingFloorList(baseBuildingFloor);
        ExcelUtil<BaseBuildingFloor> util = new ExcelUtil<BaseBuildingFloor>(BaseBuildingFloor.class);
        return util.exportExcel(list, "building_floor");
    }

    /**
     * 获取楼栋楼层 只做记录用，房屋数据直接使用楼层号详细信息
     */
    @ApiOperation("获取楼栋楼层 只做记录用，房屋数据直接使用楼层号详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseBuildingFloorService.selectBaseBuildingFloorById(id));
    }

    /**
     * 新增楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @ApiOperation("新增楼栋楼层 只做记录用，房屋数据直接使用楼层号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:add')")
    @Log(title = "楼栋楼层 只做记录用，房屋数据直接使用楼层号", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseBuildingFloor baseBuildingFloor)
    {
        return toAjax(baseBuildingFloorService.insertBaseBuildingFloor(baseBuildingFloor));
    }

    /**
     * 修改楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @ApiOperation("修改楼栋楼层 只做记录用，房屋数据直接使用楼层号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:edit')")
    @Log(title = "楼栋楼层 只做记录用，房屋数据直接使用楼层号", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseBuildingFloor baseBuildingFloor)
    {
        return toAjax(baseBuildingFloorService.updateBaseBuildingFloor(baseBuildingFloor));
    }

    /**
     * 删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @ApiOperation("删除楼栋楼层 只做记录用，房屋数据直接使用楼层号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:remove')")
    @Log(title = "楼栋楼层 只做记录用，房屋数据直接使用楼层号", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseBuildingFloorService.deleteBaseBuildingFloorByIds(ids));
    }

    /**
     * 软删除楼栋楼层 只做记录用，房屋数据直接使用楼层号
     */
    @ApiOperation("删除楼栋楼层 只做记录用，房屋数据直接使用楼层号")
    @PreAuthorize("@ss.hasPermi('communityUnit:building_floor:remove')")
    @Log(title = "楼栋楼层 只做记录用，房屋数据直接使用楼层号", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseBuildingFloorService.updateBaseBuildingFloorByIds(ids));
    }
}
