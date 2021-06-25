package com.isstech.web.controller.communityUnit;

import java.util.List;

import com.isstech.isstechadmin.domain.vo.CaringPerson;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.service.IBaseCaringPersonService;
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
import com.isstech.isstechadmin.domain.BaseCaringPerson;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * 关爱人员信息记录 Controller
 *
 * @author yzp
 * @date 2020-11-25
 */
@Api(tags ="BaseCaringPerson", description = "关爱人员信息记录 ")
@RestController
@RequestMapping("/caringPerson/caring_person")
public class BaseCaringPersonController extends BaseController
{
    @Autowired
    private IBaseCaringPersonService baseCaringPersonService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询关爱人员信息记录 列表
     */
    /*@ApiOperation("查询关爱人员信息记录 列表")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseCaringPerson baseCaringPerson)
    {
        startPage();
        List<BaseCaringPerson> list = baseCaringPersonService.selectBaseCaringPersonList(baseCaringPerson);
        return getDataTable(list);
    }*/

    /**
     * 根据（社区名称、关爱人员名称、关爱类型 ）查询关爱人员信息列表
     */
    @ApiOperation("根据（社区名称、关爱人员名称、关爱类型 ）查询关爱人员信息列表")
//    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaringPerson caringPerson)
    {
        caringPerson.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        caringPerson.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        return getDataTable(baseCaringPersonService.selectBaseCaringAddPersonList(caringPerson));
    }

    /**
     * 导出关爱人员信息记录 列表
     */
    @ApiOperation("导出关爱人员信息记录 列表")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:export')")
    @Log(title = "关爱人员信息记录 ", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CaringPerson caringPerson)
    {
        List<CaringPerson> list = baseCaringPersonService.selectBaseCaringAddPersonList(caringPerson);
        ExcelUtil<CaringPerson> util = new ExcelUtil<CaringPerson>(CaringPerson.class);
        return util.exportExcel(list, "caring_person");
    }

    /**
     * 获取关爱人员信息记录 详细信息
     */
    @ApiOperation("获取关爱人员信息记录 详细信息")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCaringPersonService.selectBaseCaringPersonById(id));
    }

    /**
     * 新增关爱人员信息记录 
     */
    @ApiOperation("新增关爱人员信息记录 ")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:add')")
    @Log(title = "关爱人员信息记录 ", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCaringPerson baseCaringPerson)
    {
        return toAjax(baseCaringPersonService.insertBaseCaringPerson(baseCaringPerson));
    }

    /**
     * 修改关爱人员信息记录 
     */
    @ApiOperation("修改关爱人员信息记录 ")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:edit')")
    @Log(title = "关爱人员信息记录 ", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCaringPerson baseCaringPerson)
    {
        return toAjax(baseCaringPersonService.updateBaseCaringPerson(baseCaringPerson));
    }

    /**
     * 删除关爱人员信息记录 
     */
    @ApiOperation("删除关爱人员信息记录 ")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:remove')")
    @Log(title = "关爱人员信息记录 ", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCaringPersonService.deleteBaseCaringPersonByIds(ids));
    }

    /**
     * 软删除关爱人员信息记录
     */
    @ApiOperation("删除关爱人员信息记录 ")
    @PreAuthorize("@ss.hasPermi('caringPerson:caring_person:remove')")
    @Log(title = "关爱人员信息记录 ", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCaringPersonService.updateBaseCaringPersonByIds(ids));
    }


    /**
     * 查询关爱人员列表
     * @param caringPerson
     * @return 结果
     */
    @GetMapping("/getList")
    public AjaxResult getList(CaringPerson caringPerson){
        List<CaringPerson> caringPersonList = baseCaringPersonService.selectBaseCaringAddPersonList(caringPerson);
        return AjaxResult.success(caringPersonList);
    }

}
