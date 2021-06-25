package com.isstech.web.controller.communityUnit;

import java.util.List;
import java.util.Map;

import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryCommunityHouses;
import com.isstech.isstechadmin.domain.vo.CommunityHousesVo;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.service.IBaseCommunityHousesService;
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
import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import com.isstech.common.utils.poi.ExcelUtil;
import com.isstech.common.core.page.TableDataInfo;

/**
 * @Description 社区房屋信息Controller
 * @Author yzp
 * @Date 2020/11/18 14:35
 */
@Api(tags ="BaseCommunityHouses", description = "社区房屋信息")
@RestController
@RequestMapping("/communityUnit/community_houses")
public class BaseCommunityHousesController extends BaseController
{
    @Autowired
    private IBaseCommunityHousesService baseCommunityHousesService;
    @Autowired
    private DataAccessUtils dataAccessUtils;

    /**
     * 查询社区房屋信息列表
     */
    @ApiOperation("查询社区房屋信息列表")
//    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommunityHousesVo communityHousesVo)
    {
        communityHousesVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        communityHousesVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<BaseCommunityHouses> list = baseCommunityHousesService.selectBaseCommunityHousesList(communityHousesVo);
        return getDataTable(list);
    }

    /**
     * 根据（人员ID、是否历史-delFlag）查询所有关联的房屋
     */
    @ApiOperation("根据（人员ID、是否历史-delFlag）查询所有关联的房屋")
//    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:list')")
    @GetMapping("/queryList")
    public TableDataInfo queryList(CommunityHousesVo communityHousesVo)
    {
        communityHousesVo.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        communityHousesVo.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        startPage();
        List<CommunityHousesVo> list = baseCommunityHousesService.queryCommunityHousesVoList(communityHousesVo);
        return getDataTable(list);
    }

    /**
     * 导出社区房屋信息列表
     */
    @ApiOperation("导出社区房屋信息列表")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:export')")
    @Log(title = "社区房屋信息-导出", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommunityHousesVo communityHousesVo)
    {
        List<BaseCommunityHouses> list = baseCommunityHousesService.selectBaseCommunityHousesList(communityHousesVo);
        ExcelUtil<BaseCommunityHouses> util = new ExcelUtil<BaseCommunityHouses>(BaseCommunityHouses.class);
        return util.exportExcel(list, "community_houses");
    }

    /**
     * 获取社区房屋信息详细信息
     */
    @ApiOperation("获取社区房屋信息详细信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(baseCommunityHousesService.selectBaseCommunityHousesById(id));
    }

    /**
     * 新增社区房屋信息
     */
    @ApiOperation("新增社区房屋信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:add')")
    @Log(title = "社区房屋信息-新增", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaseCommunityHouses baseCommunityHouses)
    {
        return new AjaxResult(200,"操作成功",baseCommunityHousesService.insertBaseCommunityHouses(baseCommunityHouses));
    }

    /**
     * 修改社区房屋信息
     */
    @ApiOperation("修改社区房屋信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:edit')")
    @Log(title = "社区房屋信息-修改", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaseCommunityHouses baseCommunityHouses)
    {
        return toAjax(baseCommunityHousesService.updateBaseCommunityHouses(baseCommunityHouses));
    }

    /**
     * 删除社区房屋信息
     */
    @ApiOperation("删除社区房屋信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:remove')")
    @Log(title = "社区房屋信息-删除", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityHousesService.deleteBaseCommunityHousesByIds(ids));
    }

    /**
     * 软删除社区房屋信息
     */
    @ApiOperation("删除社区房屋信息")
    @PreAuthorize("@ss.hasPermi('communityUnit:community_houses:remove')")
    @Log(title = "社区房屋信息-删除", businessType = BusinessType.DELETE)
	@PutMapping("/{ids}")
    public AjaxResult update(@PathVariable String[] ids)
    {
        return toAjax(baseCommunityHousesService.updateBaseCommunityHousesByIds(ids));
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
        List<BaseCommunityHouses> list = baseCommunityHousesService.getByVehicleInfo(information);
        return AjaxResult.success(list);
    }


    /**
     * 根据（房主姓名、房间号）查询房屋信息列表：
     *  返回字段包括：户主基本信息、房屋所有信息
     */
    @ApiOperation("根据（人员ID、是否历史-delFlag）查询所有关联的房屋")
    @GetMapping("/queryHouseAndPersonList")
    public TableDataInfo queryHouseAndPersonList(QueryCommunityHouses queryCommunityHouses)
    {
        if(StringUtils.isBlank(queryCommunityHouses.getName())){queryCommunityHouses.setName(null);}
        if(StringUtils.isBlank(queryCommunityHouses.getPersonName())){queryCommunityHouses.setPersonName(null); }
        startPage();
        List<Map<String,Object>> list = baseCommunityHousesService.queryHouseAndPersonList(queryCommunityHouses);
        return getDataTable(list);
    }


}
