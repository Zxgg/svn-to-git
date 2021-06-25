package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseVisitorVehicleInformation;
import com.isstech.isstechadmin.service.IBaseVisitorVehicleInformationService;
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
 * 访客车辆信息Controller
 *
 * @author isstech
 * @date 2020-11-23
 */
@Api(tags ="BaseVisitorVehicleInformation", description = "访客车辆信息")
@RestController
@RequestMapping("/passManage/visitVehicle")
public class BaseVisitorVehicleInformationController extends BaseController
{
    @Autowired
    private IBaseVisitorVehicleInformationService baseVisitorVehicleInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询访客车辆信息列表
     */
    @ApiOperation("查询访客车辆信息列表")
//    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        baseVisitorVehicleInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseVisitorVehicleInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseVisitorVehicleInformation> list = baseVisitorVehicleInformationService.selectBaseVisitorVehicleInformationList(baseVisitorVehicleInformation);
        return getDataTable(list);
    }

    /**
     * 导出访客车辆信息列表
     */
    @ApiOperation("导出访客车辆信息列表")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:export')")
    @Log(title = "访客车辆信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        List<BaseVisitorVehicleInformation> list = baseVisitorVehicleInformationService.selectBaseVisitorVehicleInformationList(baseVisitorVehicleInformation);
        ExcelUtil<BaseVisitorVehicleInformation> util = new ExcelUtil<BaseVisitorVehicleInformation>(BaseVisitorVehicleInformation.class);
        return util.exportExcel(list, "visitVehicle");
    }

    /**
     * 获取访客车辆信息详细信息
     */
    @ApiOperation("获取访客车辆信息详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVisitorVehicleInformationService.selectBaseVisitorVehicleInformationById(id));
    }

    /**
     * 新增访客车辆信息
     */
    @ApiOperation("新增访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:add')")
    @Log(title = "访客车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        return toAjax(baseVisitorVehicleInformationService.insertBaseVisitorVehicleInformation(baseVisitorVehicleInformation));
    }

    /**
     * 修改访客车辆信息
     */
    @ApiOperation("修改访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:edit')")
    @Log(title = "访客车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        return toAjax(baseVisitorVehicleInformationService.updateBaseVisitorVehicleInformation(baseVisitorVehicleInformation));
    }

    /**
     * 删除访客车辆信息
     */
    @ApiOperation("删除访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:remove')")
    @Log(title = "访客车辆信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVisitorVehicleInformationService.deleteBaseVisitorVehicleInformationByIds(ids));
    }

    /**
     * 软删除访客车辆信息
     */
    @ApiOperation("软删除访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitVehicle:remove')")
    @Log(title = "访客车辆信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVisitorVehicleInformationService.updateBaseVisitorVehicleInformationByIds(ids));
    }
}
