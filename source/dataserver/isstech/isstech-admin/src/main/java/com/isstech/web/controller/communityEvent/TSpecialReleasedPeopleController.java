package com.isstech.web.controller.communityEvent;

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
import com.isstech.isstechadmin.domain.TSpecialReleasedPeople;
import com.isstech.isstechadmin.service.ITSpecialReleasedPeopleService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 刑满释放人员 Controller
 *
 * @author yzp
 * @date 2021-02-22
 */
@Api(tags ="TSpecialReleasedPeople", description = "刑满释放人员 ")
@RestController
@RequestMapping("/special_personnel/special_released_people")
public class TSpecialReleasedPeopleController extends BaseController
{
    @Autowired
    private ITSpecialReleasedPeopleService tSpecialReleasedPeopleService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询刑满释放人员 列表
     */
    @ApiOperation("查询刑满释放人员 列表")
    //@PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        tSpecialReleasedPeople.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tSpecialReleasedPeople.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TSpecialReleasedPeople> list = tSpecialReleasedPeopleService.selectTSpecialReleasedPeopleList(tSpecialReleasedPeople);
        return getDataTable(list);
    }

    /**
     * 导出刑满释放人员 列表
     */
    @ApiOperation("导出刑满释放人员 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:export')")
    @Log(title = "刑满释放人员 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        List<TSpecialReleasedPeople> list = tSpecialReleasedPeopleService.selectTSpecialReleasedPeopleList(tSpecialReleasedPeople);
        ExcelUtil<TSpecialReleasedPeople> util = new ExcelUtil<TSpecialReleasedPeople>(TSpecialReleasedPeople.class);
        return util.exportExcel(list, "special_released_people");
    }

    /**
     * 获取刑满释放人员 详细信息
     */
    @ApiOperation("获取刑满释放人员 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpecialReleasedPeopleService.selectTSpecialReleasedPeopleById(id));
    }

    /**
     * 新增刑满释放人员 
     */
    @ApiOperation("新增刑满释放人员 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:add')")
    @Log(title = "刑满释放人员 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        return toAjax(tSpecialReleasedPeopleService.insertTSpecialReleasedPeople(tSpecialReleasedPeople));
    }

    /**
     * 修改刑满释放人员 
     */
    @ApiOperation("修改刑满释放人员 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:edit')")
    @Log(title = "刑满释放人员 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        return toAjax(tSpecialReleasedPeopleService.updateTSpecialReleasedPeople(tSpecialReleasedPeople));
    }

    /**
     * 删除刑满释放人员 
     */
    @ApiOperation("删除刑满释放人员 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:remove')")
    @Log(title = "刑满释放人员 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpecialReleasedPeopleService.deleteTSpecialReleasedPeopleByIds(ids));
    }

    /**
     * 软删除刑满释放人员 
     */
    @ApiOperation("软删除刑满释放人员 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_released_people:remove')")
    @Log(title = "刑满释放人员 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpecialReleasedPeopleService.updateTSpecialReleasedPeopleByIds(ids));
    }
}
