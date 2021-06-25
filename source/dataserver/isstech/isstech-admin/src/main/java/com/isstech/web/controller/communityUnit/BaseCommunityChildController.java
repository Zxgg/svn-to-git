package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseCommunityChild;
import com.isstech.isstechadmin.service.IBaseCommunityChildService;
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
 * 小区信息  Controller
 *
 * @author yzp
 * @date 2020-11-26
 */
@Api(tags ="BaseCommunityChild", description = "小区信息  ")
@RestController
@RequestMapping("/communityUnit/community_child")
public class BaseCommunityChildController extends BaseController
{
    @Autowired
    private IBaseCommunityChildService baseCommunityChildService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询小区信息  列表
     */
    @ApiOperation("查询小区信息  列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunityChild baseCommunityChild)
    {
//        baseCommunityChild.setAllDataAccess(dataAccessUtils.isAllDataAccess());
//        baseCommunityChild.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityChild> list = baseCommunityChildService.selectBaseCommunityChildList(baseCommunityChild);
        return getDataTable(list);
    }

    /**
     * 导出小区信息  列表
     */
    @ApiOperation("导出小区信息  列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:export')")
    @Log(title = "小区信息  ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunityChild baseCommunityChild)
    {
        List<BaseCommunityChild> list = baseCommunityChildService.selectBaseCommunityChildList(baseCommunityChild);
        ExcelUtil<BaseCommunityChild> util = new ExcelUtil<BaseCommunityChild>(BaseCommunityChild.class);
        return util.exportExcel(list, "community_child");
    }

    /**
     * 获取小区信息  详细信息
     */
    @ApiOperation("获取小区信息  详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityChildService.selectBaseCommunityChildById(id));
    }

    /**
     * 新增小区信息  
     */
    @ApiOperation("新增小区信息  ")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:add')")
    @Log(title = "小区信息  ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityChild baseCommunityChild)
    {
        return toAjax(baseCommunityChildService.insertBaseCommunityChild(baseCommunityChild));
    }

    /**
     * 修改小区信息  
     */
    @ApiOperation("修改小区信息  ")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:edit')")
    @Log(title = "小区信息  ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityChild baseCommunityChild)
    {
        return toAjax(baseCommunityChildService.updateBaseCommunityChild(baseCommunityChild));
    }

    /**
     * 删除小区信息  
     */
    @ApiOperation("删除小区信息  ")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:remove')")
    @Log(title = "小区信息  ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildService.deleteBaseCommunityChildByIds(ids));
    }

    /**
     * 软删除小区信息
     */
    @ApiOperation("删除小区信息  ")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_child:remove')")
    @Log(title = "小区信息  ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildService.updateBaseCommunityChildByIds(ids));
    }
}
