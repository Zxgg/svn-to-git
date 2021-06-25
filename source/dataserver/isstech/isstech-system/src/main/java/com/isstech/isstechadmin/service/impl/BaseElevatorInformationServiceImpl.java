package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseElevatorInformationMapper;
import com.isstech.isstechadmin.domain.BaseElevatorInformation;
import com.isstech.isstechadmin.service.IBaseElevatorInformationService;

/**
 * 电梯信息Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseElevatorInformationServiceImpl implements IBaseElevatorInformationService 
{
    @Autowired
    private BaseElevatorInformationMapper baseElevatorInformationMapper;

    /**
     * 查询电梯信息
     * 
     * @param id 电梯信息ID
     * @return 电梯信息
     */
    @Override
    public BaseElevatorInformation selectBaseElevatorInformationById(String id)
    {
        return baseElevatorInformationMapper.selectBaseElevatorInformationById(id);
    }

    /**
     * 查询电梯信息列表
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 电梯信息
     */
    @Override
    public List<BaseElevatorInformation> selectBaseElevatorInformationList(BaseElevatorInformation baseElevatorInformation)
    {
        return baseElevatorInformationMapper.selectBaseElevatorInformationList(baseElevatorInformation);
    }

    /**
     * 新增电梯信息
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 结果
     */
    @Override
    public int insertBaseElevatorInformation(BaseElevatorInformation baseElevatorInformation)
    {
        baseElevatorInformation.setId(baseElevatorInformationMapper.selectUuid());
        baseElevatorInformation.setCreateBy(SecurityUtils.getUsername());
        baseElevatorInformation.setCreateTime(DateUtils.getNowDate());
        baseElevatorInformation.setDelFlag("0");
        return baseElevatorInformationMapper.insertBaseElevatorInformation(baseElevatorInformation);
    }

    /**
     * 修改电梯信息
     * 
     * @param baseElevatorInformation 电梯信息
     * @return 结果
     */
    @Override
    public int updateBaseElevatorInformation(BaseElevatorInformation baseElevatorInformation)
    {
        baseElevatorInformation.setUpdateBy(SecurityUtils.getUsername());
        baseElevatorInformation.setUpdateTime(DateUtils.getNowDate());
        return baseElevatorInformationMapper.updateBaseElevatorInformation(baseElevatorInformation);
    }

    /**
     * 批量删除电梯信息
     * 
     * @param ids 需要删除的电梯信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseElevatorInformationByIds(String[] ids)
    {
        return baseElevatorInformationMapper.deleteBaseElevatorInformationByIds(ids);
    }

    /**
     * 批量软删除电梯信息
     *
     * @param ids 需要删除的电梯信息ID
     * @return 结果
     */
    @Override
    public int updateBaseElevatorInformationByIds(String[] ids)
    {
        return baseElevatorInformationMapper.updateBaseElevatorInformationByIds(ids);
    }

    /**
     * 删除电梯信息信息
     * 
     * @param id 电梯信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseElevatorInformationById(String id)
    {
        return baseElevatorInformationMapper.deleteBaseElevatorInformationById(id);
    }
}
