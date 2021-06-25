package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.BaseTempVisitorPersonnelInformation;
import com.isstech.isstechadmin.service.IBaseTempVisitorPersonnelInformationService;
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
 * 临时访客人员信息Controller
 *
 * @author isstech
 * @date 2020-11-23
 */
@Api(tags ="BaseTempVisitorPersonnelInformation", description = "临时访客人员信息")
@RestController
@RequestMapping("/passManage/temp_visitor_personnel")
public class BaseTempVisitorPersonnelInformationController extends BaseController
{
    @Autowired
    private IBaseTempVisitorPersonnelInformationService baseTempVisitorPersonnelInformationService;

    /**
     * 查询临时访客人员信息列表
     */
    @ApiOperation("查询临时访客人员信息列表")
//    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        startPage();
        List<BaseTempVisitorPersonnelInformation> list = baseTempVisitorPersonnelInformationService.selectBaseTempVisitorPersonnelInformationList(baseTempVisitorPersonnelInformation);
        return getDataTable(list);
    }

    /**
     * 导出临时访客人员信息列表
     */
    @ApiOperation("导出临时访客人员信息列表")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:export')")
    @Log(title = "临时访客人员信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        List<BaseTempVisitorPersonnelInformation> list = baseTempVisitorPersonnelInformationService.selectBaseTempVisitorPersonnelInformationList(baseTempVisitorPersonnelInformation);
        ExcelUtil<BaseTempVisitorPersonnelInformation> util = new ExcelUtil<BaseTempVisitorPersonnelInformation>(BaseTempVisitorPersonnelInformation.class);
        return util.exportExcel(list, "temp_visitor_personnel");
    }

    /**
     * 获取临时访客人员信息详细信息
     */
    @ApiOperation("获取临时访客人员信息详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseTempVisitorPersonnelInformationService.selectBaseTempVisitorPersonnelInformationById(id));
    }

    /**
     * 新增临时访客人员信息
     */
    @ApiOperation("新增临时访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:add')")
    @Log(title = "临时访客人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        return toAjax(baseTempVisitorPersonnelInformationService.insertBaseTempVisitorPersonnelInformation(baseTempVisitorPersonnelInformation));
    }

    /**
     * 修改临时访客人员信息
     */
    @ApiOperation("修改临时访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:edit')")
    @Log(title = "临时访客人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        return toAjax(baseTempVisitorPersonnelInformationService.updateBaseTempVisitorPersonnelInformation(baseTempVisitorPersonnelInformation));
    }

    /**
     * 删除临时访客人员信息
     */
    @ApiOperation("删除临时访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:remove')")
    @Log(title = "临时访客人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseTempVisitorPersonnelInformationService.deleteBaseTempVisitorPersonnelInformationByIds(ids));
    }
    /**
     * 软删除临时访客人员信息
     */
    @ApiOperation("软删除临时访客人员信息")
    @PreAuthorize("@ss.hasPermi('passManage:temp_visitor_personnel:remove')")
    @Log(title = "临时访客人员信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseTempVisitorPersonnelInformationService.updateBaseTempVisitorPersonnelInformationByIds(ids));
    }
}
