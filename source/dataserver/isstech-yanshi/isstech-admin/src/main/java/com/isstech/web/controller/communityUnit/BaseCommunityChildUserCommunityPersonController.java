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
import com.isstech.isstechadmin.domain.BaseCommunityChildUserCommunityPerson;
import com.isstech.isstechadmin.service.IBaseCommunityChildUserCommunityPersonService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 小区用户和小区人员房屋关联 Controller
 *
 * @author yzp
 * @date 2021-02-19
 */
@Api(tags ="BaseCommunityChildUserCommunityPerson", description = "小区用户和小区人员房屋关联 ")
@RestController
@RequestMapping("/property_company/community_child_user_person")
public class BaseCommunityChildUserCommunityPersonController extends BaseController
{
    @Autowired
    private IBaseCommunityChildUserCommunityPersonService baseCommunityChildUserCommunityPersonService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询小区用户和小区人员房屋关联 列表
     */
    @ApiOperation("查询小区用户和小区人员房屋关联 列表")
    //@PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        baseCommunityChildUserCommunityPerson.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseCommunityChildUserCommunityPerson.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityChildUserCommunityPerson> list = baseCommunityChildUserCommunityPersonService.selectBaseCommunityChildUserCommunityPersonList(baseCommunityChildUserCommunityPerson);
        return getDataTable(list);
    }

    /**
     * 导出小区用户和小区人员房屋关联 列表
     */
    @ApiOperation("导出小区用户和小区人员房屋关联 列表")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:export')")
    @Log(title = "小区用户和小区人员房屋关联 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        List<BaseCommunityChildUserCommunityPerson> list = baseCommunityChildUserCommunityPersonService.selectBaseCommunityChildUserCommunityPersonList(baseCommunityChildUserCommunityPerson);
        ExcelUtil<BaseCommunityChildUserCommunityPerson> util = new ExcelUtil<BaseCommunityChildUserCommunityPerson>(BaseCommunityChildUserCommunityPerson.class);
        return util.exportExcel(list, "community_child_user_person");
    }

    /**
     * 获取小区用户和小区人员房屋关联 详细信息
     */
    @ApiOperation("获取小区用户和小区人员房屋关联 详细信息")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityChildUserCommunityPersonService.selectBaseCommunityChildUserCommunityPersonById(id));
    }

    /**
     * 新增小区用户和小区人员房屋关联 
     */
    @ApiOperation("新增小区用户和小区人员房屋关联 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:add')")
    @Log(title = "小区用户和小区人员房屋关联 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        return toAjax(baseCommunityChildUserCommunityPersonService.insertBaseCommunityChildUserCommunityPerson(baseCommunityChildUserCommunityPerson));
    }

    /**
     * 修改小区用户和小区人员房屋关联 
     */
    @ApiOperation("修改小区用户和小区人员房屋关联 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:edit')")
    @Log(title = "小区用户和小区人员房屋关联 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityChildUserCommunityPerson baseCommunityChildUserCommunityPerson)
    {
        return toAjax(baseCommunityChildUserCommunityPersonService.updateBaseCommunityChildUserCommunityPerson(baseCommunityChildUserCommunityPerson));
    }

    /**
     * 删除小区用户和小区人员房屋关联 
     */
    @ApiOperation("删除小区用户和小区人员房屋关联 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:remove')")
    @Log(title = "小区用户和小区人员房屋关联 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildUserCommunityPersonService.deleteBaseCommunityChildUserCommunityPersonByIds(ids));
    }

    /**
     * 软删除小区用户和小区人员房屋关联 
     */
    @ApiOperation("软删除小区用户和小区人员房屋关联 ")
    @PreAuthorize("@ss.hasPermi('property_company:community_child_user_person:remove')")
    @Log(title = "小区用户和小区人员房屋关联 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityChildUserCommunityPersonService.updateBaseCommunityChildUserCommunityPersonByIds(ids));
    }
}
