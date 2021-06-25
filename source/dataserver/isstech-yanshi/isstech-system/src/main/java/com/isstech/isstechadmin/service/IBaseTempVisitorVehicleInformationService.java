package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseTempVisitorVehicleInformation;

/**
 * 临时访客车辆信息Service接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface IBaseTempVisitorVehicleInformationService 
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
     * 批量删除临时访客车辆信息
     * 
     * @param ids 需要删除的临时访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseTempVisitorVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除临时访客车辆信息
     *
     * @param ids 需要删除的临时访客车辆信息ID
     * @return 结果
     */
    public int updateBaseTempVisitorVehicleInformationByIds(String[] ids);

    /**
     * 删除临时访客车辆信息信息
     * 
     * @param id 临时访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseTempVisitorVehicleInformationById(String id);

    public String selectVehicleByPlate(String lpn);
}
