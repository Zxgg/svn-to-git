package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.CommunityGroupAbroadVo;
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
import com.isstech.isstechadmin.domain.BaseGroupAbroad;
import com.isstech.isstechadmin.service.IBaseGroupAbroadService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 境内外非政府组织Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseGroupAbroad", description = "境内外非政府组织")
@RestController
@RequestMapping("/communityManage/group_abroad")
public class BaseGroupAbroadController extends BaseController
{
    @Autowired
    private IBaseGroupAbroadService baseGroupAbroadService;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    /**
     * 查询境内外非政府组织列表
     */
    @ApiOperation("查询境内外非政府组织列表")
//    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityGroupAbroadVo baseGroupAbroad)
    {
        baseGroupAbroad.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseGroupAbroad.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<CommunityGroupAbroadVo> list = baseGroupAbroadService.selectBaseGroupAbroadList(baseGroupAbroad);
        return getDataTable(list);
    }

    /**
     * 导出境内外非政府组织列表
     */
    @ApiOperation("导出境内外非政府组织列表")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:export')")
    @Log(title = "境内外非政府组织", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunityGroupAbroadVo baseGroupAbroad)
    {
        List<CommunityGroupAbroadVo> list = baseGroupAbroadService.selectBaseGroupAbroadList(baseGroupAbroad);
        ExcelUtil<CommunityGroupAbroadVo> util = new ExcelUtil<CommunityGroupAbroadVo>(CommunityGroupAbroadVo.class);
        return util.exportExcel(list, "group_abroad");
    }

    /**
     * 获取境内外非政府组织详细信息
     */
    @ApiOperation("获取境内外非政府组织详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseGroupAbroadService.selectBaseGroupAbroadById(id));
    }

    /**
     * 新增境内外非政府组织
     */
    @ApiOperation("新增境内外非政府组织")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:add')")
    @Log(title = "境内外非政府组织", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseGroupAbroad baseGroupAbroad, @RequestBody QueryVo vo)
    {
        return toAjax(baseGroupAbroadService.insertBaseGroupAbroad(baseGroupAbroad,vo));
    }

    /**
     * 修改境内外非政府组织
     */
    @ApiOperation("修改境内外非政府组织")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:edit')")
    @Log(title = "境内外非政府组织", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseGroupAbroad baseGroupAbroad, @RequestBody QueryVo vo)
    {
        return toAjax(baseGroupAbroadService.updateBaseGroupAbroad(baseGroupAbroad,vo));
    }

    /**
     * 删除境内外非政府组织
     */
    @ApiOperation("删除境内外非政府组织")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:remove')")
    @Log(title = "境内外非政府组织", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseGroupAbroadService.deleteBaseGroupAbroadByIds(ids));
    }

    /**
     * 软删除境内外非政府组织
     */
    @ApiOperation("删除境内外非政府组织")
    @PreAuthorize("@ss.hasPermi('communityManage:group_abroad:remove')")
    @Log(title = "境内外非政府组织", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseGroupAbroadService.updateBaseGroupAbroadByIds(ids));
    }
}
