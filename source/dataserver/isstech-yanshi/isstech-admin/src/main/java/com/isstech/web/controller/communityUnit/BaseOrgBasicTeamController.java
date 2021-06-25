package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.BaseOrgBasicTeamVo;
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
import com.isstech.isstechadmin.domain.BaseOrgBasicTeam;
import com.isstech.isstechadmin.service.IBaseOrgBasicTeamService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 综治队伍Controller
 *
 * @author yzp
 * @date 2021-01-29
 */
@Api(tags ="BaseOrgBasicTeam", description = "综治队伍")
@RestController
@RequestMapping("/communityManage/org_basic_team")
public class BaseOrgBasicTeamController extends BaseController
{
    @Autowired
    private IBaseOrgBasicTeamService baseOrgBasicTeamService;

    /**
     * 查询综治队伍列表
     */
    @ApiOperation("查询综治队伍列表")
    //@PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseOrgBasicTeamVo baseOrgBasicTeam)
    {
        startPage();
        List<BaseOrgBasicTeamVo> list = baseOrgBasicTeamService.selectBaseOrgBasicTeamVoList(baseOrgBasicTeam);
        return getDataTable(list);
    }

    /**
     * 导出综治队伍列表
     */
    @ApiOperation("导出综治队伍列表")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:export')")
    @Log(title = "综治队伍", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseOrgBasicTeamVo baseOrgBasicTeam)
    {
        List<BaseOrgBasicTeamVo> list = baseOrgBasicTeamService.selectBaseOrgBasicTeamVoList(baseOrgBasicTeam);
        ExcelUtil<BaseOrgBasicTeamVo> util = new ExcelUtil<BaseOrgBasicTeamVo>(BaseOrgBasicTeamVo.class);
        return util.exportExcel(list, "org_basic_team");
    }

    /**
     * 获取综治队伍详细信息
     */
    @ApiOperation("获取综治队伍详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOrgBasicTeamService.selectBaseOrgBasicTeamById(id));
    }

    /**
     * 新增综治队伍
     */
    @ApiOperation("新增综治队伍")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:add')")
    @Log(title = "综治队伍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOrgBasicTeamVo baseOrgBasicTeamVo)
    {
        return toAjax(baseOrgBasicTeamService.insertBaseOrgBasicTeam(baseOrgBasicTeamVo));
    }

    /**
     * 修改综治队伍
     */
    @ApiOperation("修改综治队伍")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:edit')")
    @Log(title = "综治队伍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOrgBasicTeamVo baseOrgBasicTeamVo)
    {
        return toAjax(baseOrgBasicTeamService.updateBaseOrgBasicTeam(baseOrgBasicTeamVo));
    }

    /**
     * 删除综治队伍
     */
    @ApiOperation("删除综治队伍")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:remove')")
    @Log(title = "综治队伍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicTeamService.deleteBaseOrgBasicTeamByIds(ids));
    }

    /**
     * 软删除综治队伍
     */
    @ApiOperation("软删除综治队伍")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic_team:remove')")
    @Log(title = "综治队伍", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicTeamService.updateBaseOrgBasicTeamByIds(ids));
    }
}
