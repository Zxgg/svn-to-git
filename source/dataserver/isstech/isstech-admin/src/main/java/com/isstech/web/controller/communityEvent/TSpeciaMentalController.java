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
import com.isstech.isstechadmin.domain.TSpeciaMental;
import com.isstech.isstechadmin.service.ITSpeciaMentalService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 易肇事肇祸精神病人 Controller
 *
 * @author yzp
 * @date 2021-02-22
 */
@Api(tags ="TSpeciaMental", description = "易肇事肇祸精神病人 ")
@RestController
@RequestMapping("/special_personnel/specia_mental")
public class TSpeciaMentalController extends BaseController
{
    @Autowired
    private ITSpeciaMentalService tSpeciaMentalService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询易肇事肇祸精神病人 列表
     */
    @ApiOperation("查询易肇事肇祸精神病人 列表")
    //@PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:list')")
    @GetMapping("/list")
    public TableDataInfo list(TSpeciaMental tSpeciaMental)
    {
        tSpeciaMental.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        tSpeciaMental.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<TSpeciaMental> list = tSpeciaMentalService.selectTSpeciaMentalList(tSpeciaMental);
        return getDataTable(list);
    }

    /**
     * 导出易肇事肇祸精神病人 列表
     */
    @ApiOperation("导出易肇事肇祸精神病人 列表")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:export')")
    @Log(title = "易肇事肇祸精神病人 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(TSpeciaMental tSpeciaMental)
    {
        List<TSpeciaMental> list = tSpeciaMentalService.selectTSpeciaMentalList(tSpeciaMental);
        ExcelUtil<TSpeciaMental> util = new ExcelUtil<TSpeciaMental>(TSpeciaMental.class);
        return util.exportExcel(list, "specia_mental");
    }

    /**
     * 获取易肇事肇祸精神病人 详细信息
     */
    @ApiOperation("获取易肇事肇祸精神病人 详细信息")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(tSpeciaMentalService.selectTSpeciaMentalById(id));
    }

    /**
     * 新增易肇事肇祸精神病人 
     */
    @ApiOperation("新增易肇事肇祸精神病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:add')")
    @Log(title = "易肇事肇祸精神病人 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TSpeciaMental tSpeciaMental)
    {
        return toAjax(tSpeciaMentalService.insertTSpeciaMental(tSpeciaMental));
    }

    /**
     * 修改易肇事肇祸精神病人 
     */
    @ApiOperation("修改易肇事肇祸精神病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:edit')")
    @Log(title = "易肇事肇祸精神病人 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TSpeciaMental tSpeciaMental)
    {
        return toAjax(tSpeciaMentalService.updateTSpeciaMental(tSpeciaMental));
    }

    /**
     * 删除易肇事肇祸精神病人 
     */
    @ApiOperation("删除易肇事肇祸精神病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:remove')")
    @Log(title = "易肇事肇祸精神病人 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaMentalService.deleteTSpeciaMentalByIds(ids));
    }

    /**
     * 软删除易肇事肇祸精神病人 
     */
    @ApiOperation("软删除易肇事肇祸精神病人 ")
    @PreAuthorize("@ss.hasPermi('special_personnel:specia_mental:remove')")
    @Log(title = "易肇事肇祸精神病人 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(tSpeciaMentalService.updateTSpeciaMentalByIds(ids));
    }
}
