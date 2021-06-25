package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.vo.PassVehicleVo;
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
import com.isstech.isstechadmin.domain.BasePassVehicle;
import com.isstech.isstechadmin.service.IBasePassVehicleService;

/**
 * 车辆通行Controller
 *
 * @author yzp
 * @date 2020-12-12
 */
@Api(tags ="BasePassVehicle", description = "车辆通行")
@RestController
@RequestMapping("/passManage/pass_vehicle")
public class BasePassVehicleController extends BaseController
{
    @Autowired
    private IBasePassVehicleService basePassVehicleService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询车辆通行列表
     */
    @ApiOperation("查询车辆通行列表")
//    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(PassVehicleVo passVehicleVo)
    {
        passVehicleVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        passVehicleVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePassVehicle> list = basePassVehicleService.selectBasePassVehicleList(passVehicleVo);
        return getDataTable(list);
    }

    /**
     * 导出车辆通行列表
     */
    @ApiOperation("导出车辆通行列表")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:export')")
    @Log(title = "车辆通行", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PassVehicleVo passVehicleVo)
    {
        List<BasePassVehicle> list = basePassVehicleService.selectBasePassVehicleList(passVehicleVo);
        ExcelUtil<BasePassVehicle> util = new ExcelUtil<BasePassVehicle>(BasePassVehicle.class);
        return util.exportExcel(list, "pass_vehicle");
    }

    /**
     * 获取车辆通行详细信息
     */
    @ApiOperation("获取车辆通行详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:query')")
    @GetMapping(value = "/{motorVehicleId}")
    public AjaxResult getInfo(@PathVariable("motorVehicleId") String motorVehicleId)
    {
        return AjaxResult.success(basePassVehicleService.selectBasePassVehicleById(motorVehicleId));
    }

    /**
     * 新增车辆通行
     */
    @ApiOperation("新增车辆通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:add')")
    @Log(title = "车辆通行", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePassVehicle basePassVehicle)
    {
        return toAjax(basePassVehicleService.insertBasePassVehicle(basePassVehicle));
    }

    /**
     * 修改车辆通行
     */
    @ApiOperation("修改车辆通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:edit')")
    @Log(title = "车辆通行", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePassVehicle basePassVehicle)
    {
        return toAjax(basePassVehicleService.updateBasePassVehicle(basePassVehicle));
    }

    /**
     * 删除车辆通行
     */
    @ApiOperation("删除车辆通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:remove')")
    @Log(title = "车辆通行", businessType = BusinessType.DELETE)
	@DeleteMapping("/{motorVehicleIds}")
    public AjaxResult remove(@PathVariable String[] motorVehicleIds)
    {
        return toAjax(basePassVehicleService.deleteBasePassVehicleByIds(motorVehicleIds));
    }
    /**
     * 软删除车辆通行
     */
    @ApiOperation("删除车辆通行")
    @PreAuthorize("@ss.hasPermi('passManage:pass_vehicle:remove')")
    @Log(title = "车辆通行", businessType = BusinessType.DELETE)
	@PutMapping("/{motorVehicleIds}")
    public AjaxResult update(@PathVariable String[] motorVehicleIds)
    {
        return toAjax(basePassVehicleService.updateBasePassVehicleByIds(motorVehicleIds));
    }
}
