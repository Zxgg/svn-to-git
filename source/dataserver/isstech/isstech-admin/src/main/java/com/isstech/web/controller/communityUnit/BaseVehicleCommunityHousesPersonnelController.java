package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseVehicleCommunityHousesPersonnel;
import com.isstech.isstechadmin.domain.vo.VehComHousesPersonVo;
import com.isstech.isstechadmin.service.IBaseVehicleCommunityHousesPersonnelService;
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
 * 车辆和社区房屋和人员关联 Controller
 *
 * @author yzp
 * @date 2020-11-30
 */
@Api(tags ="BaseVehicleCommunityHousesPersonnel", description = "车辆和社区房屋和人员关联 ")
@RestController
@RequestMapping("/communityUnit/vehicle_houses_personnel")
public class BaseVehicleCommunityHousesPersonnelController extends BaseController
{
    @Autowired
    private IBaseVehicleCommunityHousesPersonnelService baseVehicleCommunityHousesPersonnelService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询车辆和社区房屋和人员关联 列表
     */
    @ApiOperation("查询车辆和社区房屋和人员关联 列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(VehComHousesPersonVo vehComHousesPersonVo)
    {
        vehComHousesPersonVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        vehComHousesPersonVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<VehComHousesPersonVo> list = baseVehicleCommunityHousesPersonnelService.selectVehComHousesPersonVoList(vehComHousesPersonVo);
        return getDataTable(list);
    }

    /**
     * 导出车辆和社区房屋和人员关联 列表
     */
    @ApiOperation("导出车辆和社区房屋和人员关联 列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:export')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VehComHousesPersonVo vehComHousesPersonVo)
    {
        List<VehComHousesPersonVo> list = baseVehicleCommunityHousesPersonnelService.selectVehComHousesPersonVoList(vehComHousesPersonVo);
        ExcelUtil<VehComHousesPersonVo> util = new ExcelUtil<VehComHousesPersonVo>(VehComHousesPersonVo.class);
        return util.exportExcel(list, "vehicle_houses_personnel");
    }
/*
    @ApiOperation("导出车辆和社区房屋和人员关联 列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:export')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        List<BaseVehicleCommunityHousesPersonnel> list = baseVehicleCommunityHousesPersonnelService.selectBaseVehicleCommunityHousesPersonnelList(baseVehicleCommunityHousesPersonnel);
        ExcelUtil<BaseVehicleCommunityHousesPersonnel> util = new ExcelUtil<BaseVehicleCommunityHousesPersonnel>(BaseVehicleCommunityHousesPersonnel.class);
        return util.exportExcel(list, "vehicle_houses_personnel");
    }
*/

    /**
     * 获取车辆和社区房屋和人员关联 详细信息
     */
    @ApiOperation("获取车辆和社区房屋和人员关联 详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVehicleCommunityHousesPersonnelService.selectBaseVehicleCommunityHousesPersonnelById(id));
    }

    /**
     * 新增车辆和社区房屋和人员关联 
     */
    @ApiOperation("新增车辆和社区房屋和人员关联 ")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:add')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        return toAjax(baseVehicleCommunityHousesPersonnelService.insertBaseVehicleCommunityHousesPersonnel(baseVehicleCommunityHousesPersonnel));
    }

    /**
     * 修改车辆和社区房屋和人员关联 
     */
    @ApiOperation("修改车辆和社区房屋和人员关联 ")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:edit')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        return toAjax(baseVehicleCommunityHousesPersonnelService.updateBaseVehicleCommunityHousesPersonnel(baseVehicleCommunityHousesPersonnel));
    }

    /**
     * 删除车辆和社区房屋和人员关联 
     */
    @ApiOperation("删除车辆和社区房屋和人员关联 ")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:remove')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVehicleCommunityHousesPersonnelService.deleteBaseVehicleCommunityHousesPersonnelByIds(ids));
    }
    /**
     * 软删除车辆和社区房屋和人员关联
     */
    @ApiOperation("软删除车辆和社区房屋和人员关联 ")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_houses_personnel:remove')")
    @Log(title = "车辆和社区房屋和人员关联 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVehicleCommunityHousesPersonnelService.updateBaseVehicleCommunityHousesPersonnelByIds(ids));
    }
}
