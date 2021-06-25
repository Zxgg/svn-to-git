package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseVisitorPersonnelInformation;
import com.isstech.isstechadmin.domain.vo.Community;
import com.isstech.isstechadmin.mapper.BaseVisitorPersonnelInformationMapper;
import com.isstech.isstechadmin.service.IBaseVisitorPersonnelInformationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 访客人员信息Service业务层处理
 * 
 * @author isstech
 * @date 2020-11-23
 */
@Service
public class BaseVisitorPersonnelInformationServiceImpl implements IBaseVisitorPersonnelInformationService
{
    @Autowired
    private BaseVisitorPersonnelInformationMapper baseVisitorPersonnelInformationMapper;

    /**
     * 查询访客人员信息
     * 
     * @param id 访客人员信息ID
     * @return 访客人员信息
     */
    @Override
    public BaseVisitorPersonnelInformation selectBaseVisitorPersonnelInformationById(String id)
    {
        return baseVisitorPersonnelInformationMapper.selectBaseVisitorPersonnelInformationById(id);
    }

    /**
     * 查询访客人员信息列表
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 访客人员信息
     */
    @Override
    public List<BaseVisitorPersonnelInformation> selectBaseVisitorPersonnelInformationList(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        return baseVisitorPersonnelInformationMapper.selectBaseVisitorPersonnelInformationList(baseVisitorPersonnelInformation);
    }

    /**
     * 新增访客人员信息
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 结果
     */
    @Override
    public int insertBaseVisitorPersonnelInformation(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        baseVisitorPersonnelInformation.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isEmpty(baseVisitorPersonnelInformation.getCreateBy())){
            baseVisitorPersonnelInformation.setCreateBy(SecurityUtils.getUsername());
        }
        return baseVisitorPersonnelInformationMapper.insertBaseVisitorPersonnelInformation(baseVisitorPersonnelInformation);
    }

    /**
     * 修改访客人员信息
     * 
     * @param baseVisitorPersonnelInformation 访客人员信息
     * @return 结果
     */
    @Override
    public int updateBaseVisitorPersonnelInformation(BaseVisitorPersonnelInformation baseVisitorPersonnelInformation)
    {
        baseVisitorPersonnelInformation.setUpdateTime(DateUtils.getNowDate());
        baseVisitorPersonnelInformation.setUpdateBy(SecurityUtils.getUsername());
        return baseVisitorPersonnelInformationMapper.updateBaseVisitorPersonnelInformation(baseVisitorPersonnelInformation);
    }

    /**
     * 批量删除访客人员信息
     * 
     * @param ids 需要删除的访客人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVisitorPersonnelInformationByIds(String[] ids)
    {
        return baseVisitorPersonnelInformationMapper.deleteBaseVisitorPersonnelInformationByIds(ids);
    }
    /**
     * 批量软删除访客人员信息
     *
     * @param ids 需要删除的访客人员信息ID
     * @return 结果
     */
    @Override
    public int updateBaseVisitorPersonnelInformationByIds(String[] ids)
    {
        return baseVisitorPersonnelInformationMapper.updateBaseVisitorPersonnelInformationByIds(ids);
    }

    /**
     * 删除访客人员信息信息
     * 
     * @param id 访客人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVisitorPersonnelInformationById(String id)
    {
        return baseVisitorPersonnelInformationMapper.deleteBaseVisitorPersonnelInformationById(id);
    }

    /**
     * 根据身份证号查询访客人员id
     * @param credentialNumber
     * @return
     */
    @Override
    public Community selectPersonIdByIdentificationNumber(String credentialNumber) {
        return baseVisitorPersonnelInformationMapper.selectPersonIdByIdentificationNumber(credentialNumber);
    }
}
