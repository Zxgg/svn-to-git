package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseCommunityHousesPersonnel;
import com.isstech.isstechadmin.service.IBaseCommunityHousesPersonnelService;
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
 * 社区房屋和人员关联Controller
 *
 * @author yzp
 * @date 2020-11-30
 */
@Api(tags ="BaseCommunityHousesPersonnel", description = "社区房屋和人员关联")
@RestController
@RequestMapping("/communityUnit/community_houses_personnel")
public class BaseCommunityHousesPersonnelController extends BaseController
{
    @Autowired
    private IBaseCommunityHousesPersonnelService baseCommunityHousesPersonnelService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区房屋和人员关联列表
     */
    @ApiOperation("查询社区房屋和人员关联列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        baseCommunityHousesPersonnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCommunityHousesPersonnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityHousesPersonnel> list = baseCommunityHousesPersonnelService.selectBaseCommunityHousesPersonnelList(baseCommunityHousesPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出社区房屋和人员关联列表
     */
    @ApiOperation("导出社区房屋和人员关联列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:export')")
    @Log(title = "社区房屋和人员关联", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        List<BaseCommunityHousesPersonnel> list = baseCommunityHousesPersonnelService.selectBaseCommunityHousesPersonnelList(baseCommunityHousesPersonnel);
        ExcelUtil<BaseCommunityHousesPersonnel> util = new ExcelUtil<BaseCommunityHousesPersonnel>(BaseCommunityHousesPersonnel.class);
        return util.exportExcel(list, "community_houses_personnel");
    }

    /**
     * 获取社区房屋和人员关联详细信息
     */
    @ApiOperation("获取社区房屋和人员关联详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityHousesPersonnelService.selectBaseCommunityHousesPersonnelById(id));
    }

    /**
     * 新增社区房屋和人员关联
     */
    @ApiOperation("新增社区房屋和人员关联")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:add')")
    @Log(title = "社区房屋和人员关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        return toAjax(baseCommunityHousesPersonnelService.insertBaseCommunityHousesPersonnel(baseCommunityHousesPersonnel));
    }

    /**
     * 修改社区房屋和人员关联
     */
    @ApiOperation("修改社区房屋和人员关联")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:edit')")
    @Log(title = "社区房屋和人员关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        return toAjax(baseCommunityHousesPersonnelService.updateBaseCommunityHousesPersonnel(baseCommunityHousesPersonnel));
    }

    /**
     * 删除社区房屋和人员关联
     */
    @ApiOperation("删除社区房屋和人员关联")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:remove')")
    @Log(title = "社区房屋和人员关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityHousesPersonnelService.deleteBaseCommunityHousesPersonnelByIds(ids));
    }

    /**
     * 软删除社区房屋和人员关联
     */
    @ApiOperation("删除社区房屋和人员关联")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses_personnel:remove')")
    @Log(title = "社区房屋和人员关联", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityHousesPersonnelService.updateBaseCommunityHousesPersonnelByIds(ids));
    }
}
