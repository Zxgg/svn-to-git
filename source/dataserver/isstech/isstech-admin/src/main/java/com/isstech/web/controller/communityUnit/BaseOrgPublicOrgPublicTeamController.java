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
import com.isstech.isstechadmin.domain.BaseOrgPublicOrgPublicTeam;
import com.isstech.isstechadmin.service.IBaseOrgPublicOrgPublicTeamService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 群防群治组织和队伍关联Controller
 *
 * @author yzp
 * @date 2021-01-29
 */
@Api(tags ="BaseOrgPublicOrgPublicTeam", description = "群防群治组织和队伍关联")
@RestController
@RequestMapping("/communityManage/org_public_org_public_team")
public class BaseOrgPublicOrgPublicTeamController extends BaseController
{
    @Autowired
    private IBaseOrgPublicOrgPublicTeamService baseOrgPublicOrgPublicTeamService;

    /**
     * 查询群防群治组织和队伍关联列表
     */
    @ApiOperation("查询群防群治组织和队伍关联列表")
    //@PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        startPage();
        List<BaseOrgPublicOrgPublicTeam> list = baseOrgPublicOrgPublicTeamService.selectBaseOrgPublicOrgPublicTeamList(baseOrgPublicOrgPublicTeam);
        return getDataTable(list);
    }

    /**
     * 导出群防群治组织和队伍关联列表
     */
    @ApiOperation("导出群防群治组织和队伍关联列表")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:export')")
    @Log(title = "群防群治组织和队伍关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        List<BaseOrgPublicOrgPublicTeam> list = baseOrgPublicOrgPublicTeamService.selectBaseOrgPublicOrgPublicTeamList(baseOrgPublicOrgPublicTeam);
        ExcelUtil<BaseOrgPublicOrgPublicTeam> util = new ExcelUtil<BaseOrgPublicOrgPublicTeam>(BaseOrgPublicOrgPublicTeam.class);
        return util.exportExcel(list, "org_public_org_public_team");
    }

    /**
     * 获取群防群治组织和队伍关联详细信息
     */
    @ApiOperation("获取群防群治组织和队伍关联详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOrgPublicOrgPublicTeamService.selectBaseOrgPublicOrgPublicTeamById(id));
    }

    /**
     * 新增群防群治组织和队伍关联
     */
    @ApiOperation("新增群防群治组织和队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:add')")
    @Log(title = "群防群治组织和队伍关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        return toAjax(baseOrgPublicOrgPublicTeamService.insertBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam));
    }

    /**
     * 修改群防群治组织和队伍关联
     */
    @ApiOperation("修改群防群治组织和队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:edit')")
    @Log(title = "群防群治组织和队伍关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        return toAjax(baseOrgPublicOrgPublicTeamService.updateBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam));
    }

    /**
     * 删除群防群治组织和队伍关联
     */
    @ApiOperation("删除群防群治组织和队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:remove')")
    @Log(title = "群防群治组织和队伍关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOrgPublicOrgPublicTeamService.deleteBaseOrgPublicOrgPublicTeamByIds(ids));
    }

    /**
     * 软删除群防群治组织和队伍关联
     */
    @ApiOperation("软删除群防群治组织和队伍关联")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public_org_public_team:remove')")
    @Log(title = "群防群治组织和队伍关联", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOrgPublicOrgPublicTeamService.updateBaseOrgPublicOrgPublicTeamByIds(ids));
    }
}
