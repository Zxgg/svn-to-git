package com.isstech.web.controller.communityEvent;

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
import com.isstech.isstechadmin.domain.TSpeciaCorrect;
import com.isstech.isstechadmin.service.ITSpeciaCorrectService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 社区矫正人员信息 Controller
 *
 * @author yzp
 * @date 2021-02-22
 */
@Api(tags ="TSpeciaCorrect", description = "社区矫正人员信息 ")
@RestController
@RequestMapping("/special_personnel/specia_correct")
public class TSpeciaCorrectController extends BaseController
{
    @Autowired
    private ITSpeciaCorrectService tSpeciaCorrectService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区矫正人员信息 列表
     */
    @ApiOperation("查询社区矫正人员信息 列表")
    //@PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpeciaCorrect tSpeciaCorrect)
    {
        tSpeciaCorrect.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tSpeciaCorrect.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TSpeciaCorrect> list = tSpeciaCorrectService.selectTSpeciaCorrectList(tSpeciaCorrect);
        return getDataTable(list);
    }

    /**
     * 导出社区矫正人员信息 列表
     */
    @ApiOperation("导出社区矫正人员信息 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:export')")
    @Log(title = "社区矫正人员信息 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpeciaCorrect tSpeciaCorrect)
    {
        List<TSpeciaCorrect> list = tSpeciaCorrectService.selectTSpeciaCorrectList(tSpeciaCorrect);
        ExcelUtil<TSpeciaCorrect> util = new ExcelUtil<TSpeciaCorrect>(TSpeciaCorrect.class);
        return util.exportExcel(list, "specia_correct");
    }

    /**
     * 获取社区矫正人员信息 详细信息
     */
    @ApiOperation("获取社区矫正人员信息 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpeciaCorrectService.selectTSpeciaCorrectById(id));
    }

    /**
     * 新增社区矫正人员信息 
     */
    @ApiOperation("新增社区矫正人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:add')")
    @Log(title = "社区矫正人员信息 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpeciaCorrect tSpeciaCorrect)
    {
        return toAjax(tSpeciaCorrectService.insertTSpeciaCorrect(tSpeciaCorrect));
    }

    /**
     * 修改社区矫正人员信息 
     */
    @ApiOperation("修改社区矫正人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:edit')")
    @Log(title = "社区矫正人员信息 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpeciaCorrect tSpeciaCorrect)
    {
        return toAjax(tSpeciaCorrectService.updateTSpeciaCorrect(tSpeciaCorrect));
    }

    /**
     * 删除社区矫正人员信息 
     */
    @ApiOperation("删除社区矫正人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:remove')")
    @Log(title = "社区矫正人员信息 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaCorrectService.deleteTSpeciaCorrectByIds(ids));
    }

    /**
     * 软删除社区矫正人员信息 
     */
    @ApiOperation("软删除社区矫正人员信息 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_correct:remove')")
    @Log(title = "社区矫正人员信息 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaCorrectService.updateTSpeciaCorrectByIds(ids));
    }
}
