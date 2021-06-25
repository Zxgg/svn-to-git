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
import com.isstech.isstechadmin.domain.BaseAlarmPersonnel;
import com.isstech.isstechadmin.service.IBaseAlarmPersonnelService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 告警人员Controller
 *
 * @author yzp
 * @date 2021-04-26
 */
@Api(tags ="BaseAlarmPersonnel", description = "告警人员")
@RestController
@RequestMapping("/isstechadmin/alarm_personnel")
public class BaseAlarmPersonnelController extends BaseController
{
    @Autowired
    private IBaseAlarmPersonnelService baseAlarmPersonnelService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询告警人员列表
     */
    @ApiOperation("查询告警人员列表")
    //@PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseAlarmPersonnel baseAlarmPersonnel)
    {
        baseAlarmPersonnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseAlarmPersonnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseAlarmPersonnel> list = baseAlarmPersonnelService.selectBaseAlarmPersonnelList(baseAlarmPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出告警人员列表
     */
    @ApiOperation("导出告警人员列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:export')")
    @Log(title = "告警人员", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseAlarmPersonnel baseAlarmPersonnel)
    {
        List<BaseAlarmPersonnel> list = baseAlarmPersonnelService.selectBaseAlarmPersonnelList(baseAlarmPersonnel);
        ExcelUtil<BaseAlarmPersonnel> util = new ExcelUtil<BaseAlarmPersonnel>(BaseAlarmPersonnel.class);
        return util.exportExcel(list, "alarm_personnel");
    }

    /**
     * 获取告警人员详细信息
     */
    @ApiOperation("获取告警人员详细信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseAlarmPersonnelService.selectBaseAlarmPersonnelById(id));
    }

    /**
     * 新增告警人员
     */
    @ApiOperation("新增告警人员")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:add')")
    @Log(title = "告警人员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseAlarmPersonnel baseAlarmPersonnel)
    {
        return toAjax(baseAlarmPersonnelService.insertBaseAlarmPersonnel(baseAlarmPersonnel));
    }

    /**
     * 修改告警人员
     */
    @ApiOperation("修改告警人员")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:edit')")
    @Log(title = "告警人员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseAlarmPersonnel baseAlarmPersonnel)
    {
        return toAjax(baseAlarmPersonnelService.updateBaseAlarmPersonnel(baseAlarmPersonnel));
    }

    /**
     * 删除告警人员
     */
    @ApiOperation("删除告警人员")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:remove')")
    @Log(title = "告警人员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseAlarmPersonnelService.deleteBaseAlarmPersonnelByIds(ids));
    }

    /**
     * 软删除告警人员
     */
    @ApiOperation("软删除告警人员")
    @PreAuthorize("@ss.hasPermi('isstechadmin:alarm_personnel:remove')")
    @Log(title = "告警人员", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseAlarmPersonnelService.updateBaseAlarmPersonnelByIds(ids));
    }
}
