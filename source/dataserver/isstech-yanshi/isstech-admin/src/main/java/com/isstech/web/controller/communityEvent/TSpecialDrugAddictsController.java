package com.isstech.web.controller.communityEvent;

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
import com.isstech.isstechadmin.domain.TSpecialDrugAddicts;
import com.isstech.isstechadmin.service.ITSpecialDrugAddictsService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 吸毒人员信息 Controller
 *
 * @author yzp
 * @date 2021-02-22
 */
@Api(tags ="TSpecialDrugAddicts", description = "吸毒人员信息 ")
@RestController
@RequestMapping("/special_personnel/special_drug_addicts")
public class TSpecialDrugAddictsController extends BaseController
{
    @Autowired
    private ITSpecialDrugAddictsService tSpecialDrugAddictsService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询吸毒人员信息 列表
     */
    @ApiOperation("查询吸毒人员信息 列表")
    //@PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        tSpecialDrugAddicts.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tSpecialDrugAddicts.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TSpecialDrugAddicts> list = tSpecialDrugAddictsService.selectTSpecialDrugAddictsList(tSpecialDrugAddicts);
        return getDataTable(list);
    }

    /**
     * 导出吸毒人员信息 列表
     */
    @ApiOperation("导出吸毒人员信息 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:export')")
    @Log(title = "吸毒人员信息 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        List<TSpecialDrugAddicts> list = tSpecialDrugAddictsService.selectTSpecialDrugAddictsList(tSpecialDrugAddicts);
        ExcelUtil<TSpecialDrugAddicts> util = new ExcelUtil<TSpecialDrugAddicts>(TSpecialDrugAddicts.class);
        return util.exportExcel(list, "special_drug_addicts");
    }

    /**
     * 获取吸毒人员信息 详细信息
     */
    @ApiOperation("获取吸毒人员信息 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpecialDrugAddictsService.selectTSpecialDrugAddictsById(id));
    }

    /**
     * 新增吸毒人员信息 
     */
    @ApiOperation("新增吸毒人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:add')")
    @Log(title = "吸毒人员信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        return toAjax(tSpecialDrugAddictsService.insertTSpecialDrugAddicts(tSpecialDrugAddicts));
    }

    /**
     * 修改吸毒人员信息 
     */
    @ApiOperation("修改吸毒人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:edit')")
    @Log(title = "吸毒人员信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        return toAjax(tSpecialDrugAddictsService.updateTSpecialDrugAddicts(tSpecialDrugAddicts));
    }

    /**
     * 删除吸毒人员信息 
     */
    @ApiOperation("删除吸毒人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:remove')")
    @Log(title = "吸毒人员信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpecialDrugAddictsService.deleteTSpecialDrugAddictsByIds(ids));
    }

    /**
     * 软删除吸毒人员信息 
     */
    @ApiOperation("软删除吸毒人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_drug_addicts:remove')")
    @Log(title = "吸毒人员信息 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpecialDrugAddictsService.updateTSpecialDrugAddictsByIds(ids));
    }
}
