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
import com.isstech.isstechadmin.domain.BaseOpinionRecord;
import com.isstech.isstechadmin.service.IBaseOpinionRecordService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 投诉意见记录Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseOpinionRecord", description = "投诉意见记录")
@RestController
@RequestMapping("/communityServices/opinion_record")
public class BaseOpinionRecordController extends BaseController
{
    @Autowired
    private IBaseOpinionRecordService baseOpinionRecordService;

    /**
     * 查询投诉意见记录列表
     */
    @ApiOperation("查询投诉意见记录列表")
//    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseOpinionRecord baseOpinionRecord)
    {
        startPage();
        List<BaseOpinionRecord> list = baseOpinionRecordService.selectBaseOpinionRecordList(baseOpinionRecord);
        return getDataTable(list);
    }

    /**
     * 导出投诉意见记录列表
     */
    @ApiOperation("导出投诉意见记录列表")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:export')")
    @Log(title = "投诉意见记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseOpinionRecord baseOpinionRecord)
    {
        List<BaseOpinionRecord> list = baseOpinionRecordService.selectBaseOpinionRecordList(baseOpinionRecord);
        ExcelUtil<BaseOpinionRecord> util = new ExcelUtil<BaseOpinionRecord>(BaseOpinionRecord.class);
        return util.exportExcel(list, "opinion_record");
    }

    /**
     * 获取投诉意见记录详细信息
     */
    @ApiOperation("获取投诉意见记录详细信息")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseOpinionRecordService.selectBaseOpinionRecordById(id));
    }

    /**
     * 新增投诉意见记录
     */
    @ApiOperation("新增投诉意见记录")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:add')")
    @Log(title = "投诉意见记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseOpinionRecord baseOpinionRecord)
    {
        return toAjax(baseOpinionRecordService.insertBaseOpinionRecord(baseOpinionRecord));
    }

    /**
     * 修改投诉意见记录
     */
    @ApiOperation("修改投诉意见记录")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:edit')")
    @Log(title = "投诉意见记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseOpinionRecord baseOpinionRecord)
    {
        return toAjax(baseOpinionRecordService.updateBaseOpinionRecord(baseOpinionRecord));
    }

    /**
     * 删除投诉意见记录
     */
    @ApiOperation("删除投诉意见记录")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:remove')")
    @Log(title = "投诉意见记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseOpinionRecordService.deleteBaseOpinionRecordByIds(ids));
    }

    /**
     * 软删除投诉意见记录
     */
    @ApiOperation("软删除投诉意见记录")
    @PreAuthorize("@ss.hasPermi('communityServices:opinion_record:remove')")
    @Log(title = "投诉意见记录", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseOpinionRecordService.updateBaseOpinionRecordByIds(ids));
    }
}
