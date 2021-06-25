package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseOrgPublic;
import com.isstech.isstechadmin.domain.vo.CommunityOrgPublicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.service.IBaseOrgPublicService;
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
 * 群防群治组织Controller
 *
 * @author yzp
 * @date 2020-11-27
 */
@Api(tags ="BaseOrgPublic", description = "群防群治组织")
@RestController
@RequestMapping("/communityManage/org_public")
public class BaseOrgPublicController extends BaseController
{
    @Autowired
    private IBaseOrgPublicService baseOrgPublicService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询群防群治组织列表
     */
    @ApiOperation("查询群防群治组织列表")
//    @PreAuthorize("@ss.hasPermi('communityManage:org_public:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityOrgPublicVo baseOrgPublic)
    {
        baseOrgPublic.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseOrgPublic.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<CommunityOrgPublicVo> list = baseOrgPublicService.selectBaseOrgPublicList(baseOrgPublic);
        return getDataTable(list);
    }

    /**
     * 导出群防群治组织列表
     */
    @ApiOperation("导出群防群治组织列表")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:export')")
    @Log(title = "群防群治组织", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunityOrgPublicVo baseOrgPublic)
    {
        List<CommunityOrgPublicVo> list = baseOrgPublicService.selectBaseOrgPublicList(baseOrgPublic);
        ExcelUtil<CommunityOrgPublicVo> util = new ExcelUtil<CommunityOrgPublicVo>(CommunityOrgPublicVo.class);
        return util.exportExcel(list, "org_public");
    }

    /**
     * 获取群防群治组织详细信息
     */
    @ApiOperation("获取群防群治组织详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOrgPublicService.selectBaseOrgPublicById(id));
    }

    /**
     * 新增群防群治组织
     */
    @ApiOperation("新增群防群治组织")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:add')")
    @Log(title = "群防群治组织", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOrgPublic baseOrgPublic, @RequestBody QueryVo vo)
    {
        return toAjax(baseOrgPublicService.insertBaseOrgPublic(baseOrgPublic,vo));
    }

    /**
     * 修改群防群治组织
     */
    @ApiOperation("修改群防群治组织")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:edit')")
    @Log(title = "群防群治组织", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOrgPublic baseOrgPublic, @RequestBody QueryVo vo)
    {
        return toAjax(baseOrgPublicService.updateBaseOrgPublic(baseOrgPublic,vo));
    }

    /**
     * 删除群防群治组织
     */
    @ApiOperation("删除群防群治组织")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:remove')")
    @Log(title = "群防群治组织", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOrgPublicService.deleteBaseOrgPublicByIds(ids));
    }

    /**
     * 软删除群防群治组织
     */
    @ApiOperation("软删除群防群治组织")
    @PreAuthorize("@ss.hasPermi('communityManage:org_public:remove')")
    @Log(title = "群防群治组织", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOrgPublicService.updateBaseOrgPublicByIds(ids));
    }
}
