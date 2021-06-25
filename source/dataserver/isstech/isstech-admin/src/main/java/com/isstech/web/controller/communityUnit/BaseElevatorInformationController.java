package com.isstech.web.controller.communityUnit;

import java.util.List;
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
import com.isstech.isstechadmin.domain.BaseElevatorInformation;
import com.isstech.isstechadmin.service.IBaseElevatorInformationService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 电梯信息Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BaseElevatorInformation", description = "电梯信息")
@RestController
@RequestMapping("/property_company/elevator_information")
public class BaseElevatorInformationController extends BaseController
{
    @Autowired
    private IBaseElevatorInformationService baseElevatorInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询电梯信息列表
     */
    @ApiOperation("查询电梯信息列表")
    //@PreAuthorize("@ss.hasPermi('property_company:elevator_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseElevatorInformation baseElevatorInformation)
    {
        baseElevatorInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseElevatorInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseElevatorInformation> list = baseElevatorInformationService.selectBaseElevatorInformationList(baseElevatorInformation);
        return getDataTable(list);
    }

    /**
     * 导出电梯信息列表
     */
    @ApiOperation("导出电梯信息列表")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:export')")
    @Log(title = "电梯信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseElevatorInformation baseElevatorInformation)
    {
        List<BaseElevatorInformation> list = baseElevatorInformationService.selectBaseElevatorInformationList(baseElevatorInformation);
        ExcelUtil<BaseElevatorInformation> util = new ExcelUtil<BaseElevatorInformation>(BaseElevatorInformation.class);
        return util.exportExcel(list, "elevator_information");
    }

    /**
     * 获取电梯信息详细信息
     */
    @ApiOperation("获取电梯信息详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseElevatorInformationService.selectBaseElevatorInformationById(id));
    }

    /**
     * 新增电梯信息
     */
    @ApiOperation("新增电梯信息")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:add')")
    @Log(title = "电梯信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseElevatorInformation baseElevatorInformation)
    {
        return toAjax(baseElevatorInformationService.insertBaseElevatorInformation(baseElevatorInformation));
    }

    /**
     * 修改电梯信息
     */
    @ApiOperation("修改电梯信息")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:edit')")
    @Log(title = "电梯信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseElevatorInformation baseElevatorInformation)
    {
        return toAjax(baseElevatorInformationService.updateBaseElevatorInformation(baseElevatorInformation));
    }

    /**
     * 删除电梯信息
     */
    @ApiOperation("删除电梯信息")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:remove')")
    @Log(title = "电梯信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseElevatorInformationService.deleteBaseElevatorInformationByIds(ids));
    }

    /**
     * 软删除电梯信息
     */
    @ApiOperation("软删除电梯信息")
    @PreAuthorize("@ss.hasPermi('property_company:elevator_information:remove')")
    @Log(title = "电梯信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseElevatorInformationService.updateBaseElevatorInformationByIds(ids));
    }
}
