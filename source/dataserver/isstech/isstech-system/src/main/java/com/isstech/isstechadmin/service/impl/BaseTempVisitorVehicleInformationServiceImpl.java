package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseTempVisitorVehicleInformation;
import com.isstech.isstechadmin.mapper.BaseTempVisitorVehicleInformationMapper;
import com.isstech.isstechadmin.service.IBaseTempVisitorVehicleInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 临时访客车辆信息Service业务层处理
 * 
 * @author isstech
 * @date 2020-11-23
 */
@Service
public class BaseTempVisitorVehicleInformationServiceImpl implements IBaseTempVisitorVehicleInformationService
{
    @Autowired
    private BaseTempVisitorVehicleInformationMapper baseTempVisitorVehicleInformationMapper;

    /**
     * 查询临时访客车辆信息
     * 
     * @param id 临时访客车辆信息ID
     * @return 临时访客车辆信息
     */
    @Override
    public BaseTempVisitorVehicleInformation selectBaseTempVisitorVehicleInformationById(String id)
    {
        return baseTempVisitorVehicleInformationMapper.selectBaseTempVisitorVehicleInformationById(id);
    }

    /**
     * 查询临时访客车辆信息列表
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 临时访客车辆信息
     */
    @Override
    public List<BaseTempVisitorVehicleInformation> selectBaseTempVisitorVehicleInformationList(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        return baseTempVisitorVehicleInformationMapper.selectBaseTempVisitorVehicleInformationList(baseTempVisitorVehicleInformation);
    }

    /**
     * 新增临时访客车辆信息
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 结果
     */
    @Override
    public int insertBaseTempVisitorVehicleInformation(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        baseTempVisitorVehicleInformation.setCreateTime(DateUtils.getNowDate());
        baseTempVisitorVehicleInformation.setCreateBy(SecurityUtils.getUsername());
        return baseTempVisitorVehicleInformationMapper.insertBaseTempVisitorVehicleInformation(baseTempVisitorVehicleInformation);
    }

    /**
     * 修改临时访客车辆信息
     * 
     * @param baseTempVisitorVehicleInformation 临时访客车辆信息
     * @return 结果
     */
    @Override
    public int updateBaseTempVisitorVehicleInformation(BaseTempVisitorVehicleInformation baseTempVisitorVehicleInformation)
    {
        baseTempVisitorVehicleInformation.setUpdateTime(DateUtils.getNowDate());
        baseTempVisitorVehicleInformation.setUpdateBy(SecurityUtils.getUsername());
        return baseTempVisitorVehicleInformationMapper.updateBaseTempVisitorVehicleInformation(baseTempVisitorVehicleInformation);
    }

    /**
     * 批量删除临时访客车辆信息
     * 
     * @param ids 需要删除的临时访客车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseTempVisitorVehicleInformationByIds(String[] ids)
    {
        return baseTempVisitorVehicleInformationMapper.deleteBaseTempVisitorVehicleInformationByIds(ids);
    }
    /**
     * 批量软删除临时访客车辆信息
     *
     * @param ids 需要删除的临时访客车辆信息ID
     * @return 结果
     */
    @Override
    public int updateBaseTempVisitorVehicleInformationByIds(String[] ids)
    {
        return baseTempVisitorVehicleInformationMapper.updateBaseTempVisitorVehicleInformationByIds(ids);
    }

    /**
     * 删除临时访客车辆信息信息
     * 
     * @param id 临时访客车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseTempVisitorVehicleInformationById(String id)
    {
        return baseTempVisitorVehicleInformationMapper.deleteBaseTempVisitorVehicleInformationById(id);
    }

    @Override
    public String selectVehicleByPlate(String lpn) {
        return baseTempVisitorVehicleInformationMapper.selectVehicleByPlate(lpn);
    }
}
