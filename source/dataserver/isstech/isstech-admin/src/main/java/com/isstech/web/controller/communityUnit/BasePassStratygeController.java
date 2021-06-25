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
import com.isstech.isstechadmin.domain.BasePassStratyge;
import com.isstech.isstechadmin.service.IBasePassStratygeService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 通行策略 Controller
 *
 * @author yzp
 * @date 2021-02-01
 */
@Api(tags ="BasePassStratyge", description = "通行策略 ")
@RestController
@RequestMapping("/passManage/pass_stratyge")
public class BasePassStratygeController extends BaseController
{
    @Autowired
    private IBasePassStratygeService basePassStratygeService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询通行策略 列表
     */
    @ApiOperation("查询通行策略 列表")
    //@PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePassStratyge basePassStratyge)
    {
        basePassStratyge.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        basePassStratyge.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePassStratyge> list = basePassStratygeService.selectBasePassStratygeList(basePassStratyge);
        return getDataTable(list);
    }

    /**
     * 导出通行策略 列表
     */
    @ApiOperation("导出通行策略 列表")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:export')")
    @Log(title = "通行策略 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasePassStratyge basePassStratyge)
    {
        List<BasePassStratyge> list = basePassStratygeService.selectBasePassStratygeList(basePassStratyge);
        ExcelUtil<BasePassStratyge> util = new ExcelUtil<BasePassStratyge>(BasePassStratyge.class);
        return util.exportExcel(list, "pass_stratyge");
    }

    /**
     * 获取通行策略 详细信息
     */
    @ApiOperation("获取通行策略 详细信息")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basePassStratygeService.selectBasePassStratygeById(id));
    }

    /**
     * 新增通行策略 
     */
    @ApiOperation("新增通行策略 ")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:add')")
    @Log(title = "通行策略 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePassStratyge basePassStratyge)
    {
        return toAjax(basePassStratygeService.insertBasePassStratyge(basePassStratyge));
    }

    /**
     * 修改通行策略 
     */
    @ApiOperation("修改通行策略 ")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:edit')")
    @Log(title = "通行策略 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePassStratyge basePassStratyge)
    {
        return toAjax(basePassStratygeService.updateBasePassStratyge(basePassStratyge));
    }

    /**
     * 删除通行策略 
     */
    @ApiOperation("删除通行策略 ")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:remove')")
    @Log(title = "通行策略 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basePassStratygeService.deleteBasePassStratygeByIds(ids));
    }

    /**
     * 软删除通行策略 
     */
    @ApiOperation("软删除通行策略 ")
    @PreAuthorize("@ss.hasPermi('passManage:pass_stratyge:remove')")
    @Log(title = "通行策略 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(basePassStratygeService.updateBasePassStratygeByIds(ids));
    }
}
