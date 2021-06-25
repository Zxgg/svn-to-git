package com.isstech.web.controller.communityUnit;

import java.util.List;
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
import com.isstech.isstechadmin.domain.BaseStrangerPersonnel;
import com.isstech.isstechadmin.service.IBaseStrangerPersonnelService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 陌生人Controller
 *
 * @author yzp
 * @date 2021-01-15
 */
@Api(tags ="BaseStrangerPersonnel", description = "陌生人")
@RestController
@RequestMapping("/communityUnit/personnel")
public class BaseStrangerPersonnelController extends BaseController
{
    @Autowired
    private IBaseStrangerPersonnelService baseStrangerPersonnelService;

    /**
     * 查询陌生人列表
     */
    @ApiOperation("查询陌生人列表")
    //@PreAuthorize("@ss.hasPermi('communityUnit:personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseStrangerPersonnel baseStrangerPersonnel)
    {
        startPage();
        List<BaseStrangerPersonnel> list = baseStrangerPersonnelService.selectBaseStrangerPersonnelList(baseStrangerPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出陌生人列表
     */
    @ApiOperation("导出陌生人列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:export')")
    @Log(title = "陌生人", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseStrangerPersonnel baseStrangerPersonnel)
    {
        List<BaseStrangerPersonnel> list = baseStrangerPersonnelService.selectBaseStrangerPersonnelList(baseStrangerPersonnel);
        ExcelUtil<BaseStrangerPersonnel> util = new ExcelUtil<BaseStrangerPersonnel>(BaseStrangerPersonnel.class);
        return util.exportExcel(list, "personnel");
    }

    /**
     * 获取陌生人详细信息
     */
    @ApiOperation("获取陌生人详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseStrangerPersonnelService.selectBaseStrangerPersonnelById(id));
    }

    /**
     * 新增陌生人
     */
    @ApiOperation("新增陌生人")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:add')")
    @Log(title = "陌生人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseStrangerPersonnel baseStrangerPersonnel)
    {
        return toAjax(baseStrangerPersonnelService.insertBaseStrangerPersonnel(baseStrangerPersonnel));
    }

    /**
     * 修改陌生人
     */
    @ApiOperation("修改陌生人")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:edit')")
    @Log(title = "陌生人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseStrangerPersonnel baseStrangerPersonnel)
    {
        return toAjax(baseStrangerPersonnelService.updateBaseStrangerPersonnel(baseStrangerPersonnel));
    }

    /**
     * 删除陌生人
     */
    @ApiOperation("删除陌生人")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:remove')")
    @Log(title = "陌生人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseStrangerPersonnelService.deleteBaseStrangerPersonnelByIds(ids));
    }

    /**
     * 软删除陌生人
     */
    @ApiOperation("软删除陌生人")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel:remove')")
    @Log(title = "陌生人", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseStrangerPersonnelService.updateBaseStrangerPersonnelByIds(ids));
    }
}
