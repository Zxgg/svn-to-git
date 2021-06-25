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
import com.isstech.isstechadmin.domain.BaseVoteProject;
import com.isstech.isstechadmin.service.IBaseVoteProjectService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 投票项目信息 Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseVoteProject", description = "投票项目信息 ")
@RestController
@RequestMapping("/communityServices/vote_project")
public class BaseVoteProjectController extends BaseController
{
    @Autowired
    private IBaseVoteProjectService baseVoteProjectService;

    /**
     * 查询投票项目信息 列表
     */
    @ApiOperation("查询投票项目信息 列表")
//    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVoteProject baseVoteProject)
    {
        startPage();
        List<BaseVoteProject> list = baseVoteProjectService.selectBaseVoteProjectList(baseVoteProject);
        return getDataTable(list);
    }

    /**
     * 导出投票项目信息 列表
     */
    @ApiOperation("导出投票项目信息 列表")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:export')")
    @Log(title = "投票项目信息 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVoteProject baseVoteProject)
    {
        List<BaseVoteProject> list = baseVoteProjectService.selectBaseVoteProjectList(baseVoteProject);
        ExcelUtil<BaseVoteProject> util = new ExcelUtil<BaseVoteProject>(BaseVoteProject.class);
        return util.exportExcel(list, "vote_project");
    }

    /**
     * 获取投票项目信息 详细信息
     */
    @ApiOperation("获取投票项目信息 详细信息")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVoteProjectService.selectBaseVoteProjectById(id));
    }

    /**
     * 新增投票项目信息 
     */
    @ApiOperation("新增投票项目信息 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:add')")
    @Log(title = "投票项目信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVoteProject baseVoteProject)
    {
        return toAjax(baseVoteProjectService.insertBaseVoteProject(baseVoteProject));
    }

    /**
     * 修改投票项目信息 
     */
    @ApiOperation("修改投票项目信息 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:edit')")
    @Log(title = "投票项目信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVoteProject baseVoteProject)
    {
        return toAjax(baseVoteProjectService.updateBaseVoteProject(baseVoteProject));
    }

    /**
     * 删除投票项目信息 
     */
    @ApiOperation("删除投票项目信息 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:remove')")
    @Log(title = "投票项目信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVoteProjectService.deleteBaseVoteProjectByIds(ids));
    }
    /**
     * 软删除投票项目信息
     */
    @ApiOperation("软删除投票项目信息 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_project:remove')")
    @Log(title = "投票项目信息 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVoteProjectService.updateBaseVoteProjectByIds(ids));
    }
}
