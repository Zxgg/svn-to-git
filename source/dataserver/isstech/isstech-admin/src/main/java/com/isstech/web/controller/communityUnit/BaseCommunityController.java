package com.isstech.web.controller.communityUnit;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.BaseCommunity;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.service.IBaseCommunityService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 社区信息Controller
 * 
 * @author isstech
 * @date 2020-11-18
 */
@Api(tags ="BaseCommunity", description = "社区信息")
@RestController
@RequestMapping("/communityUnit/community")
public class BaseCommunityController extends BaseController
{
    @Autowired
    private IBaseCommunityService baseCommunityService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区信息列表
     */
    @ApiOperation("查询社区信息列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:community:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunity baseCommunity)
    {
//        baseCommunity.setAllDataAccess(dataAccessUtils.isAllDataAccess());
//        baseCommunity.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunity> list = baseCommunityService.selectBaseCommunityList(baseCommunity);
        return getDataTable(list);
    }

    /**
     * 查询社区菜单列表
     */
    @ApiOperation("查询社区菜单列表")
    @GetMapping("/menu")
    public AjaxResult selectBaseCommunityListAll()
    {
        return AjaxResult.success(baseCommunityService.selectBaseCommunityListAll());
    }

    /**
     * 导出社区信息列表
     */
    @ApiOperation("导出社区信息列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:export')")
    @Log(title = "社区信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunity baseCommunity)
    {
        List<BaseCommunity> list = baseCommunityService.selectBaseCommunityList(baseCommunity);
        ExcelUtil<BaseCommunity> util = new ExcelUtil<BaseCommunity>(BaseCommunity.class);
        return util.exportExcel(list, "community");
    }

    /**
     * 获取社区信息详细信息
     */
    @ApiOperation("获取社区信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityService.selectBaseCommunityById(id));
    }

    /**
     * 新增社区信息
     */
    @ApiOperation("新增社区信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:add')")
    @Log(title = "社区信息-新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunity baseCommunity)
    {
        return toAjax(baseCommunityService.insertBaseCommunity(baseCommunity));
    }

    /**
     * 修改社区信息
     */
    @ApiOperation("修改社区信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:edit')")
    @Log(title = "社区信息-修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunity baseCommunity)
    {
        return toAjax(baseCommunityService.updateBaseCommunity(baseCommunity));
    }

    /**
     * 删除社区信息
     */
    @ApiOperation("删除社区信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:remove')")
    @Log(title = "社区信息-删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityService.deleteBaseCommunityByIds(ids));
    }

    /**
     * 软删除社区信息
     */
    @ApiOperation("删除社区信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community:remove')")
    @Log(title = "社区信息-删除", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityService.updateBaseCommunityByIds(ids));
    }

    /**
     * 查询社区->小区->楼栋->单元->住户 树形结构信息
     *
     * @author qishuwei
     * @date 2020-12-07
     */
    @ApiOperation("查询社区->小区->楼栋->住户 树形结构信息")
    @GetMapping("selectTreeInfo")
    public AjaxResult selectTreeInfo(QueryVo queryVo){
        if(queryVo.getCommunityId() == null)
            return  AjaxResult.success(null);
        return AjaxResult.success(baseCommunityService.selectTreeInfo(queryVo));
    }

}
