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
import com.isstech.isstechadmin.domain.BaseCertifyApplicationForm;
import com.isstech.isstechadmin.service.IBaseCertifyApplicationFormService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 证明办理申请单Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BaseCertifyApplicationForm", description = "证明办理申请单")
@RestController
@RequestMapping("/property_company/certify_application_form")
public class BaseCertifyApplicationFormController extends BaseController
{
    @Autowired
    private IBaseCertifyApplicationFormService baseCertifyApplicationFormService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询证明办理申请单列表
     */
    @ApiOperation("查询证明办理申请单列表")
    //@PreAuthorize("@ss.hasPermi('property_company:certify_application_form:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        baseCertifyApplicationForm.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCertifyApplicationForm.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCertifyApplicationForm> list = baseCertifyApplicationFormService.selectBaseCertifyApplicationFormList(baseCertifyApplicationForm);
        return getDataTable(list);
    }

    /**
     * 导出证明办理申请单列表
     */
    @ApiOperation("导出证明办理申请单列表")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:export')")
    @Log(title = "证明办理申请单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        List<BaseCertifyApplicationForm> list = baseCertifyApplicationFormService.selectBaseCertifyApplicationFormList(baseCertifyApplicationForm);
        ExcelUtil<BaseCertifyApplicationForm> util = new ExcelUtil<BaseCertifyApplicationForm>(BaseCertifyApplicationForm.class);
        return util.exportExcel(list, "certify_application_form");
    }

    /**
     * 获取证明办理申请单详细信息
     */
    @ApiOperation("获取证明办理申请单详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCertifyApplicationFormService.selectBaseCertifyApplicationFormById(id));
    }

    /**
     * 新增证明办理申请单
     */
    @ApiOperation("新增证明办理申请单")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:add')")
    @Log(title = "证明办理申请单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        return toAjax(baseCertifyApplicationFormService.insertBaseCertifyApplicationForm(baseCertifyApplicationForm));
    }

    /**
     * 修改证明办理申请单
     */
    @ApiOperation("修改证明办理申请单")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:edit')")
    @Log(title = "证明办理申请单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCertifyApplicationForm baseCertifyApplicationForm)
    {
        return toAjax(baseCertifyApplicationFormService.updateBaseCertifyApplicationForm(baseCertifyApplicationForm));
    }

    /**
     * 删除证明办理申请单
     */
    @ApiOperation("删除证明办理申请单")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:remove')")
    @Log(title = "证明办理申请单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCertifyApplicationFormService.deleteBaseCertifyApplicationFormByIds(ids));
    }

    /**
     * 软删除证明办理申请单
     */
    @ApiOperation("软删除证明办理申请单")
    @PreAuthorize("@ss.hasPermi('property_company:certify_application_form:remove')")
    @Log(title = "证明办理申请单", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCertifyApplicationFormService.updateBaseCertifyApplicationFormByIds(ids));
    }
}
