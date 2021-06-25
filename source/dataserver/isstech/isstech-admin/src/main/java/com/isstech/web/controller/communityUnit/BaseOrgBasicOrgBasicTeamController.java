package com.isstech.web.controller.communityUnit;

import java.util.List;
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
import com.isstech.isstechadmin.domain.BaseOrgBasicOrgBasicTeam;
import com.isstech.isstechadmin.service.IBaseOrgBasicOrgBasicTeamService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 综治机构和综治队伍关联Controller
 *
 * @author yzp
 * @date 2021-01-29
 */
@Api(tags ="BaseOrgBasicOrgBasicTeam", description = "综治机构和综治队伍关联")
@RestController
@RequestMapping("/communityManage/org_basic_org_basic_team")
public class BaseOrgBasicOrgBasicTeamController extends BaseController
{
    @Autowired
    private IBaseOrgBasicOrgBasicTeamService baseOrgBasicOrgBasicTeamService;

    /**
     * 查询综治机构和综治队伍关联列表
     */
    @ApiOperation("查询综治机构和综治队伍关联列表")
    //@PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        startPage();
        List<BaseOrgBasicOrgBasicTeam> list = baseOrgBasicOrgBasicTeamService.selectBaseOrgBasicOrgBasicTeamList(baseOrgBasicOrgBasicTeam);
        return getDataTable(list);
    }

    /**
     * 导出综治机构和综治队伍关联列表
     */
    @ApiOperation("导出综治机构和综治队伍关联列表")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:export')")
    @Log(title = "综治机构和综治队伍关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        List<BaseOrgBasicOrgBasicTeam> list = baseOrgBasicOrgBasicTeamService.selectBaseOrgBasicOrgBasicTeamList(baseOrgBasicOrgBasicTeam);
        ExcelUtil<BaseOrgBasicOrgBasicTeam> util = new ExcelUtil<BaseOrgBasicOrgBasicTeam>(BaseOrgBasicOrgBasicTeam.class);
        return util.exportExcel(list, "org_basic_org_basic_team");
    }

    /**
     * 获取综治机构和综治队伍关联详细信息
     */
    @ApiOperation("获取综治机构和综治队伍关联详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOrgBasicOrgBasicTeamService.selectBaseOrgBasicOrgBasicTeamById(id));
    }

    /**
     * 新增综治机构和综治队伍关联
     */
    @ApiOperation("新增综治机构和综治队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:add')")
    @Log(title = "综治机构和综治队伍关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        return toAjax(baseOrgBasicOrgBasicTeamService.insertBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam));
    }

    /**
     * 修改综治机构和综治队伍关联
     */
    @ApiOperation("修改综治机构和综治队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:edit')")
    @Log(title = "综治机构和综治队伍关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        return toAjax(baseOrgBasicOrgBasicTeamService.updateBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam));
    }

    /**
     * 删除综治机构和综治队伍关联
     */
    @ApiOperation("删除综治机构和综治队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:remove')")
    @Log(title = "综治机构和综治队伍关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicOrgBasicTeamService.deleteBaseOrgBasicOrgBasicTeamByIds(ids));
    }

    /**
     * 软删除综治机构和综治队伍关联
     */
    @ApiOperation("软删除综治机构和综治队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_org_basic_team:remove')")
    @Log(title = "综治机构和综治队伍关联", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicOrgBasicTeamService.updateBaseOrgBasicOrgBasicTeamByIds(ids));
    }
}
