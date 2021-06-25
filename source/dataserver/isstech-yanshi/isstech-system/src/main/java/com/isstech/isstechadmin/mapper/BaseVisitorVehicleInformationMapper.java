package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVisitorVehicleInformation;
import org.apache.ibatis.annotations.Select;

/**
 * 访客车辆信息Mapper接口
 * 
 * @author yzp
 * @date 2020-11-27
 */
public interface BaseVisitorVehicleInformationMapper 
{
    /**
     * 查询访客车辆信息
     * 
     * @param id 访客车辆信息ID
     * @return 访客车辆信息
     */
    public BaseVisitorVehicleInformation selectBaseVisitorVehicleInformationById(String id);

    /**
     * 查询访客车辆信息列表
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 访客车辆信息集合
     */
    public List<BaseVisitorVehicleInformation> selectBaseVisitorVehicleInformationList(BaseVisitorVehicleInformation baseVisitorVehicleInformation);

    /**
     * 新增访客车辆信息
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 结果
     */
    public int insertBaseVisitorVehicleInformation(BaseVisitorVehicleInformation baseVisitorVehicleInformation);

    /**
     * 修改访客车辆信息
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 结果
     */
    public int updateBaseVisitorVehicleInformation(BaseVisitorVehicleInformation baseVisitorVehicleInformation);

    /**
     * 删除访客车辆信息
     * 
     * @param id 访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseVisitorVehicleInformationById(String id);

    /**
     * 批量删除访客车辆信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVisitorVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除访客车辆信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVisitorVehicleInformationByIds(String[] ids);

    @Select("select id ,target_person_community_id targetPersonCommunityId,target_person_community_child_id targetPersonCommunityChildId  from base_visitor_vehicle_information bvvi  where plate =#{lpn} order by create_time desc LIMIT 1 ")
    public BaseVisitorVehicleInformation selectVehicleByPlate(String lpn);
}
