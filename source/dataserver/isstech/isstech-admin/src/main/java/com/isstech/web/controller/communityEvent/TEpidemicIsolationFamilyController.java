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
import com.isstech.isstechadmin.domain.TEpidemicIsolationFamily;
import com.isstech.isstechadmin.service.ITEpidemicIsolationFamilyService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 隔离家庭 Controller
 *
 * @author yzp
 * @date 2021-01-20
 */
@Api(tags ="TEpidemicIsolationFamily", description = "隔离家庭 ")
@RestController
@RequestMapping("/epidemic/epidemic_isolation_family")
public class TEpidemicIsolationFamilyController extends BaseController
{
    @Autowired
    private ITEpidemicIsolationFamilyService tEpidemicIsolationFamilyService;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    /**
     * 查询隔离家庭 列表
     */
    @ApiOperation("查询隔离家庭 列表")
    //@PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:list')")
    @GetMapping("/list")
    public TableDataInfo list(TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        tEpidemicIsolationFamily.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tEpidemicIsolationFamily.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TEpidemicIsolationFamily> list = tEpidemicIsolationFamilyService.selectTEpidemicIsolationFamilyList(tEpidemicIsolationFamily);
        return getDataTable(list);
    }

    /**
     * 导出隔离家庭 列表
     */
    @ApiOperation("导出隔离家庭 列表")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:export')")
    @Log(title = "隔离家庭 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        List<TEpidemicIsolationFamily> list = tEpidemicIsolationFamilyService.queryTEpidemicIsolationFamilyList(tEpidemicIsolationFamily);
        ExcelUtil<TEpidemicIsolationFamily> util = new ExcelUtil<TEpidemicIsolationFamily>(TEpidemicIsolationFamily.class);
        return util.exportExcel(list, "epidemic_isolation_family");
    }

    /**
     * 获取隔离家庭 详细信息
     */
    @ApiOperation("获取隔离家庭 详细信息")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tEpidemicIsolationFamilyService.selectTEpidemicIsolationFamilyById(id));
    }

    /**
     * 新增隔离家庭 
     */
    @ApiOperation("新增隔离家庭 ")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:add')")
    @Log(title = "隔离家庭 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        return toAjax(tEpidemicIsolationFamilyService.insertTEpidemicIsolationFamily(tEpidemicIsolationFamily));
    }

    /**
     * 修改隔离家庭 
     */
    @ApiOperation("修改隔离家庭 ")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:edit')")
    @Log(title = "隔离家庭 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TEpidemicIsolationFamily tEpidemicIsolationFamily)
    {
        return toAjax(tEpidemicIsolationFamilyService.updateTEpidemicIsolationFamily(tEpidemicIsolationFamily));
    }

    /**
     * 删除隔离家庭 
     */
    @ApiOperation("删除隔离家庭 ")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:remove')")
    @Log(title = "隔离家庭 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tEpidemicIsolationFamilyService.deleteTEpidemicIsolationFamilyByIds(ids));
    }

    /**
     * 软删除隔离家庭 
     */
    @ApiOperation("软删除隔离家庭 ")
    @PreAuthorize("@ss.hasPermi('epidemic:epidemic_isolation_family:remove')")
    @Log(title = "隔离家庭 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tEpidemicIsolationFamilyService.updateTEpidemicIsolationFamilyByIds(ids));
    }
}
