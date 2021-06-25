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
import com.isstech.isstechadmin.domain.BaseEntranceGuardApply;
import com.isstech.isstechadmin.service.IBaseEntranceGuardApplyService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 *  门禁申请Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BaseEntranceGuardApply", description = " 门禁申请")
@RestController
@RequestMapping("/property_company/entrance_guard_apply")
public class BaseEntranceGuardApplyController extends BaseController
{
    @Autowired
    private IBaseEntranceGuardApplyService baseEntranceGuardApplyService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询 门禁申请列表
     */
    @ApiOperation("查询 门禁申请列表")
    //@PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseEntranceGuardApply baseEntranceGuardApply)
    {
        baseEntranceGuardApply.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseEntranceGuardApply.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseEntranceGuardApply> list = baseEntranceGuardApplyService.selectBaseEntranceGuardApplyList(baseEntranceGuardApply);
        return getDataTable(list);
    }

    /**
     * 导出 门禁申请列表
     */
    @ApiOperation("导出 门禁申请列表")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:export')")
    @Log(title = " 门禁申请", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseEntranceGuardApply baseEntranceGuardApply)
    {
        List<BaseEntranceGuardApply> list = baseEntranceGuardApplyService.selectBaseEntranceGuardApplyList(baseEntranceGuardApply);
        ExcelUtil<BaseEntranceGuardApply> util = new ExcelUtil<BaseEntranceGuardApply>(BaseEntranceGuardApply.class);
        return util.exportExcel(list, "entrance_guard_apply");
    }

    /**
     * 获取 门禁申请详细信息
     */
    @ApiOperation("获取 门禁申请详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseEntranceGuardApplyService.selectBaseEntranceGuardApplyById(id));
    }

    /**
     * 新增 门禁申请
     */
    @ApiOperation("新增 门禁申请")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:add')")
    @Log(title = " 门禁申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseEntranceGuardApply baseEntranceGuardApply)
    {
        return toAjax(baseEntranceGuardApplyService.insertBaseEntranceGuardApply(baseEntranceGuardApply));
    }

    /**
     * 修改 门禁申请
     */
    @ApiOperation("修改 门禁申请")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:edit')")
    @Log(title = " 门禁申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseEntranceGuardApply baseEntranceGuardApply)
    {
        return toAjax(baseEntranceGuardApplyService.updateBaseEntranceGuardApply(baseEntranceGuardApply));
    }

    /**
     * 删除 门禁申请
     */
    @ApiOperation("删除 门禁申请")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:remove')")
    @Log(title = " 门禁申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseEntranceGuardApplyService.deleteBaseEntranceGuardApplyByIds(ids));
    }

    /**
     * 软删除 门禁申请
     */
    @ApiOperation("软删除 门禁申请")
    @PreAuthorize("@ss.hasPermi('property_company:entrance_guard_apply:remove')")
    @Log(title = " 门禁申请", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseEntranceGuardApplyService.updateBaseEntranceGuardApplyByIds(ids));
    }
}
