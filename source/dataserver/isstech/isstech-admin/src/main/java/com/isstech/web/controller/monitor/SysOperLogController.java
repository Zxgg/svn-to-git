package com.isstech.web.controller.monitor;

import java.util.List;

import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.SysOperLog;
import com.isstech.isstechadmin.service.ISysOperLogService;
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
import com.isstech.common.core.page.TableDataInfo;

/**
 * 操作日志记录Controller
 *
 * @author yzp
 * @date 2020-11-11
 */
@Api(tags ="SysOperLog", description = "操作日志记录")
@RestController
@RequestMapping("/system/log")
public class SysOperLogController extends BaseController
{
    @Autowired
    private ISysOperLogService sysOperLogService;

    /**
     * 查询操作日志记录列表
     */
    @ApiOperation("查询操作日志记录列表")
//    @PreAuthorize("@ss.hasPermi('system:log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOperLog sysOperLog)
    {
        startPage();
        List<SysOperLog> list = sysOperLogService.selectSysOperLogList(sysOperLog);
        return getDataTable(list);
    }

    /**
     * 导出操作日志记录列表
     */
    @ApiOperation("导出操作日志记录列表")
    @PreAuthorize("@ss.hasPermi('system:log:export')")
    @Log(title = "操作日志记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysOperLog sysOperLog)
    {
        List<SysOperLog> list = sysOperLogService.selectSysOperLogList(sysOperLog);
        ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>(SysOperLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 获取操作日志记录详细信息
     */
    @ApiOperation("获取操作日志记录详细信息")
    @PreAuthorize("@ss.hasPermi('system:log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(sysOperLogService.selectSysOperLogById(id));
    }

    /**
     * 新增操作日志记录
     */
    @ApiOperation("新增操作日志记录")
    @PreAuthorize("@ss.hasPermi('system:log:add')")
    @Log(title = "操作日志记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOperLog sysOperLog)
    {
        return toAjax(sysOperLogService.insertSysOperLog(sysOperLog));
    }

    /**
     * 修改操作日志记录
     */
    @ApiOperation("修改操作日志记录")
    @PreAuthorize("@ss.hasPermi('system:log:edit')")
    @Log(title = "操作日志记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOperLog sysOperLog)
    {
        return toAjax(sysOperLogService.updateSysOperLog(sysOperLog));
    }

    /**
     * 删除操作日志记录
     */
    @ApiOperation("删除操作日志记录")
    @PreAuthorize("@ss.hasPermi('system:log:remove')")
    @Log(title = "操作日志记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysOperLogService.deleteSysOperLogByIds(ids));
    }
}
