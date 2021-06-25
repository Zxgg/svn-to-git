package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryVehicleInformation;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.domain.vo.VehiclePersonHouseVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息Mapper接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface BaseVehicleInformationMapper 
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
    public int insertBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation);

    /**
     * 修改车辆信息
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 结果
     */
    public int updateBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation);

    /**
     * 删除车辆信息
     * 
     * @param id 车辆信息ID
     * @return 结果
     */
    public int deleteBaseVehicleInformationById(String id);

    /**
     * 批量删除车辆信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除车辆信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVehicleInformationByIds(String[] ids);

    public String selectUuid();

    /**
     * 查询返回车辆信息+车主信息
     * */
    //public List<Map<String,Object>> selectVehicleAndPerson(@Param("plate")String plate , @Param("name")String name, @Param("identificationNumber")String identificationNumber);
    public List<Map<String,Object>> selectVehicleAndPerson(QueryVehicleInformation queryVehicleInformation);

    /**
     * 查询车人房联合信息
     * @param queryVehicleInformation
     * @return
     */
    public List<VehiclePersonHouseVo> getVehiclePersonHouses(QueryVehicleInformation queryVehicleInformation);

    @Select("select id ,community_id communityId,community_child_id communityChildId  from base_vehicle_information bvi where plate=#{lpn} and del_flag='0' ")
    public BaseVehicleInformation selectVehicleByPlate(String lpn);

}
