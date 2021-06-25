package com.isstech.web.controller.system;

import java.util.List;

import com.isstech.common.core.domain.entity.SysDictData;
import com.isstech.isstechadmin.service.ISysDictTypeService;
import com.isstech.isstechadmin.service.ISysDictDataService;
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
 * 字典数据Controller
 *
 * @author yzp
 * @date 2020-11-11
 */
@Api(tags ="SysDictData", description = "字典数据")
@RestController
@RequestMapping("/system/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService sysDictDataService;

    @Autowired
    private ISysDictTypeService dictTypeService;
    /**
     * 查询字典数据列表
     */
    @ApiOperation("查询字典数据列表")
//    @PreAuthorize("@ss.hasPermi('system:data:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDictData sysDictData)
    {
        startPage();
        List<SysDictData> list = sysDictDataService.selectDictDataList(sysDictData);
        return getDataTable(list);
    }

    /**
     * 导出字典数据列表
     */
    @ApiOperation("导出字典数据列表")
    @PreAuthorize("@ss.hasPermi('system:data:export')")
    @Log(title = "字典数据", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDictData sysDictData)
    {
        List<SysDictData> list = sysDictDataService.selectDictDataList(sysDictData);
        ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class);
        return util.exportExcel(list, "data");
    }

    /**
     * 获取字典数据详细信息
     */
    @ApiOperation("获取字典数据详细信息")
    @PreAuthorize("@ss.hasPermi('system:data:query')")
    @GetMapping(value = "/{dictCode}")
    public AjaxResult getInfo(@PathVariable("dictCode") Long dictCode)
    {
        return AjaxResult.success(sysDictDataService.selectDictDataById(dictCode));
    }

    /**
     * 新增字典数据
     */
    @ApiOperation("新增字典数据")
    @PreAuthorize("@ss.hasPermi('system:data:add')")
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDictData sysDictData)
    {
        return toAjax(sysDictDataService.insertDictData(sysDictData));
    }

    /**
     * 修改字典数据
     */
    @ApiOperation("修改字典数据")
    @PreAuthorize("@ss.hasPermi('system:data:edit')")
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDictData sysDictData)
    {
        return toAjax(sysDictDataService.updateDictData(sysDictData));
    }

    /**
     * 删除字典数据
     */
    @ApiOperation("删除字典数据")
    @PreAuthorize("@ss.hasPermi('system:data:remove')")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dictCodes}")
    public AjaxResult remove(@PathVariable Long[] dictCodes)
    {
        return toAjax(sysDictDataService.deleteDictDataByIds(dictCodes));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }

    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/type/out/{dictType}")
    public AjaxResult dictOutType(@PathVariable String dictType)
    {
        return AjaxResult.success(dictTypeService.selectDictDataByType(dictType));
    }
}
