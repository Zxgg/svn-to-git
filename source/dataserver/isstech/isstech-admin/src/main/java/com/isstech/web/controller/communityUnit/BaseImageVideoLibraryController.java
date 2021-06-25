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
import com.isstech.isstechadmin.domain.BaseImageVideoLibrary;
import com.isstech.isstechadmin.service.IBaseImageVideoLibraryService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 视频信息库Controller
 *
 * @author yzp
 * @date 2021-02-01
 */
@Api(tags ="BaseImageVideoLibrary", description = "视频信息库")
@RestController
@RequestMapping("/imagesInformationLibrarys/imageVideoLibrary")
public class BaseImageVideoLibraryController extends BaseController
{
    @Autowired
    private IBaseImageVideoLibraryService baseImageVideoLibraryService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询视频信息库列表
     */
    @ApiOperation("查询视频信息库列表")
    //@PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseImageVideoLibrary baseImageVideoLibrary)
    {
        baseImageVideoLibrary.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseImageVideoLibrary.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseImageVideoLibrary> list = baseImageVideoLibraryService.selectBaseImageVideoLibraryList(baseImageVideoLibrary);
        return getDataTable(list);
    }

    /**
     * 导出视频信息库列表
     */
    @ApiOperation("导出视频信息库列表")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:export')")
    @Log(title = "视频信息库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseImageVideoLibrary baseImageVideoLibrary)
    {
        List<BaseImageVideoLibrary> list = baseImageVideoLibraryService.selectBaseImageVideoLibraryList(baseImageVideoLibrary);
        ExcelUtil<BaseImageVideoLibrary> util = new ExcelUtil<BaseImageVideoLibrary>(BaseImageVideoLibrary.class);
        return util.exportExcel(list, "imageVideoLibrary");
    }

    /**
     * 获取视频信息库详细信息
     */
    @ApiOperation("获取视频信息库详细信息")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseImageVideoLibraryService.selectBaseImageVideoLibraryById(id));
    }

    /**
     * 新增视频信息库
     */
    @ApiOperation("新增视频信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:add')")
    @Log(title = "视频信息库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseImageVideoLibrary baseImageVideoLibrary)
    {
        return toAjax(baseImageVideoLibraryService.insertBaseImageVideoLibrary(baseImageVideoLibrary));
    }

    /**
     * 修改视频信息库
     */
    @ApiOperation("修改视频信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:edit')")
    @Log(title = "视频信息库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseImageVideoLibrary baseImageVideoLibrary)
    {
        return toAjax(baseImageVideoLibraryService.updateBaseImageVideoLibrary(baseImageVideoLibrary));
    }

    /**
     * 删除视频信息库
     */
    @ApiOperation("删除视频信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:remove')")
    @Log(title = "视频信息库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseImageVideoLibraryService.deleteBaseImageVideoLibraryByIds(ids));
    }

    /**
     * 软删除视频信息库
     */
    @ApiOperation("软删除视频信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:imageVideoLibrary:remove')")
    @Log(title = "视频信息库", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseImageVideoLibraryService.updateBaseImageVideoLibraryByIds(ids));
    }
}
