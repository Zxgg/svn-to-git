package com.isstech.web.controller.communityEvent;

import java.util.List;

import com.isstech.isstechadmin.domain.TSpecialYoung;
import com.isstech.isstechadmin.service.ITSpecialYoungService;
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
 * 重点青少年 Controller
 *
 * @author yzp
 * @date 2020-12-08
 */
@Api(tags ="TSpecialYoung", description = "重点青少年 ")
@RestController
@RequestMapping("/special_personnel/special_young")
public class TSpecialYoungController extends BaseController
{
    @Autowired
    private ITSpecialYoungService tSpecialYoungService;

    /**
     * 查询重点青少年 列表
     */
    @ApiOperation("查询重点青少年 列表")
//    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpecialYoung tSpecialYoung)
    {
        startPage();
        List<TSpecialYoung> list = tSpecialYoungService.selectTSpecialYoungList(tSpecialYoung);
        return getDataTable(list);
    }

    /**
     * 导出重点青少年 列表
     */
    @ApiOperation("导出重点青少年 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:export')")
    @Log(title = "重点青少年 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpecialYoung tSpecialYoung)
    {
        List<TSpecialYoung> list = tSpecialYoungService.selectTSpecialYoungList(tSpecialYoung);
        ExcelUtil<TSpecialYoung> util = new ExcelUtil<TSpecialYoung>(TSpecialYoung.class);
        return util.exportExcel(list, "special_young");
    }

    /**
     * 获取重点青少年 详细信息
     */
    @ApiOperation("获取重点青少年 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpecialYoungService.selectTSpecialYoungById(id));
    }

    /**
     * 新增重点青少年 
     */
    @ApiOperation("新增重点青少年 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:add')")
    @Log(title = "重点青少年 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpecialYoung tSpecialYoung)
    {
        return toAjax(tSpecialYoungService.insertTSpecialYoung(tSpecialYoung));
    }

    /**
     * 修改重点青少年 
     */
    @ApiOperation("修改重点青少年 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:edit')")
    @Log(title = "重点青少年 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpecialYoung tSpecialYoung)
    {
        return toAjax(tSpecialYoungService.updateTSpecialYoung(tSpecialYoung));
    }

    /**
     * 删除重点青少年 
     */
    @ApiOperation("删除重点青少年 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:remove')")
    @Log(title = "重点青少年 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpecialYoungService.deleteTSpecialYoungByIds(ids));
    }

    /**
     * 软删除重点青少年
     */
    @ApiOperation("软删除重点青少年 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:special_young:remove')")
    @Log(title = "重点青少年 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpecialYoungService.updateTSpecialYoungByIds(ids));
    }
}
