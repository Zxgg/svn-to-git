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
import com.isstech.isstechadmin.domain.BaseImageVehicleinforLibrary;
import com.isstech.isstechadmin.service.IBaseImageVehicleinforLibraryService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 车辆图像信息库Controller
 *
 * @author yzp
 * @date 2021-02-01
 */
@Api(tags ="BaseImageVehicleinforLibrary", description = "车辆图像信息库")
@RestController
@RequestMapping("/imagesInformationLibrarys/ImageVehicleinforLibrary")
public class BaseImageVehicleinforLibraryController extends BaseController
{
    @Autowired
    private IBaseImageVehicleinforLibraryService baseImageVehicleinforLibraryService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询车辆图像信息库列表
     */
    @ApiOperation("查询车辆图像信息库列表")
    //@PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        baseImageVehicleinforLibrary.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseImageVehicleinforLibrary.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseImageVehicleinforLibrary> list = baseImageVehicleinforLibraryService.selectBaseImageVehicleinforLibraryList(baseImageVehicleinforLibrary);
        return getDataTable(list);
    }

    /**
     * 导出车辆图像信息库列表
     */
    @ApiOperation("导出车辆图像信息库列表")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:export')")
    @Log(title = "车辆图像信息库", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        List<BaseImageVehicleinforLibrary> list = baseImageVehicleinforLibraryService.selectBaseImageVehicleinforLibraryList(baseImageVehicleinforLibrary);
        ExcelUtil<BaseImageVehicleinforLibrary> util = new ExcelUtil<BaseImageVehicleinforLibrary>(BaseImageVehicleinforLibrary.class);
        return util.exportExcel(list, "ImageVehicleinforLibrary");
    }

    /**
     * 获取车辆图像信息库详细信息
     */
    @ApiOperation("获取车辆图像信息库详细信息")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseImageVehicleinforLibraryService.selectBaseImageVehicleinforLibraryById(id));
    }

    /**
     * 新增车辆图像信息库
     */
    @ApiOperation("新增车辆图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:add')")
    @Log(title = "车辆图像信息库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        return toAjax(baseImageVehicleinforLibraryService.insertBaseImageVehicleinforLibrary(baseImageVehicleinforLibrary));
    }

    /**
     * 修改车辆图像信息库
     */
    @ApiOperation("修改车辆图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:edit')")
    @Log(title = "车辆图像信息库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseImageVehicleinforLibrary baseImageVehicleinforLibrary)
    {
        return toAjax(baseImageVehicleinforLibraryService.updateBaseImageVehicleinforLibrary(baseImageVehicleinforLibrary));
    }

    /**
     * 删除车辆图像信息库
     */
    @ApiOperation("删除车辆图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:remove')")
    @Log(title = "车辆图像信息库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseImageVehicleinforLibraryService.deleteBaseImageVehicleinforLibraryByIds(ids));
    }

    /**
     * 软删除车辆图像信息库
     */
    @ApiOperation("软删除车辆图像信息库")
    @PreAuthorize("@ss.hasPermi('imagesInformationLibrarys:ImageVehicleinforLibrary:remove')")
    @Log(title = "车辆图像信息库", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseImageVehicleinforLibraryService.updateBaseImageVehicleinforLibraryByIds(ids));
    }
}
