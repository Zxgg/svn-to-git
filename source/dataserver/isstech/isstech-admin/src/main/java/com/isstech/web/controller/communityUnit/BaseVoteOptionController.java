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
import com.isstech.isstechadmin.domain.BaseVoteOption;
import com.isstech.isstechadmin.service.IBaseVoteOptionService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 投票项的选项 Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseVoteOption", description = "投票项的选项 ")
@RestController
@RequestMapping("/communityServices/vote_option")
public class BaseVoteOptionController extends BaseController
{
    @Autowired
    private IBaseVoteOptionService baseVoteOptionService;

    /**
     * 查询投票项的选项 列表
     */
    @ApiOperation("查询投票项的选项 列表")
//    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVoteOption baseVoteOption)
    {
        startPage();
        List<BaseVoteOption> list = baseVoteOptionService.selectBaseVoteOptionList(baseVoteOption);
        return getDataTable(list);
    }

    /**
     * 导出投票项的选项 列表
     */
    @ApiOperation("导出投票项的选项 列表")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:export')")
    @Log(title = "投票项的选项 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVoteOption baseVoteOption)
    {
        List<BaseVoteOption> list = baseVoteOptionService.selectBaseVoteOptionList(baseVoteOption);
        ExcelUtil<BaseVoteOption> util = new ExcelUtil<BaseVoteOption>(BaseVoteOption.class);
        return util.exportExcel(list, "vote_option");
    }

    /**
     * 获取投票项的选项 详细信息
     */
    @ApiOperation("获取投票项的选项 详细信息")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVoteOptionService.selectBaseVoteOptionById(id));
    }

    /**
     * 新增投票项的选项 
     */
    @ApiOperation("新增投票项的选项 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:add')")
    @Log(title = "投票项的选项 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVoteOption baseVoteOption)
    {
        return toAjax(baseVoteOptionService.insertBaseVoteOption(baseVoteOption));
    }

    /**
     * 修改投票项的选项 
     */
    @ApiOperation("修改投票项的选项 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:edit')")
    @Log(title = "投票项的选项 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVoteOption baseVoteOption)
    {
        return toAjax(baseVoteOptionService.updateBaseVoteOption(baseVoteOption));
    }

    /**
     * 删除投票项的选项 
     */
    @ApiOperation("删除投票项的选项 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:remove')")
    @Log(title = "投票项的选项 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVoteOptionService.deleteBaseVoteOptionByIds(ids));
    }
    /**
     * 软删除投票项的选项
     */
    @ApiOperation("软删除投票项的选项 ")
    @PreAuthorize("@ss.hasPermi('communityServices:vote_option:remove')")
    @Log(title = "投票项的选项 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVoteOptionService.updateBaseVoteOptionByIds(ids));
    }
}
