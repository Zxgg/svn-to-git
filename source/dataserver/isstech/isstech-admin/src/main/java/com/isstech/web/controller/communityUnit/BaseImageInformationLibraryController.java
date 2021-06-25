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
import com.isstech.isstechadmin.domain.BaseImageInformationLibrary;
import com.isstech.isstechadmin.service.IBaseImageInformationLibraryService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 图像信息库Controller
 *
 * @author yzp
 * @date 2021-02-01
 */
@Api(tags ="BaseImageInformationLibrary", description = "图像信息库")
@RestController
@RequestMapping("/imagesInformationLibrarys/image_information_library")
public class BaseImageInformationLibraryController extends BaseController
{
    @Autowired
    private IBaseImageInformationLibraryService baseImageInformationLibraryService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询图像信息库列表
     */
    @ApiOperation("查询图像信息库列表")
    //@PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseImageInformationLibrary baseImageInformationLibrary)
    {
        baseImageInformationLibrary.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseImageInformationLibrary.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseImageInformationLibrary> list = baseImageInformationLibraryService.selectBaseImageInformationLibraryList(baseImageInformationLibrary);
        return getDataTable(list);
    }

    /**
     * 导出图像信息库列表
     */
    @ApiOperation("导出图像信息库列表")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:export')")
    @Log(title = "图像信息库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseImageInformationLibrary baseImageInformationLibrary)
    {
        List<BaseImageInformationLibrary> list = baseImageInformationLibraryService.selectBaseImageInformationLibraryList(baseImageInformationLibrary);
        ExcelUtil<BaseImageInformationLibrary> util = new ExcelUtil<BaseImageInformationLibrary>(BaseImageInformationLibrary.class);
        return util.exportExcel(list, "image_information_library");
    }

    /**
     * 获取图像信息库详细信息
     */
    @ApiOperation("获取图像信息库详细信息")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseImageInformationLibraryService.selectBaseImageInformationLibraryById(id));
    }

    /**
     * 新增图像信息库
     */
    @ApiOperation("新增图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:add')")
    @Log(title = "图像信息库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseImageInformationLibrary baseImageInformationLibrary)
    {
        return toAjax(baseImageInformationLibraryService.insertBaseImageInformationLibrary(baseImageInformationLibrary));
    }

    /**
     * 修改图像信息库
     */
    @ApiOperation("修改图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:edit')")
    @Log(title = "图像信息库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseImageInformationLibrary baseImageInformationLibrary)
    {
        return toAjax(baseImageInformationLibraryService.updateBaseImageInformationLibrary(baseImageInformationLibrary));
    }

    /**
     * 删除图像信息库
     */
    @ApiOperation("删除图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:remove')")
    @Log(title = "图像信息库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseImageInformationLibraryService.deleteBaseImageInformationLibraryByIds(ids));
    }

    /**
     * 软删除图像信息库
     */
    @ApiOperation("软删除图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:image_information_library:remove')")
    @Log(title = "图像信息库", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseImageInformationLibraryService.updateBaseImageInformationLibraryByIds(ids));
    }
}
