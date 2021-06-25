package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseVisitorVehicleInformation;
import com.isstech.isstechadmin.service.IBaseVisitorVehicleInformationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseVisitorVehicleInformationMapper;

/**
 * 访客车辆信息Service业务层处理
 * 
 * @author isstech
 * @date 2020-11-23
 */
@Service
public class BaseVisitorVehicleInformationServiceImpl implements IBaseVisitorVehicleInformationService
{
    @Autowired
    private BaseVisitorVehicleInformationMapper baseVisitorVehicleInformationMapper;

    /**
     * 查询访客车辆信息
     * 
     * @param id 访客车辆信息ID
     * @return 访客车辆信息
     */
    @Override
    public BaseVisitorVehicleInformation selectBaseVisitorVehicleInformationById(String id)
    {
        return baseVisitorVehicleInformationMapper.selectBaseVisitorVehicleInformationById(id);
    }

    /**
     * 查询访客车辆信息列表
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 访客车辆信息
     */
    @Override
    public List<BaseVisitorVehicleInformation> selectBaseVisitorVehicleInformationList(BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        return baseVisitorVehicleInformationMapper.selectBaseVisitorVehicleInformationList(baseVisitorVehicleInformation);
    }

    /**
     * 新增访客车辆信息
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 结果
     */
    @Override
    public int insertBaseVisitorVehicleInformation(BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        baseVisitorVehicleInformation.setCreateTime(DateUtils.getNowDate());
        baseVisitorVehicleInformation.setCreateBy(SecurityUtils.getUsername());
        return baseVisitorVehicleInformationMapper.insertBaseVisitorVehicleInformation(baseVisitorVehicleInformation);
    }

    /**
     * 修改访客车辆信息
     * 
     * @param baseVisitorVehicleInformation 访客车辆信息
     * @return 结果
     */
    @Override
    public int updateBaseVisitorVehicleInformation(BaseVisitorVehicleInformation baseVisitorVehicleInformation)
    {
        baseVisitorVehicleInformation.setUpdateTime(DateUtils.getNowDate());
        baseVisitorVehicleInformation.setUpdateBy(SecurityUtils.getUsername());
        return baseVisitorVehicleInformationMapper.updateBaseVisitorVehicleInformation(baseVisitorVehicleInformation);
    }

    /**
     * 批量删除访客车辆信息
     * 
     * @param ids 需要删除的访客车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVisitorVehicleInformationByIds(String[] ids)
    {
        return baseVisitorVehicleInformationMapper.deleteBaseVisitorVehicleInformationByIds(ids);
    }
    /**
     * 批量软删除访客车辆信息
     *
     * @param ids 需要删除的访客车辆信息ID
     * @return 结果
     */
    @Override
    public int updateBaseVisitorVehicleInformationByIds(String[] ids)
    {
        return baseVisitorVehicleInformationMapper.updateBaseVisitorVehicleInformationByIds(ids);
    }

    /**
     * 删除访客车辆信息信息
     * 
     * @param id 访客车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVisitorVehicleInformationById(String id)
    {
        return baseVisitorVehicleInformationMapper.deleteBaseVisitorVehicleInformationById(id);
    }

    @Override
    public BaseVisitorVehicleInformation selectVehicleByPlate(String lpn) {
        return baseVisitorVehicleInformationMapper.selectVehicleByPlate(lpn);
    }
}
