package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.CommunityOrgBasicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
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
import com.isstech.isstechadmin.domain.BaseOrgBasic;
import com.isstech.isstechadmin.service.IBaseOrgBasicService;
import com.isstech.common.utils.poi.ExcelUtil;

/**
 * 综治机构管理Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseOrgBasic", description = "综治机构管理")
@RestController
@RequestMapping("/communityManage/org_basic")
public class BaseOrgBasicController extends BaseController
{
    @Autowired
    private IBaseOrgBasicService baseOrgBasicService;

    /**
     * 查询综治机构管理列表
     */
    @ApiOperation("查询综治机构管理列表")
//    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:list')")
    @GetMapping("/list")
    public AjaxResult list(CommunityOrgBasicVo baseOrgBasic)
    {
        List<CommunityOrgBasicVo> list = baseOrgBasicService.selectBaseOrgBasicList(baseOrgBasic);
        return AjaxResult.success(list);
    }

    /**
     * 导出综治机构管理列表
     */
    @ApiOperation("导出综治机构管理列表")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:export')")
    @Log(title = "综治机构管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunityOrgBasicVo baseOrgBasic)
    {
        List<CommunityOrgBasicVo> list = baseOrgBasicService.selectBaseOrgBasicList(baseOrgBasic);
        ExcelUtil<CommunityOrgBasicVo> util = new ExcelUtil<CommunityOrgBasicVo>(CommunityOrgBasicVo.class);
        return util.exportExcel(list, "org_basic");
    }

    /**
     * 获取综治机构管理详细信息
     */
    @ApiOperation("获取综治机构管理详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOrgBasicService.selectBaseOrgBasicById(id));
    }

    /**
     * 新增综治机构管理
     */
    @ApiOperation("新增综治机构管理")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:add')")
    @Log(title = "综治机构管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOrgBasic baseOrgBasic, @RequestBody QueryVo vo)
    {
        System.out.println("test:"+vo.getCommunityId());
        return toAjax(baseOrgBasicService.insertBaseOrgBasic(baseOrgBasic,vo));
    }

    /**
     * 修改综治机构管理
     */
    @ApiOperation("修改综治机构管理")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:edit')")
    @Log(title = "综治机构管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOrgBasic baseOrgBasic, @RequestBody QueryVo vo)
    {
        return toAjax(baseOrgBasicService.updateBaseOrgBasic(baseOrgBasic,vo));
    }

    /**
     * 删除综治机构管理
     */
    @ApiOperation("删除综治机构管理")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:remove')")
    @Log(title = "综治机构管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicService.deleteBaseOrgBasicByIds(ids));
    }
    /**
     * 软删除综治机构管理
     */
    @ApiOperation("删除综治机构管理")
    @PreAuthorize("@ss.hasPermi('communityManage:org_basic:remove')")
    @Log(title = "综治机构管理", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOrgBasicService.updateBaseOrgBasicByIds(ids));
    }
}
