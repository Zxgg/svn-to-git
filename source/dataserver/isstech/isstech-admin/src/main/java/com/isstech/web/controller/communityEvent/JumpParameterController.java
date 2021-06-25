package com.isstech.web.controller.communityEvent;

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
import com.isstech.isstechadmin.domain.JumpParameter;
import com.isstech.isstechadmin.service.IJumpParameterService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 轨迹跳转参数Controller
 *
 * @author yzp
 * @date 2021-01-19
 */
@Api(tags ="JumpParameter", description = "轨迹跳转参数")
@RestController
@RequestMapping("/jump/parameter")
public class JumpParameterController extends BaseController
{
    @Autowired
    private IJumpParameterService jumpParameterService;

    /**
     * 查询轨迹跳转参数列表
     */
    @ApiOperation("查询轨迹跳转参数列表")
    //@PreAuthorize("@ss.hasPermi('jump:parameter:list')")
    @GetMapping("/list")
    public TableDataInfo list(JumpParameter jumpParameter)
    {
        startPage();
        List<JumpParameter> list = jumpParameterService.selectJumpParameterList(jumpParameter);
        return getDataTable(list);
    }

    /**
     * 导出轨迹跳转参数列表
     */
    @ApiOperation("导出轨迹跳转参数列表")
    @PreAuthorize("@ss.hasPermi('jump:parameter:export')")
    @Log(title = "轨迹跳转参数", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JumpParameter jumpParameter)
    {
        List<JumpParameter> list = jumpParameterService.selectJumpParameterList(jumpParameter);
        ExcelUtil<JumpParameter> util = new ExcelUtil<JumpParameter>(JumpParameter.class);
        return util.exportExcel(list, "parameter");
    }

    /**
     * 获取轨迹跳转参数详细信息
     */
    @ApiOperation("获取轨迹跳转参数详细信息")
    @PreAuthorize("@ss.hasPermi('jump:parameter:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(jumpParameterService.selectJumpParameterById(id));
    }

    /**
     * 新增轨迹跳转参数
     */
    @ApiOperation("新增轨迹跳转参数")
    @PreAuthorize("@ss.hasPermi('jump:parameter:add')")
    @Log(title = "轨迹跳转参数", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JumpParameter jumpParameter)
    {
        return toAjax(jumpParameterService.insertJumpParameter(jumpParameter));
    }

    /**
     * 修改轨迹跳转参数
     */
    @ApiOperation("修改轨迹跳转参数")
    @PreAuthorize("@ss.hasPermi('jump:parameter:edit')")
    @Log(title = "轨迹跳转参数", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JumpParameter jumpParameter)
    {
        return toAjax(jumpParameterService.updateJumpParameter(jumpParameter));
    }

    /**
     * 删除轨迹跳转参数
     */
    @ApiOperation("删除轨迹跳转参数")
    @PreAuthorize("@ss.hasPermi('jump:parameter:remove')")
    @Log(title = "轨迹跳转参数", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(jumpParameterService.deleteJumpParameterByIds(ids));
    }
}
