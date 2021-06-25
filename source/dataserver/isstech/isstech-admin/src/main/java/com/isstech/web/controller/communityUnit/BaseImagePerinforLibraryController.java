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
import com.isstech.isstechadmin.domain.BaseImagePerinforLibrary;
import com.isstech.isstechadmin.service.IBaseImagePerinforLibraryService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 人员图像信息库Controller
 *
 * @author yzp
 * @date 2021-02-01
 */
@Api(tags ="BaseImagePerinforLibrary", description = "人员图像信息库")
@RestController
@RequestMapping("/imagesInformationLibrarys/ImagePerinforLibrary")
public class BaseImagePerinforLibraryController extends BaseController
{
    @Autowired
    private IBaseImagePerinforLibraryService baseImagePerinforLibraryService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询人员图像信息库列表
     */
    @ApiOperation("查询人员图像信息库列表")
    //@PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        baseImagePerinforLibrary.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseImagePerinforLibrary.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseImagePerinforLibrary> list = baseImagePerinforLibraryService.selectBaseImagePerinforLibraryList(baseImagePerinforLibrary);
        return getDataTable(list);
    }

    /**
     * 导出人员图像信息库列表
     */
    @ApiOperation("导出人员图像信息库列表")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:export')")
    @Log(title = "人员图像信息库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        List<BaseImagePerinforLibrary> list = baseImagePerinforLibraryService.selectBaseImagePerinforLibraryList(baseImagePerinforLibrary);
        ExcelUtil<BaseImagePerinforLibrary> util = new ExcelUtil<BaseImagePerinforLibrary>(BaseImagePerinforLibrary.class);
        return util.exportExcel(list, "ImagePerinforLibrary");
    }

    /**
     * 获取人员图像信息库详细信息
     */
    @ApiOperation("获取人员图像信息库详细信息")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseImagePerinforLibraryService.selectBaseImagePerinforLibraryById(id));
    }

    /**
     * 新增人员图像信息库
     */
    @ApiOperation("新增人员图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:add')")
    @Log(title = "人员图像信息库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        return toAjax(baseImagePerinforLibraryService.insertBaseImagePerinforLibrary(baseImagePerinforLibrary));
    }

    /**
     * 修改人员图像信息库
     */
    @ApiOperation("修改人员图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:edit')")
    @Log(title = "人员图像信息库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseImagePerinforLibrary baseImagePerinforLibrary)
    {
        return toAjax(baseImagePerinforLibraryService.updateBaseImagePerinforLibrary(baseImagePerinforLibrary));
    }

    /**
     * 删除人员图像信息库
     */
    @ApiOperation("删除人员图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:remove')")
    @Log(title = "人员图像信息库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseImagePerinforLibraryService.deleteBaseImagePerinforLibraryByIds(ids));
    }

    /**
     * 软删除人员图像信息库
     */
    @ApiOperation("软删除人员图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImagePerinforLibrary:remove')")
    @Log(title = "人员图像信息库", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseImagePerinforLibraryService.updateBaseImagePerinforLibraryByIds(ids));
    }
}
