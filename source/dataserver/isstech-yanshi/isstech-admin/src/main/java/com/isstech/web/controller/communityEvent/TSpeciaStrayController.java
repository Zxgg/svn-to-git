package com.isstech.web.controller.communityEvent;

import java.util.List;

import com.isstech.isstechadmin.domain.TSpeciaStray;
import com.isstech.isstechadmin.service.ITSpeciaStrayService;
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
 * 闲散流浪人员信息 Controller
 *
 * @author yzp
 * @date 2020-12-08
 */
@Api(tags ="TSpeciaStray", description = "闲散流浪人员信息 ")
@RestController
@RequestMapping("/special_personnel/specia_stray")
public class TSpeciaStrayController extends BaseController
{
    @Autowired
    private ITSpeciaStrayService tSpeciaStrayService;

    /**
     * 查询闲散流浪人员信息 列表
     */
    @ApiOperation("查询闲散流浪人员信息 列表")
//    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpeciaStray tSpeciaStray)
    {
        startPage();
        List<TSpeciaStray> list = tSpeciaStrayService.selectTSpeciaStrayList(tSpeciaStray);
        return getDataTable(list);
    }

    /**
     * 导出闲散流浪人员信息 列表
     */
    @ApiOperation("导出闲散流浪人员信息 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:export')")
    @Log(title = "闲散流浪人员信息 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpeciaStray tSpeciaStray)
    {
        List<TSpeciaStray> list = tSpeciaStrayService.selectTSpeciaStrayList(tSpeciaStray);
        ExcelUtil<TSpeciaStray> util = new ExcelUtil<TSpeciaStray>(TSpeciaStray.class);
        return util.exportExcel(list, "specia_stray");
    }

    /**
     * 获取闲散流浪人员信息 详细信息
     */
    @ApiOperation("获取闲散流浪人员信息 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpeciaStrayService.selectTSpeciaStrayById(id));
    }

    /**
     * 新增闲散流浪人员信息 
     */
    @ApiOperation("新增闲散流浪人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:add')")
    @Log(title = "闲散流浪人员信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpeciaStray tSpeciaStray)
    {
        return toAjax(tSpeciaStrayService.insertTSpeciaStray(tSpeciaStray));
    }

    /**
     * 修改闲散流浪人员信息 
     */
    @ApiOperation("修改闲散流浪人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:edit')")
    @Log(title = "闲散流浪人员信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpeciaStray tSpeciaStray)
    {
        return toAjax(tSpeciaStrayService.updateTSpeciaStray(tSpeciaStray));
    }

    /**
     * 删除闲散流浪人员信息 
     */
    @ApiOperation("删除闲散流浪人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:remove')")
    @Log(title = "闲散流浪人员信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaStrayService.deleteTSpeciaStrayByIds(ids));
    }
    /**
     * 软删除闲散流浪人员信息
     */
    @ApiOperation("软删除闲散流浪人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_stray:remove')")
    @Log(title = "闲散流浪人员信息 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaStrayService.updateTSpeciaStrayByIds(ids));
    }
}
