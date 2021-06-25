package com.isstech.web.controller.communityUnit;

import com.isstech.common.annotation.Log;
import com.isstech.common.core.controller.BaseController;
import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.page.TableDataInfo;
import com.isstech.common.enums.BusinessType;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryVehicleInformation;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.domain.vo.VehiclePersonHouseVo;
import com.isstech.isstechadmin.service.IBaseVehicleInformationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息Controller
 *
 * @author isstech
 * @date 2020-11-23
 */
@Api(tags ="BaseVehicleInformation", description = "车辆信息")
@RestController
@RequestMapping("/communityUnit/vehicle_information")
public class BaseVehicleInformationController extends BaseController
{
    @Autowired
    private IBaseVehicleInformationService baseVehicleInformationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询车辆信息列表
     */
    @ApiOperation("查询车辆信息列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(BaseVehicleInformation baseVehicleInformation)
    {
        baseVehicleInformation.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseVehicleInformation.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseVehicleInformation> list = baseVehicleInformationService.selectBaseVehicleInformationList(baseVehicleInformation);
        return getDataTable(list);
    }

    /**
     * 根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息
     */
    @ApiOperation("根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息")
    @GetMapping("/selectVehicleInformationVoList")
    public TableDataInfo selectVehicleInformationVoList(VehicleInformationVo vehicleInformationVo)
    {
        vehicleInformationVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        vehicleInformationVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<VehicleInformationVo> list = baseVehicleInformationService.selectVehicleInformationVoList(vehicleInformationVo);
        return getDataTable(list);
    }

    /**
     * 导出车辆信息列表
     */
    @ApiOperation("导出车辆信息列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:export')")
    @Log(title = "车辆信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaseVehicleInformation baseVehicleInformation)
    {
        List<BaseVehicleInformation> list = baseVehicleInformationService.selectBaseVehicleInformationList(baseVehicleInformation);
        ExcelUtil<BaseVehicleInformation> util = new ExcelUtil<BaseVehicleInformation>(BaseVehicleInformation.class);
        return util.exportExcel(list, "vehicle_information");
    }

    /**
     * 获取车辆信息详细信息
     */
    @ApiOperation("获取车辆信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseVehicleInformationService.selectBaseVehicleInformationById(id));
    }

    /**
     * 新增车辆信息
     */
    @ApiOperation("新增车辆信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:add')")
    @Log(title = "车辆信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseVehicleInformation baseVehicleInformation)
    {
        return baseVehicleInformationService.insertBaseVehicleInformation(baseVehicleInformation);
    }

    /**
     * 修改车辆信息
     */
    @ApiOperation("修改车辆信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:edit')")
    @Log(title = "车辆信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseVehicleInformation baseVehicleInformation)
    {
        return baseVehicleInformationService.updateBaseVehicleInformation(baseVehicleInformation);
    }

    /**
     * 删除车辆信息
     */
    @ApiOperation("删除车辆信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseVehicleInformationService.deleteBaseVehicleInformationByIds(ids));
    }

    /**
     * 软删除车辆信息
     */
    @ApiOperation("软删除车辆信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:vehicle_information:remove')")
    @Log(title = "车辆信息", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseVehicleInformationService.updateBaseVehicleInformationByIds(ids));
    }

    /**
     * 根据 车牌、车主姓名、车主身份证号码
     * 查询返回  车辆基本信息+车主基本信息
     * */
    @ApiOperation("根据车牌、车主姓名、车主身份证号码，查询 车辆基本信息+车主基本信息")
    @GetMapping("/listAndPerson")
    public TableDataInfo listAndPerson(QueryVehicleInformation queryVehicleInformation)
    {
        if(StringUtils.isBlank(queryVehicleInformation.getPlate())){queryVehicleInformation.setPlate(null);}
        if(StringUtils.isBlank(queryVehicleInformation.getName())){queryVehicleInformation.setName(null);}
        if(StringUtils.isBlank(queryVehicleInformation.getIdentificationNumber())){queryVehicleInformation.setIdentificationNumber(null);  }
        startPage();
        List<Map<String,Object>> list = baseVehicleInformationService.selectVehicleAndPerson(queryVehicleInformation);
        return getDataTable(list);
    }


    /**
     * 查询车人房联合信息
     * @param queryVehicleInformation
     * @return
     */
    @GetMapping("/list/vehiclePersonHouses")
    public TableDataInfo getVehiclePersonHouses(QueryVehicleInformation queryVehicleInformation)
    {
        if(StringUtils.isBlank(queryVehicleInformation.getPlate())){queryVehicleInformation.setPlate(null);}
        if(StringUtils.isBlank(queryVehicleInformation.getName())){queryVehicleInformation.setName(null);}
        if(StringUtils.isBlank(queryVehicleInformation.getIdentificationNumber())){queryVehicleInformation.setIdentificationNumber(null);  }
        startPage();
        List<VehiclePersonHouseVo> list = baseVehicleInformationService.getVehiclePersonHouses(queryVehicleInformation);
        return getDataTable(list);
    }
}
