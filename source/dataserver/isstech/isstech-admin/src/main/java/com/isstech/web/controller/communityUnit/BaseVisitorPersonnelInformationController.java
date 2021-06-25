package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseVisitorPersonnelInformation;
import com.isstech.isstechadmin.service.IBaseVisitorPersonnelInformationService;
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
 * 访客人员信息Controller
 *
 * @author isstech
 * @date 2020-11-23
 */
@Api(tags ="BaseVisitorPersonnelInformation", description = "访客人员信息")
@RestController
@RequestMapping("/passManage/visitPerson")
public class BaseVisitorPersonnelInformationController extends BaseController
{
    @Autowired
    private IBaseVisitorPersonnelInformationService baseVisitorPersonnelInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询访客人员信息列表
     */
    @ApiOperation("查询访客人员信息列表")
//    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        baseVisitorPersonnelInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseVisitorPersonnelInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseVisitorPersonnelInformation> list = baseVisitorPersonnelInformationService.selectBaseVisitorPersonnelInformationList(baseVisitorPersonnelInformation);
        return getDataTable(list);
    }

    /**
     * 导出访客人员信息列表
     */
    @ApiOperation("导出访客人员信息列表")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:export')")
    @Log(title = "访客人员信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        List<BaseVisitorPersonnelInformation> list = baseVisitorPersonnelInformationService.selectBaseVisitorPersonnelInformationList(baseVisitorPersonnelInformation);
        ExcelUtil<BaseVisitorPersonnelInformation> util = new ExcelUtil<BaseVisitorPersonnelInformation>(BaseVisitorPersonnelInformation.class);
        return util.exportExcel(list, "visitPerson");
    }

    /**
     * 获取访客人员信息详细信息
     */
    @ApiOperation("获取访客人员信息详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVisitorPersonnelInformationService.selectBaseVisitorPersonnelInformationById(id));
    }

    /**
     * 新增访客人员信息
     */
    @ApiOperation("新增访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:add')")
    @Log(title = "访客人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        return toAjax(baseVisitorPersonnelInformationService.insertBaseVisitorPersonnelInformation(baseVisitorPersonnelInformation));
    }

    /**
     * 修改访客人员信息
     */
    @ApiOperation("修改访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:edit')")
    @Log(title = "访客人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        return toAjax(baseVisitorPersonnelInformationService.updateBaseVisitorPersonnelInformation(baseVisitorPersonnelInformation));
    }

    /**
     * 删除访客人员信息
     */
    @ApiOperation("删除访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:remove')")
    @Log(title = "访客人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVisitorPersonnelInformationService.deleteBaseVisitorPersonnelInformationByIds(ids));
    }
    /**
     * 软删除访客人员信息
     */
    @ApiOperation("软删除访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:visitPerson:remove')")
    @Log(title = "访客人员信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVisitorPersonnelInformationService.updateBaseVisitorPersonnelInformationByIds(ids));
    }
}
