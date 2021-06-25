package com.isstech.web.controller.communityUnit;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.BasePersonnelInformation;
import com.isstech.isstechadmin.domain.vo.PersonDetailVo;
import com.isstech.isstechadmin.domain.vo.PersonHousesVo;
import com.isstech.isstechadmin.domain.vo.PersonInfVo;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.service.IBasePersonnelInformationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 人员信息Controller
 *
 * @author yzp
 * @date 2020-11-23
 */
@Api(tags ="BasePersonnelInformation", description = "人员信息")
@RestController
@RequestMapping("/communityUnit/personnel_information")
public class BasePersonnelInformationController extends BaseController
{
    @Autowired
    private IBasePersonnelInformationService basePersonnelInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询人员信息列表
     */
    @ApiOperation("查询人员信息列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasePersonnelInformation basePersonnelInformation)
    {
        basePersonnelInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        basePersonnelInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BasePersonnelInformation> list = basePersonnelInformationService.selectBasePersonnelInformationList(basePersonnelInformation);
        return getDataTable(list);
    }

    /**
     * 导出人员信息列表
     */
    @ApiOperation("导出人员信息列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:export')")
    @Log(title = "人员信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasePersonnelInformation basePersonnelInformation)
    {
        List<BasePersonnelInformation> list = basePersonnelInformationService.selectBasePersonnelInformationList(basePersonnelInformation);
        ExcelUtil<BasePersonnelInformation> util = new ExcelUtil<BasePersonnelInformation>(BasePersonnelInformation.class);
        return util.exportExcel(list, "personnel_information");
    }

    /**
     * 获取人员信息详细信息
     */
    @ApiOperation("获取人员信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(basePersonnelInformationService.selectBasePersonnelInformationById(id));
    }

    @ApiOperation("根据人员姓名模糊查询人员信息详细信息")
    @GetMapping(value = "/getInfoByName")
    public TableDataInfo getInfoByName(@RequestParam("name") String name,@RequestParam("communityId") String communityId,@RequestParam("communityChildId") String communityChildId)
    {
        startPage();
        List<PersonHousesVo> list = basePersonnelInformationService.selectBasePersonnelInformationByName(name,communityId,communityChildId);
        return getDataTable(list);
    }

    @ApiOperation("根据房屋id查询人员信息")
    @GetMapping(value = "/selectPerInformByHousesId")
    public TableDataInfo selectPerInformByHousesId(@RequestParam("houseId") String houseId)
    {
        startPage();
        List<PersonHousesVo> list = basePersonnelInformationService.selectPerInformByHousesId(houseId);
        return getDataTable(list);
    }

    /**
     * 新增人员信息
     */
    @ApiOperation("新增人员信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:add')")
    @Log(title = "人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasePersonnelInformation basePersonnelInformation)
    {
        return new AjaxResult(200,"操作成功",basePersonnelInformationService.insertBasePersonnelInformation(basePersonnelInformation));
    }

    /**
     * 修改人员信息
     */
    @ApiOperation("修改人员信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:edit')")
    @Log(title = "人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasePersonnelInformation basePersonnelInformation)
    {
        return toAjax(basePersonnelInformationService.updateBasePersonnelInformation(basePersonnelInformation));
    }

    /**
     * 删除人员信息
     */
    @ApiOperation("删除人员信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:remove')")
    @Log(title = "人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(basePersonnelInformationService.deleteBasePersonnelInformationByIds(ids));
    }

    /**
     * 软删除人员信息
     */
    @ApiOperation("软删除人员信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:personnel_information:remove')")
    @Log(title = "人员信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(basePersonnelInformationService.updateBasePersonnelInformationByIds(ids));
    }

    /**
     * 根据车辆的信息查询所关联的房屋信息
     * @param information
     * @return 房屋信息
     * @author qishuwei
     * @date 2020-12-22
     */
    @GetMapping("/getByVehicleInfo")
    public AjaxResult getByVehicleInfo(VehicleInformationVo information){
        List<BasePersonnelInformation> list = basePersonnelInformationService.getByVehicleInfo(information);
        return AjaxResult.success(list);
    }

    /**
     * 批量删除特殊人员
     * @param id
     * @return 操作结果
     * @author qishuwei
     * @date 2020-12-22
     */
    @GetMapping("/removeSpecial")
    public AjaxResult removeSpecial(String[] id){
         return toAjax(basePersonnelInformationService.removeSpecial(id));
    }

    /**
     * 根据网格id汇总人员信息
     * @param gridId 网格id
     * @return
     */
    @ApiOperation("根据网格id汇总人员信息")
    @GetMapping("/getByGridId/{gridId}")
    public AjaxResult removeSpecial(@PathVariable("gridId") String gridId){
        PersonDetailVo personDetailVo = basePersonnelInformationService.sumByGrid(gridId);
        PersonInfVo personInfVo = basePersonnelInformationService.transform(personDetailVo);
        return AjaxResult.success(personInfVo);
    }

    /**
     * 根据网格id汇总人员信息
     * @param gridId 网格id
     * @return
     */
    @ApiOperation("根据网格id汇总人员信息")
    @GetMapping("/getByGridId")
    public AjaxResult removeSpecial2(@RequestParam("gridId") String gridId){
        PersonDetailVo personDetailVo = basePersonnelInformationService.sumByGrid(gridId);
        PersonInfVo personInfVo = basePersonnelInformationService.transform(personDetailVo);
        return AjaxResult.success(personInfVo);
    }

}
