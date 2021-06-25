package com.isstech.isstechadmin.service;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryVehicleInformation;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.domain.vo.VehiclePersonHouseVo;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息Service接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface IBaseVehicleInformationService 
{
    /**
     * 查询车辆信息
     * 
     * @param id 车辆信息ID
     * @return 车辆信息
     */
    public BaseVehicleInformation selectBaseVehicleInformationById(String id);

    /**
     * 查询车辆信息列表
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 车辆信息集合
     */
    public List<BaseVehicleInformation> selectBaseVehicleInformationList(BaseVehicleInformation baseVehicleInformation);

    /**
     * 根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息
     *
     * @param vehicleInformationVo 车辆信息补充
     * @return 车辆信息补充集合
     */
    public List<VehicleInformationVo> selectVehicleInformationVoList(VehicleInformationVo vehicleInformationVo);

    /**
     * 新增车辆信息
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 结果
     */
    public AjaxResult insertBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation);

    /**
     * 修改车辆信息
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 结果
     */
    public AjaxResult updateBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation);

    /**
     * 批量删除车辆信息
     * 
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    public int deleteBaseVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除车辆信息
     *
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    public int updateBaseVehicleInformationByIds(String[] ids);

    /**
     * 删除车辆信息信息
     * 
     * @param id 车辆信息ID
     * @return 结果
     */
    public int deleteBaseVehicleInformationById(String id);

    /**
     *  根据车牌号查询车辆信息
     * @param lpn
     * @return
     */
    public BaseVehicleInformation  selectVehicleByPlate(String lpn);

    /**
     *  参数：车牌、车主姓名、车主身份证号码
     * 返回值：车辆基本信息+车主基本信息
     */
    public List<Map<String,Object>> selectVehicleAndPerson(QueryVehicleInformation queryVehicleInformation);

    /**
     * 查询车人房联合信息
     * @param queryVehicleInformation
     * @return
     */
    public List<VehiclePersonHouseVo> getVehiclePersonHouses(QueryVehicleInformation queryVehicleInformation);
}
