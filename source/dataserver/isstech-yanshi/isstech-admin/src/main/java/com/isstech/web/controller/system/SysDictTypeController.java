package com.isstech.web.controller.system;

import java.util.List;

import com.isstech.common.core.domain.entity.SysDictType;
import com.isstech.isstechadmin.service.ISysDictTypeService;
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
 * 字典类型Controller
 *
 * @author yzp
 * @date 2020-11-11
 */
@Api(tags ="SysDictType", description = "字典类型")
@RestController
@RequestMapping("/system/type")
public class SysDictTypeController extends BaseController
{
    @Autowired
    private ISysDictTypeService sysDictTypeService;

    /**
     * 查询字典类型列表
     */
    @ApiOperation("查询字典类型列表")
//    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictType sysDictType)
    {
        startPage();
        List<SysDictType> list = sysDictTypeService.selectDictTypeList(sysDictType);
        return getDataTable(list);
    }

    /**
     * 导出字典类型列表
     */
    @ApiOperation("导出字典类型列表")
    @PreAuthorize("@ss.hasPermi('system:type:export')")
    @Log(title = "字典类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDictType sysDictType)
    {
        List<SysDictType> list = sysDictTypeService.selectDictTypeList(sysDictType);
        ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>(SysDictType.class);
        return util.exportExcel(list, "type");
    }

    /**
     * 获取字典类型详细信息
     */
    @ApiOperation("获取字典类型详细信息")
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysDictTypeService.selectDictTypeById(id));
    }

    /**
     * 新增字典类型
     */
    @ApiOperation("新增字典类型")
    @PreAuthorize("@ss.hasPermi('system:type:add')")
    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDictType sysDictType)
    {
        return toAjax(sysDictTypeService.insertDictType(sysDictType));
    }

    /**
     * 修改字典类型
     */
    @ApiOperation("修改字典类型")
    @PreAuthorize("@ss.hasPermi('system:type:edit')")
    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDictType sysDictType)
    {
        return toAjax(sysDictTypeService.updateDictType(sysDictType));
    }

    /**
     * 删除字典类型
     */
    @ApiOperation("删除字典类型")
    @PreAuthorize("@ss.hasPermi('system:type:remove')")
    @Log(title = "字典类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDictTypeService.deleteDictTypeByIds(ids));
    }

    /**
     * 清空缓存
     */
    @PreAuthorize("@ss.hasPermi('system:dict:remove')")
    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @PutMapping("/clearCache")
    public AjaxResult clearCache()
    {
        sysDictTypeService.clearCache();
        return AjaxResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/optionselect")
    public AjaxResult optionselect()
    {
        List<SysDictType> dictTypes = sysDictTypeService.selectDictTypeAll();
        return AjaxResult.success(dictTypes);
    }
}
