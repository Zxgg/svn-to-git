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
import com.isstech.isstechadmin.domain.BaseBlackPersonnel;
import com.isstech.isstechadmin.service.IBaseBlackPersonnelService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 人员黑名单Controller
 *
 * @author yzp
 * @date 2020-12-24
 */
@Api(tags ="BaseBlackPersonnel", description = "人员黑名单")
@RestController
@RequestMapping("/communityUnit/black_personnel")
public class BaseBlackPersonnelController extends BaseController
{
    @Autowired
    private IBaseBlackPersonnelService baseBlackPersonnelService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询人员黑名单列表
     */
    @ApiOperation("查询人员黑名单列表")
//    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseBlackPersonnel baseBlackPersonnel)
    {
        baseBlackPersonnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseBlackPersonnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseBlackPersonnel> list = baseBlackPersonnelService.selectBaseBlackPersonnelList(baseBlackPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出人员黑名单列表
     */
    @ApiOperation("导出人员黑名单列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:export')")
    @Log(title = "人员黑名单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseBlackPersonnel baseBlackPersonnel)
    {
        List<BaseBlackPersonnel> list = baseBlackPersonnelService.selectBaseBlackPersonnelList(baseBlackPersonnel);
        ExcelUtil<BaseBlackPersonnel> util = new ExcelUtil<BaseBlackPersonnel>(BaseBlackPersonnel.class);
        return util.exportExcel(list, "personnel");
    }

    /**
     * 获取人员黑名单详细信息
     */
    @ApiOperation("获取人员黑名单详细信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseBlackPersonnelService.selectBaseBlackPersonnelById(id));
    }

    /**
     * 新增人员黑名单
     */
    @ApiOperation("新增人员黑名单")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:add')")
    @Log(title = "人员黑名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseBlackPersonnel baseBlackPersonnel)
    {
        return toAjax(baseBlackPersonnelService.insertBaseBlackPersonnel(baseBlackPersonnel));
    }

    /**
     * 修改人员黑名单
     */
    @ApiOperation("修改人员黑名单")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:edit')")
    @Log(title = "人员黑名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseBlackPersonnel baseBlackPersonnel)
    {
        return toAjax(baseBlackPersonnelService.updateBaseBlackPersonnel(baseBlackPersonnel));
    }

    /**
     * 删除人员黑名单
     */
    @ApiOperation("删除人员黑名单")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:remove')")
    @Log(title = "人员黑名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseBlackPersonnelService.deleteBaseBlackPersonnelByIds(ids));
    }

    /**
     * 软删除人员黑名单
     */
    @ApiOperation("软删除人员黑名单")
    @PreAuthorize("@ss.hasPermi('isstechadmin:personnel:remove')")
    @Log(title = "人员黑名单", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseBlackPersonnelService.updateBaseBlackPersonnelByIds(ids));
    }
}
