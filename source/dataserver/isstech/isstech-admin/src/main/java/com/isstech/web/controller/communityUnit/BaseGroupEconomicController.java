package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.CommunityGroupEconomicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
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
import com.isstech.isstechadmin.domain.BaseGroupEconomic;
import com.isstech.isstechadmin.service.IBaseGroupEconomicService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 非公经济组织信息Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseGroupEconomic", description = "非公经济组织信息")
@RestController
@RequestMapping("/communityManage/group_economic")
public class BaseGroupEconomicController extends BaseController
{
    @Autowired
    private IBaseGroupEconomicService baseGroupEconomicService;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    /**
     * 查询非公经济组织信息列表
     */
    @ApiOperation("查询非公经济组织信息列表")
//    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityGroupEconomicVo baseGroupEconomic)
    {
        baseGroupEconomic.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseGroupEconomic.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<CommunityGroupEconomicVo> list = baseGroupEconomicService.selectBaseGroupEconomicList(baseGroupEconomic);
        return getDataTable(list);
    }

    /**
     * 导出非公经济组织信息列表
     */
    @ApiOperation("导出非公经济组织信息列表")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:export')")
    @Log(title = "非公经济组织信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunityGroupEconomicVo baseGroupEconomic)
    {
        List<CommunityGroupEconomicVo> list = baseGroupEconomicService.selectBaseGroupEconomicList(baseGroupEconomic);
        ExcelUtil<CommunityGroupEconomicVo> util = new ExcelUtil<CommunityGroupEconomicVo>(CommunityGroupEconomicVo.class);
        return util.exportExcel(list, "group_economic");
    }

    /**
     * 获取非公经济组织信息详细信息
     */
    @ApiOperation("获取非公经济组织信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseGroupEconomicService.selectBaseGroupEconomicById(id));
    }

    /**
     * 新增非公经济组织信息
     */
    @ApiOperation("新增非公经济组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:add')")
    @Log(title = "非公经济组织信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseGroupEconomic baseGroupEconomic, @RequestBody QueryVo vo)
    {
        return toAjax(baseGroupEconomicService.insertBaseGroupEconomic(baseGroupEconomic,vo));
    }

    /**
     * 修改非公经济组织信息
     */
    @ApiOperation("修改非公经济组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:edit')")
    @Log(title = "非公经济组织信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseGroupEconomic baseGroupEconomic, @RequestBody QueryVo vo)
    {
        return toAjax(baseGroupEconomicService.updateBaseGroupEconomic(baseGroupEconomic,vo));
    }

    /**
     * 删除非公经济组织信息
     */
    @ApiOperation("删除非公经济组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:remove')")
    @Log(title = "非公经济组织信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseGroupEconomicService.deleteBaseGroupEconomicByIds(ids));
    }

    /**
     * 软删除非公经济组织信息
     */
    @ApiOperation("软删除非公经济组织信息")
    @PreAuthorize("@ss.hasPermi('communityManage:group_economic:remove')")
    @Log(title = "非公经济组织信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseGroupEconomicService.updateBaseGroupEconomicByIds(ids));
    }
}
