package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseCommunityBuilding;
import com.isstech.isstechadmin.service.IBaseCommunityBuildingService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
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
 * @Description 社区楼栋Controller
 * @Author yzp
 * @Date 2020/11/18 14:34
 */
@RestController
@RequestMapping("/communityUnit/community_building")
public class BaseCommunityBuildingController extends BaseController
{
    @Autowired
    private IBaseCommunityBuildingService baseCommunityBuildingService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区楼栋列表
     */
//    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunityBuilding baseCommunityBuilding)
    {
        baseCommunityBuilding.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCommunityBuilding.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityBuilding> list = baseCommunityBuildingService.selectBaseCommunityBuildingList(baseCommunityBuilding);
        return getDataTable(list);
    }

    /**
     * 导出社区楼栋列表
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:export')")
    @Log(title = "社区楼栋", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunityBuilding baseCommunityBuilding)
    {
        List<BaseCommunityBuilding> list = baseCommunityBuildingService.selectBaseCommunityBuildingList(baseCommunityBuilding);
        ExcelUtil<BaseCommunityBuilding> util = new ExcelUtil<BaseCommunityBuilding>(BaseCommunityBuilding.class);
        return util.exportExcel(list, "community_building");
    }

    /**
     * 获取社区楼栋详细信息
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityBuildingService.selectBaseCommunityBuildingById(id));
    }

    /**
     * 新增社区楼栋
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:add')")
    @Log(title = "社区楼栋", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityBuilding baseCommunityBuilding)
    {
        return toAjax(baseCommunityBuildingService.insertBaseCommunityBuilding(baseCommunityBuilding));
    }

    /**
     * 修改社区楼栋
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:edit')")
    @Log(title = "社区楼栋", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityBuilding baseCommunityBuilding)
    {
        return toAjax(baseCommunityBuildingService.updateBaseCommunityBuilding(baseCommunityBuilding));
    }

    /**
     * 删除社区楼栋
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:remove')")
    @Log(title = "社区楼栋", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityBuildingService.deleteBaseCommunityBuildingByIds(ids));
    }

    /**
     * 软删除社区楼栋
     */
    @PreAuthorize("@ss.hasPermi('communityUnit:community_building:remove')")
    @Log(title = "社区楼栋", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityBuildingService.updateBaseCommunityBuildingByIds(ids));
    }
}
