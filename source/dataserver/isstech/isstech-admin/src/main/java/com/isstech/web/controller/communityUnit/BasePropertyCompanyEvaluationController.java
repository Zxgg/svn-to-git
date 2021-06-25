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
import com.isstech.isstechadmin.domain.BasePropertyCompanyEvaluation;
import com.isstech.isstechadmin.service.IBasePropertyCompanyEvaluationService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 物业评价 Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BasePropertyCompanyEvaluation", description = "物业评价 ")
@RestController
@RequestMapping("/property_company/property_company_evaluation")
public class BasePropertyCompanyEvaluationController extends BaseController
{
    @Autowired
    private IBasePropertyCompanyEvaluationService basePropertyCompanyEvaluationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询物业评价 列表
     */
    @ApiOperation("查询物业评价 列表")
    //@PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        basePropertyCompanyEvaluation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        basePropertyCompanyEvaluation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePropertyCompanyEvaluation> list = basePropertyCompanyEvaluationService.selectBasePropertyCompanyEvaluationList(basePropertyCompanyEvaluation);
        return getDataTable(list);
    }

    /**
     * 导出物业评价 列表
     */
    @ApiOperation("导出物业评价 列表")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:export')")
    @Log(title = "物业评价 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        List<BasePropertyCompanyEvaluation> list = basePropertyCompanyEvaluationService.selectBasePropertyCompanyEvaluationList(basePropertyCompanyEvaluation);
        ExcelUtil<BasePropertyCompanyEvaluation> util = new ExcelUtil<BasePropertyCompanyEvaluation>(BasePropertyCompanyEvaluation.class);
        return util.exportExcel(list, "property_company_evaluation");
    }

    /**
     * 获取物业评价 详细信息
     */
    @ApiOperation("获取物业评价 详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basePropertyCompanyEvaluationService.selectBasePropertyCompanyEvaluationById(id));
    }

    /**
     * 新增物业评价 
     */
    @ApiOperation("新增物业评价 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:add')")
    @Log(title = "物业评价 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        return toAjax(basePropertyCompanyEvaluationService.insertBasePropertyCompanyEvaluation(basePropertyCompanyEvaluation));
    }

    /**
     * 修改物业评价 
     */
    @ApiOperation("修改物业评价 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:edit')")
    @Log(title = "物业评价 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        return toAjax(basePropertyCompanyEvaluationService.updateBasePropertyCompanyEvaluation(basePropertyCompanyEvaluation));
    }

    /**
     * 删除物业评价 
     */
    @ApiOperation("删除物业评价 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:remove')")
    @Log(title = "物业评价 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basePropertyCompanyEvaluationService.deleteBasePropertyCompanyEvaluationByIds(ids));
    }

    /**
     * 软删除物业评价 
     */
    @ApiOperation("软删除物业评价 ")
    @PreAuthorize("@ss.hasPermi('property_company:property_company_evaluation:remove')")
    @Log(title = "物业评价 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(basePropertyCompanyEvaluationService.updateBasePropertyCompanyEvaluationByIds(ids));
    }
}
