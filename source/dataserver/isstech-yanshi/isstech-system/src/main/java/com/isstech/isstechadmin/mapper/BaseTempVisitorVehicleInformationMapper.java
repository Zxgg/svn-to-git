package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseTempVisitorVehicleInformation;
import org.apache.ibatis.annotations.Select;

/**
 * 临时访客车辆信息Mapper接口
 * 
 * @author yzp
 * @date 2020-11-27
 */
public interface BaseTempVisitorVehicleInformationMapper 
{
    /**
     * 查询临时访客车辆信息
     * 
     * @param id 临时访客车辆信息ID
     * @return 临时访客车辆信息
     */
    public BaseTempVisitorVehicleInformation selectBaseTempVisitorVehicleInformationById(String id);

    /**
     * 查询临时访客车辆信息列表
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 临时访客车辆信息集合
     */
    public List<BaseTempVisitorVehicleInformation> selectBaseTempVisitorVehicleInformationList(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation);

    /**
     * 新增临时访客车辆信息
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 结果
     */
    public int insertBaseTempVisitorVehicleInformation(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation);

    /**
     * 修改临时访客车辆信息
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 结果
     */
    public int updateBaseTempVisitorVehicleInformation(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation);

    /**
     * 删除临时访客车辆信息
     * 
     * @param id 临时访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseTempVisitorVehicleInformationById(String id);

    /**
     * 批量删除临时访客车辆信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseTempVisitorVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除临时访客车辆信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseTempVisitorVehicleInformationByIds(String[] ids);

    @Select("select id  from base_temp_visitor_vehicle_information bvvi  where plate = #{lpn} order by create_time desc LIMIT 1")
    public String selectVehicleByPlate(String lpn);
}
