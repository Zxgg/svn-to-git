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
import com.isstech.isstechadmin.domain.BaseStayBehind;
import com.isstech.isstechadmin.service.IBaseStayBehindService;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 留守人员基本信息Controller
 *
 * @author yzp
 * @date 2021-01-15
 */
@Api(tags ="BaseStayBehind", description = "留守人员基本信息")
@RestController
@RequestMapping("/isstechadmin/behind")
public class BaseStayBehindController extends BaseController
{
    @Autowired
    private IBaseStayBehindService baseStayBehindService;

    /**
     * 查询留守人员基本信息列表
     */
    @ApiOperation("查询留守人员基本信息列表")
    //@PreAuthorize("@ss.hasPermi('isstechadmin:behind:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseStayBehind baseStayBehind)
    {
        startPage();
        List<BaseStayBehind> list = baseStayBehindService.selectBaseStayBehindList(baseStayBehind);
        return getDataTable(list);
    }

    /**
     * 导出留守人员基本信息列表
     */
    @ApiOperation("导出留守人员基本信息列表")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:export')")
    @Log(title = "留守人员基本信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseStayBehind baseStayBehind)
    {
        List<BaseStayBehind> list = baseStayBehindService.selectBaseStayBehindList(baseStayBehind);
        ExcelUtil<BaseStayBehind> util = new ExcelUtil<BaseStayBehind>(BaseStayBehind.class);
        return util.exportExcel(list, "behind");
    }

    /**
     * 获取留守人员基本信息详细信息
     */
    @ApiOperation("获取留守人员基本信息详细信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseStayBehindService.selectBaseStayBehindById(id));
    }

    /**
     * 新增留守人员基本信息
     */
    @ApiOperation("新增留守人员基本信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:add')")
    @Log(title = "留守人员基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseStayBehind baseStayBehind)
    {
        return toAjax(baseStayBehindService.insertBaseStayBehind(baseStayBehind));
    }

    /**
     * 修改留守人员基本信息
     */
    @ApiOperation("修改留守人员基本信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:edit')")
    @Log(title = "留守人员基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseStayBehind baseStayBehind)
    {
        return toAjax(baseStayBehindService.updateBaseStayBehind(baseStayBehind));
    }

    /**
     * 删除留守人员基本信息
     */
    @ApiOperation("删除留守人员基本信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:remove')")
    @Log(title = "留守人员基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseStayBehindService.deleteBaseStayBehindByIds(ids));
    }

    /**
     * 软删除留守人员基本信息
     */
    @ApiOperation("软删除留守人员基本信息")
    @PreAuthorize("@ss.hasPermi('isstechadmin:behind:remove')")
    @Log(title = "留守人员基本信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseStayBehindService.updateBaseStayBehindByIds(ids));
    }

    /**
     * 根据人员id查询留守的基本信息
     * @return 留守人的基本信息
     * @author qishuwei
     * @date 2021-01-15
     */
    @GetMapping("/getByPersonId")
    public AjaxResult getByPersonId(String personId){
        return AjaxResult.success(baseStayBehindService.getByPersonId(personId));
    }


}
