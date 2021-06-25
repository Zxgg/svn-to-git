package com.isstech.web.controller.communityUnit;

import java.util.List;
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
import com.isstech.isstechadmin.domain.BaseCommunityChildUser;
import com.isstech.isstechadmin.service.IBaseCommunityChildUserService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 小区用户 Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BaseCommunityChildUser", description = "小区用户 ")
@RestController
@RequestMapping("/property_company/community_child_user")
public class BaseCommunityChildUserController extends BaseController
{
    @Autowired
    private IBaseCommunityChildUserService baseCommunityChildUserService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询小区用户 列表
     */
    @ApiOperation("查询小区用户 列表")
    //@PreAuthorize("@ss.hasPermi('property_company:community_child_user:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunityChildUser baseCommunityChildUser)
    {
        baseCommunityChildUser.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCommunityChildUser.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityChildUser> list = baseCommunityChildUserService.selectBaseCommunityChildUserList(baseCommunityChildUser);
        return getDataTable(list);
    }

    /**
     * 导出小区用户 列表
     */
    @ApiOperation("导出小区用户 列表")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:export')")
    @Log(title = "小区用户 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunityChildUser baseCommunityChildUser)
    {
        List<BaseCommunityChildUser> list = baseCommunityChildUserService.selectBaseCommunityChildUserList(baseCommunityChildUser);
        ExcelUtil<BaseCommunityChildUser> util = new ExcelUtil<BaseCommunityChildUser>(BaseCommunityChildUser.class);
        return util.exportExcel(list, "community_child_user");
    }

    /**
     * 获取小区用户 详细信息
     */
    @ApiOperation("获取小区用户 详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityChildUserService.selectBaseCommunityChildUserById(id));
    }

    /**
     * 新增小区用户 
     */
    @ApiOperation("新增小区用户 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:add')")
    @Log(title = "小区用户 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityChildUser baseCommunityChildUser)
    {
        return toAjax(baseCommunityChildUserService.insertBaseCommunityChildUser(baseCommunityChildUser));
    }

    /**
     * 修改小区用户 
     */
    @ApiOperation("修改小区用户 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:edit')")
    @Log(title = "小区用户 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityChildUser baseCommunityChildUser)
    {
        return toAjax(baseCommunityChildUserService.updateBaseCommunityChildUser(baseCommunityChildUser));
    }

    /**
     * 删除小区用户 
     */
    @ApiOperation("删除小区用户 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:remove')")
    @Log(title = "小区用户 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildUserService.deleteBaseCommunityChildUserByIds(ids));
    }

    /**
     * 软删除小区用户 
     */
    @ApiOperation("软删除小区用户 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user:remove')")
    @Log(title = "小区用户 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildUserService.updateBaseCommunityChildUserByIds(ids));
    }
}
