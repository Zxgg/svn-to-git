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
import com.isstech.isstechadmin.domain.BaseVoteRecord;
import com.isstech.isstechadmin.service.IBaseVoteRecordService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 投票记录 Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseVoteRecord", description = "投票记录 ")
@RestController
@RequestMapping("/communityServices/vote_record")
public class BaseVoteRecordController extends BaseController
{
    @Autowired
    private IBaseVoteRecordService baseVoteRecordService;

    /**
     * 查询投票记录 列表
     */
    @ApiOperation("查询投票记录 列表")
//    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVoteRecord baseVoteRecord)
    {
        startPage();
        List<BaseVoteRecord> list = baseVoteRecordService.selectBaseVoteRecordList(baseVoteRecord);
        return getDataTable(list);
    }

    /**
     * 导出投票记录 列表
     */
    @ApiOperation("导出投票记录 列表")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:export')")
    @Log(title = "投票记录 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVoteRecord baseVoteRecord)
    {
        List<BaseVoteRecord> list = baseVoteRecordService.selectBaseVoteRecordList(baseVoteRecord);
        ExcelUtil<BaseVoteRecord> util = new ExcelUtil<BaseVoteRecord>(BaseVoteRecord.class);
        return util.exportExcel(list, "vote_record");
    }

    /**
     * 获取投票记录 详细信息
     */
    @ApiOperation("获取投票记录 详细信息")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVoteRecordService.selectBaseVoteRecordById(id));
    }

    /**
     * 新增投票记录 
     */
    @ApiOperation("新增投票记录 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:add')")
    @Log(title = "投票记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVoteRecord baseVoteRecord)
    {
        return toAjax(baseVoteRecordService.insertBaseVoteRecord(baseVoteRecord));
    }

    /**
     * 修改投票记录 
     */
    @ApiOperation("修改投票记录 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:edit')")
    @Log(title = "投票记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVoteRecord baseVoteRecord)
    {
        return toAjax(baseVoteRecordService.updateBaseVoteRecord(baseVoteRecord));
    }

    /**
     * 删除投票记录 
     */
    @ApiOperation("删除投票记录 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:remove')")
    @Log(title = "投票记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVoteRecordService.deleteBaseVoteRecordByIds(ids));
    }
    /**
     * 软删除投票记录
     */
    @ApiOperation("删除投票记录 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_record:remove')")
    @Log(title = "投票记录 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVoteRecordService.updateBaseVoteRecordByIds(ids));
    }

    /**
     * 投票记录按照投票选项进行分组，返回每个选项的投票个数
     */
    @ApiOperation("投票记录按照投票选项进行分组，返回每个选项的投票个数")
    @GetMapping("/countVoteOption")
    public AjaxResult countVoteOption()
    {
        return AjaxResult.success(baseVoteRecordService.countVoteOption());
    }
}
