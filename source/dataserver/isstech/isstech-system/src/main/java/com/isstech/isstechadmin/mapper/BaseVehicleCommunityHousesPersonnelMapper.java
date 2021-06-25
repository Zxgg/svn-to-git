package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVehicleCommunityHousesPersonnel;
import com.isstech.isstechadmin.domain.vo.VehComHousesPersonVo;

/**
 * 车辆和社区房屋和人员关联 Mapper接口
 * 
 * @author yzp
 * @date 2020-11-30
 */
public interface BaseVehicleCommunityHousesPersonnelMapper 
{
    /**
     * 查询UUID
     *
     * @return uuid
     */
    public String selectUuid();

    /**
     * 查询车辆和社区房屋和人员关联 
     * 
     * @param id 车辆和社区房屋和人员关联 ID
     * @return 车辆和社区房屋和人员关联 
     */
    public BaseVehicleCommunityHousesPersonnel selectBaseVehicleCommunityHousesPersonnelById(String id);

    /**
     * 查询车辆和社区房屋和人员关联 列表-人员名称补充
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 车辆和社区房屋和人员关联 集合
     */
    public List<BaseVehicleCommunityHousesPersonnel> selectBaseVehicleCommunityHousesPersonnelList(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel);
    /**
     * 查询车辆和社区房屋和人员关联 列表-人员名称 和 车辆信息补充
     *
     * @param vehComHousesPersonVo 补充车辆信息
     * @return 车辆和社区房屋和人员关联 集合
     */
    public List<VehComHousesPersonVo> selectVehComHousesPersonVoList(VehComHousesPersonVo vehComHousesPersonVo);

    /**
     * 新增车辆和社区房屋和人员关联 
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 结果
     */
    public int insertBaseVehicleCommunityHousesPersonnel(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel);

    /**
     * 修改车辆和社区房屋和人员关联 
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 结果
     */
    public int updateBaseVehicleCommunityHousesPersonnel(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel);

    /**
     * 删除车辆和社区房屋和人员关联 
     * 
     * @param id 车辆和社区房屋和人员关联 ID
     * @return 结果
     */
    public int deleteBaseVehicleCommunityHousesPersonnelById(String id);

    /**
     * 批量删除车辆和社区房屋和人员关联 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVehicleCommunityHousesPersonnelByIds(String[] ids);
    /**
     * 批量软删除车辆和社区房屋和人员关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVehicleCommunityHousesPersonnelByIds(String[] ids);
}
