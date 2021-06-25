package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.CommunitySocialOrganizationVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
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
import com.isstech.isstechadmin.domain.BaseSocialOrganization;
import com.isstech.isstechadmin.service.IBaseSocialOrganizationService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 社会组织信息Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseSocialOrganization", description = "社会组织信息")
@RestController
@RequestMapping("/communityManage/social_organization")
public class BaseSocialOrganizationController extends BaseController
{
    @Autowired
    private IBaseSocialOrganizationService baseSocialOrganizationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社会组织信息列表
     */
    @ApiOperation("查询社会组织信息列表")
//    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunitySocialOrganizationVo baseSocialOrganization)
    {
        baseSocialOrganization.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseSocialOrganization.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<CommunitySocialOrganizationVo> list = baseSocialOrganizationService.selectBaseSocialOrganizationList(baseSocialOrganization);
        return getDataTable(list);
    }

    /**
     * 导出社会组织信息列表
     */
    @ApiOperation("导出社会组织信息列表")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:export')")
    @Log(title = "社会组织信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunitySocialOrganizationVo baseSocialOrganization)
    {
        List<CommunitySocialOrganizationVo> list = baseSocialOrganizationService.selectBaseSocialOrganizationList(baseSocialOrganization);
        ExcelUtil<CommunitySocialOrganizationVo> util = new ExcelUtil<CommunitySocialOrganizationVo>(CommunitySocialOrganizationVo.class);
        return util.exportExcel(list, "social_organization");
    }

    /**
     * 获取社会组织信息详细信息
     */
    @ApiOperation("获取社会组织信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseSocialOrganizationService.selectBaseSocialOrganizationById(id));
    }

    /**
     * 新增社会组织信息
     */
    @ApiOperation("新增社会组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:add')")
    @Log(title = "社会组织信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseSocialOrganization baseSocialOrganization, @RequestBody QueryVo vo)
    {
        return toAjax(baseSocialOrganizationService.insertBaseSocialOrganization(baseSocialOrganization,vo));
    }

    /**
     * 修改社会组织信息
     */
    @ApiOperation("修改社会组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:edit')")
    @Log(title = "社会组织信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseSocialOrganization baseSocialOrganization, @RequestBody QueryVo vo)
    {
        return toAjax(baseSocialOrganizationService.updateBaseSocialOrganization(baseSocialOrganization,vo));
    }

    /**
     * 删除社会组织信息
     */
    @ApiOperation("删除社会组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:remove')")
    @Log(title = "社会组织信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseSocialOrganizationService.deleteBaseSocialOrganizationByIds(ids));
    }

    /**
     * 软删除社会组织信息
     */
    @ApiOperation("软删除社会组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:social_organization:remove')")
    @Log(title = "社会组织信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseSocialOrganizationService.updateBaseSocialOrganizationByIds(ids));
    }
}
