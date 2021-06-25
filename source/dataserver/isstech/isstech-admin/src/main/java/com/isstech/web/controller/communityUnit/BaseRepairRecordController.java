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
import com.isstech.isstechadmin.domain.BaseRepairRecord;
import com.isstech.isstechadmin.service.IBaseRepairRecordService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 报事报修记录Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseRepairRecord", description = "报事报修记录")
@RestController
@RequestMapping("/communityServices/repair_record")
public class BaseRepairRecordController extends BaseController
{
    @Autowired
    private IBaseRepairRecordService baseRepairRecordService;

    /**
     * 查询报事报修记录列表
     */
    @ApiOperation("查询报事报修记录列表")
//    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseRepairRecord baseRepairRecord)
    {
        startPage();
        List<BaseRepairRecord> list = baseRepairRecordService.selectBaseRepairRecordList(baseRepairRecord);
        return getDataTable(list);
    }

    /**
     * 导出报事报修记录列表
     */
    @ApiOperation("导出报事报修记录列表")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:export')")
    @Log(title = "报事报修记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseRepairRecord baseRepairRecord)
    {
        List<BaseRepairRecord> list = baseRepairRecordService.selectBaseRepairRecordList(baseRepairRecord);
        ExcelUtil<BaseRepairRecord> util = new ExcelUtil<BaseRepairRecord>(BaseRepairRecord.class);
        return util.exportExcel(list, "repair_record");
    }

    /**
     * 获取报事报修记录详细信息
     */
    @ApiOperation("获取报事报修记录详细信息")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseRepairRecordService.selectBaseRepairRecordById(id));
    }

    /**
     * 新增报事报修记录
     */
    @ApiOperation("新增报事报修记录")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:add')")
    @Log(title = "报事报修记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseRepairRecord baseRepairRecord)
    {
        return toAjax(baseRepairRecordService.insertBaseRepairRecord(baseRepairRecord));
    }

    /**
     * 修改报事报修记录
     */
    @ApiOperation("修改报事报修记录")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:edit')")
    @Log(title = "报事报修记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseRepairRecord baseRepairRecord)
    {
        return toAjax(baseRepairRecordService.updateBaseRepairRecord(baseRepairRecord));
    }

    /**
     * 删除报事报修记录
     */
    @ApiOperation("删除报事报修记录")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:remove')")
    @Log(title = "报事报修记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseRepairRecordService.deleteBaseRepairRecordByIds(ids));
    }

    /**
     * 软删除报事报修记录
     */
    @ApiOperation("软删除报事报修记录")
    @PreAuthorize("@ss.hasPermi('communityServices:repair_record:remove')")
    @Log(title = "报事报修记录", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseRepairRecordService.updateBaseRepairRecordByIds(ids));
    }
}
