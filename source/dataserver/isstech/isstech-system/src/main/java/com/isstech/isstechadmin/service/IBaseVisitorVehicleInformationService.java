package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVisitorVehicleInformation;

/**
 * 访客车辆信息Service接口
 * 
 * @author isstech
 * @date 2020-11-23
 */
public interface IBaseVisitorVehicleInformationService 
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
     * 批量删除访客车辆信息
     * 
     * @param ids 需要删除的访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseVisitorVehicleInformationByIds(String[] ids);
    /**
     * 批量软删除访客车辆信息
     *
     * @param ids 需要删除的访客车辆信息ID
     * @return 结果
     */
    public int updateBaseVisitorVehicleInformationByIds(String[] ids);

    /**
     * 删除访客车辆信息信息
     * 
     * @param id 访客车辆信息ID
     * @return 结果
     */
    public int deleteBaseVisitorVehicleInformationById(String id);

    public BaseVisitorVehicleInformation selectVehicleByPlate(String lpn);
}
