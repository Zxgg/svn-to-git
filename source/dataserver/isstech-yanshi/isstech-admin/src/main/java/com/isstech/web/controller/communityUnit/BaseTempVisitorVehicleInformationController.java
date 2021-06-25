package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseTempVisitorVehicleInformation;
import com.isstech.isstechadmin.service.IBaseTempVisitorVehicleInformationService;
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
 * 临时访客车辆信息Controller
 *
 * @author isstech
 * @date 2020-11-23
 */
@Api(tags ="BaseTempVisitorVehicleInformation", description = "临时访客车辆信息")
@RestController
@RequestMapping("/passManage/temp_visitor_vehicle")
public class BaseTempVisitorVehicleInformationController extends BaseController
{
    @Autowired
    private IBaseTempVisitorVehicleInformationService baseTempVisitorVehicleInformationService;

    /**
     * 查询临时访客车辆信息列表
     */
    @ApiOperation("查询临时访客车辆信息列表")
//    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        startPage();
        List<BaseTempVisitorVehicleInformation> list = baseTempVisitorVehicleInformationService.selectBaseTempVisitorVehicleInformationList(baseTempVisitorVehicleInformation);
        return getDataTable(list);
    }

    /**
     * 导出临时访客车辆信息列表
     */
    @ApiOperation("导出临时访客车辆信息列表")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:export')")
    @Log(title = "临时访客车辆信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        List<BaseTempVisitorVehicleInformation> list = baseTempVisitorVehicleInformationService.selectBaseTempVisitorVehicleInformationList(baseTempVisitorVehicleInformation);
        ExcelUtil<BaseTempVisitorVehicleInformation> util = new ExcelUtil<BaseTempVisitorVehicleInformation>(BaseTempVisitorVehicleInformation.class);
        return util.exportExcel(list, "temp_visitor_vehicle");
    }

    /**
     * 获取临时访客车辆信息详细信息
     */
    @ApiOperation("获取临时访客车辆信息详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseTempVisitorVehicleInformationService.selectBaseTempVisitorVehicleInformationById(id));
    }

    /**
     * 新增临时访客车辆信息
     */
    @ApiOperation("新增临时访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:add')")
    @Log(title = "临时访客车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        return toAjax(baseTempVisitorVehicleInformationService.insertBaseTempVisitorVehicleInformation(baseTempVisitorVehicleInformation));
    }

    /**
     * 修改临时访客车辆信息
     */
    @ApiOperation("修改临时访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:edit')")
    @Log(title = "临时访客车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        return toAjax(baseTempVisitorVehicleInformationService.updateBaseTempVisitorVehicleInformation(baseTempVisitorVehicleInformation));
    }

    /**
     * 删除临时访客车辆信息
     */
    @ApiOperation("删除临时访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:remove')")
    @Log(title = "临时访客车辆信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseTempVisitorVehicleInformationService.deleteBaseTempVisitorVehicleInformationByIds(ids));
    }
    /**
     * 软删除临时访客车辆信息
     */
    @ApiOperation("软删除临时访客车辆信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_vehicle:remove')")
    @Log(title = "临时访客车辆信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseTempVisitorVehicleInformationService.updateBaseTempVisitorVehicleInformationByIds(ids));
    }
}
