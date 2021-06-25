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
import com.isstech.isstechadmin.domain.TSpeciaAids;
import com.isstech.isstechadmin.service.ITSpeciaAidsService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 艾滋病人 Controller
 *
 * @author yzp
 * @date 2021-02-22
 */
@Api(tags ="TSpeciaAids", description = "艾滋病人 ")
@RestController
@RequestMapping("/special_personnel/specia_aids")
public class TSpeciaAidsController extends BaseController
{
    @Autowired
    private ITSpeciaAidsService tSpeciaAidsService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询艾滋病人 列表
     */
    @ApiOperation("查询艾滋病人 列表")
    //@PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpeciaAids tSpeciaAids)
    {
        tSpeciaAids.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tSpeciaAids.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TSpeciaAids> list = tSpeciaAidsService.selectTSpeciaAidsList(tSpeciaAids);
        return getDataTable(list);
    }

    /**
     * 导出艾滋病人 列表
     */
    @ApiOperation("导出艾滋病人 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:export')")
    @Log(title = "艾滋病人 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpeciaAids tSpeciaAids)
    {
        List<TSpeciaAids> list = tSpeciaAidsService.selectTSpeciaAidsList(tSpeciaAids);
        ExcelUtil<TSpeciaAids> util = new ExcelUtil<TSpeciaAids>(TSpeciaAids.class);
        return util.exportExcel(list, "specia_aids");
    }

    /**
     * 获取艾滋病人 详细信息
     */
    @ApiOperation("获取艾滋病人 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpeciaAidsService.selectTSpeciaAidsById(id));
    }

    /**
     * 新增艾滋病人 
     */
    @ApiOperation("新增艾滋病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:add')")
    @Log(title = "艾滋病人 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpeciaAids tSpeciaAids)
    {
        return toAjax(tSpeciaAidsService.insertTSpeciaAids(tSpeciaAids));
    }

    /**
     * 修改艾滋病人 
     */
    @ApiOperation("修改艾滋病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:edit')")
    @Log(title = "艾滋病人 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpeciaAids tSpeciaAids)
    {
        return toAjax(tSpeciaAidsService.updateTSpeciaAids(tSpeciaAids));
    }

    /**
     * 删除艾滋病人 
     */
    @ApiOperation("删除艾滋病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:remove')")
    @Log(title = "艾滋病人 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaAidsService.deleteTSpeciaAidsByIds(ids));
    }

    /**
     * 软删除艾滋病人 
     */
    @ApiOperation("软删除艾滋病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_aids:remove')")
    @Log(title = "艾滋病人 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaAidsService.updateTSpeciaAidsByIds(ids));
    }
}
