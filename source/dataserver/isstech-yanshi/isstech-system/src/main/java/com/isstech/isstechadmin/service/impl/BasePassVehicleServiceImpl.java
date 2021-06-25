package com.isstech.isstechadmin.service.impl;

import java.util.List;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.PassVehicleVo;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BasePassVehicleMapper;
import com.isstech.isstechadmin.domain.BasePassVehicle;
import com.isstech.isstechadmin.service.IBasePassVehicleService;

/**
 * 车辆通行Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-12
 */
@Service
public class BasePassVehicleServiceImpl implements IBasePassVehicleService 
{
    @Autowired
    private BasePassVehicleMapper basePassVehicleMapper;

    /**
     * 查询车辆通行
     * 
     * @param motorVehicleId 车辆通行ID
     * @return 车辆通行
     */
    @Override
    public BasePassVehicle selectBasePassVehicleById(String motorVehicleId)
    {
        return basePassVehicleMapper.selectBasePassVehicleById(motorVehicleId);
    }

    /**
     * 查询车辆通行列表
     * 
     * @param passVehicleVo 车辆通行
     * @return 车辆通行
     */
    @Override
    public List<BasePassVehicle> selectBasePassVehicleList(PassVehicleVo passVehicleVo)
    {
        String startPass = DateUtils.timeStamp4Date2String(passVehicleVo.getStartPassTime());
        passVehicleVo.setStartPassTime(startPass);
        String endPass = DateUtils.timeStamp4Date2String(passVehicleVo.getEndPassTime());
        passVehicleVo.setEndPassTime(endPass);
        return basePassVehicleMapper.selectBasePassVehicleList(passVehicleVo);
    }

    /**
     * 新增车辆通行
     * 
     * @param basePassVehicle 车辆通行
     * @return 结果
     */
    @Override
    public int insertBasePassVehicle(BasePassVehicle basePassVehicle)
    {
        basePassVehicle.setCreateTime(DateUtils.getNowDate());
        basePassVehicle.setAppearTime(DateUtils.getNowDate());
        basePassVehicle.setCreateBy(SecurityUtils.getUsername());
        return basePassVehicleMapper.insertBasePassVehicle(basePassVehicle);
    }

    /**
     * 修改车辆通行
     * 
     * @param basePassVehicle 车辆通行
     * @return 结果
     */
    @Override
    public int updateBasePassVehicle(BasePassVehicle basePassVehicle)
    {
        basePassVehicle.setCreateTime(DateUtils.getNowDate());
        basePassVehicle.setCreateBy(SecurityUtils.getUsername());
        return basePassVehicleMapper.updateBasePassVehicle(basePassVehicle);
    }

    /**
     * 批量删除车辆通行
     * 
     * @param motorVehicleIds 需要删除的车辆通行ID
     * @return 结果
     */
    @Override
    public int deleteBasePassVehicleByIds(String[] motorVehicleIds)
    {
        return basePassVehicleMapper.deleteBasePassVehicleByIds(motorVehicleIds);
    }
    /**
     * 批量软删除车辆通行
     *
     * @param motorVehicleIds 需要删除的车辆通行ID
     * @return 结果
     */
    @Override
    public int updateBasePassVehicleByIds(String[] motorVehicleIds)
    {
        return basePassVehicleMapper.updateBasePassVehicleByIds(motorVehicleIds);
    }

    /**
     * 删除车辆通行信息
     * 
     * @param motorVehicleId 车辆通行ID
     * @return 结果
     */
    @Override
    public int deleteBasePassVehicleById(String motorVehicleId)
    {
        return basePassVehicleMapper.deleteBasePassVehicleById(motorVehicleId);
    }
}
