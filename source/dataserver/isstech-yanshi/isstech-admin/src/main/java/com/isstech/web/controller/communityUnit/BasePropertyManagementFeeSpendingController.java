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
import com.isstech.isstechadmin.domain.BasePropertyManagementFeeSpending;
import com.isstech.isstechadmin.service.IBasePropertyManagementFeeSpendingService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 物业费用支出记录 Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BasePropertyManagementFeeSpending", description = "物业费用支出记录 ")
@RestController
@RequestMapping("/property_company/property_management_fee_spending")
public class BasePropertyManagementFeeSpendingController extends BaseController
{
    @Autowired
    private IBasePropertyManagementFeeSpendingService basePropertyManagementFeeSpendingService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询物业费用支出记录 列表
     */
    @ApiOperation("查询物业费用支出记录 列表")
    //@PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        basePropertyManagementFeeSpending.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        basePropertyManagementFeeSpending.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePropertyManagementFeeSpending> list = basePropertyManagementFeeSpendingService.selectBasePropertyManagementFeeSpendingList(basePropertyManagementFeeSpending);
        return getDataTable(list);
    }

    /**
     * 导出物业费用支出记录 列表
     */
    @ApiOperation("导出物业费用支出记录 列表")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:export')")
    @Log(title = "物业费用支出记录 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        List<BasePropertyManagementFeeSpending> list = basePropertyManagementFeeSpendingService.selectBasePropertyManagementFeeSpendingList(basePropertyManagementFeeSpending);
        ExcelUtil<BasePropertyManagementFeeSpending> util = new ExcelUtil<BasePropertyManagementFeeSpending>(BasePropertyManagementFeeSpending.class);
        return util.exportExcel(list, "property_management_fee_spending");
    }

    /**
     * 获取物业费用支出记录 详细信息
     */
    @ApiOperation("获取物业费用支出记录 详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basePropertyManagementFeeSpendingService.selectBasePropertyManagementFeeSpendingById(id));
    }

    /**
     * 新增物业费用支出记录 
     */
    @ApiOperation("新增物业费用支出记录 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:add')")
    @Log(title = "物业费用支出记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        return toAjax(basePropertyManagementFeeSpendingService.insertBasePropertyManagementFeeSpending(basePropertyManagementFeeSpending));
    }

    /**
     * 修改物业费用支出记录 
     */
    @ApiOperation("修改物业费用支出记录 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:edit')")
    @Log(title = "物业费用支出记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        return toAjax(basePropertyManagementFeeSpendingService.updateBasePropertyManagementFeeSpending(basePropertyManagementFeeSpending));
    }

    /**
     * 删除物业费用支出记录 
     */
    @ApiOperation("删除物业费用支出记录 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:remove')")
    @Log(title = "物业费用支出记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basePropertyManagementFeeSpendingService.deleteBasePropertyManagementFeeSpendingByIds(ids));
    }

    /**
     * 软删除物业费用支出记录 
     */
    @ApiOperation("软删除物业费用支出记录 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_management_fee_spending:remove')")
    @Log(title = "物业费用支出记录 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(basePropertyManagementFeeSpendingService.updateBasePropertyManagementFeeSpendingByIds(ids));
    }
}
