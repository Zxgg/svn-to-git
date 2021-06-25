package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseTempVisitorPersonnelInformation;
import com.isstech.isstechadmin.mapper.BaseTempVisitorPersonnelInformationMapper;
import com.isstech.isstechadmin.service.IBaseTempVisitorPersonnelInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 临时访客人员信息Service业务层处理
 * 
 * @author isstech
 * @date 2020-11-23
 */
@Service
public class BaseTempVisitorPersonnelInformationServiceImpl implements IBaseTempVisitorPersonnelInformationService
{
    @Autowired
    private BaseTempVisitorPersonnelInformationMapper baseTempVisitorPersonnelInformationMapper;

    /**
     * 查询临时访客人员信息
     * 
     * @param id 临时访客人员信息ID
     * @return 临时访客人员信息
     */
    @Override
    public BaseTempVisitorPersonnelInformation selectBaseTempVisitorPersonnelInformationById(String id)
    {
        return baseTempVisitorPersonnelInformationMapper.selectBaseTempVisitorPersonnelInformationById(id);
    }

    /**
     * 查询临时访客人员信息列表
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 临时访客人员信息
     */
    @Override
    public List<BaseTempVisitorPersonnelInformation> selectBaseTempVisitorPersonnelInformationList(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        return baseTempVisitorPersonnelInformationMapper.selectBaseTempVisitorPersonnelInformationList(baseTempVisitorPersonnelInformation);
    }

    /**
     * 新增临时访客人员信息
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 结果
     */
    @Override
    public int insertBaseTempVisitorPersonnelInformation(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        baseTempVisitorPersonnelInformation.setCreateTime(DateUtils.getNowDate());
        baseTempVisitorPersonnelInformation.setCreateBy(SecurityUtils.getUsername());
        return baseTempVisitorPersonnelInformationMapper.insertBaseTempVisitorPersonnelInformation(baseTempVisitorPersonnelInformation);
    }

    /**
     * 修改临时访客人员信息
     * 
     * @param baseTempVisitorPersonnelInformation 临时访客人员信息
     * @return 结果
     */
    @Override
    public int updateBaseTempVisitorPersonnelInformation(BaseTempVisitorPersonnelInformation baseTempVisitorPersonnelInformation)
    {
        baseTempVisitorPersonnelInformation.setUpdateTime(DateUtils.getNowDate());
        baseTempVisitorPersonnelInformation.setUpdateBy(SecurityUtils.getUsername());
        return baseTempVisitorPersonnelInformationMapper.updateBaseTempVisitorPersonnelInformation(baseTempVisitorPersonnelInformation);
    }

    /**
     * 批量删除临时访客人员信息
     * 
     * @param ids 需要删除的临时访客人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseTempVisitorPersonnelInformationByIds(String[] ids)
    {
        return baseTempVisitorPersonnelInformationMapper.deleteBaseTempVisitorPersonnelInformationByIds(ids);
    }
    /**
     * 批量软删除临时访客人员信息
     *
     * @param ids 需要删除的临时访客人员信息ID
     * @return 结果
     */
    @Override
    public int updateBaseTempVisitorPersonnelInformationByIds(String[] ids)
    {
        return baseTempVisitorPersonnelInformationMapper.updateBaseTempVisitorPersonnelInformationByIds(ids);
    }

    /**
     * 删除临时访客人员信息信息
     * 
     * @param id 临时访客人员信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseTempVisitorPersonnelInformationById(String id)
    {
        return baseTempVisitorPersonnelInformationMapper.deleteBaseTempVisitorPersonnelInformationById(id);
    }

    @Override
    public String selectPersonIdByIdentificationNumber(String credentialNumber) {
        return baseTempVisitorPersonnelInformationMapper.selectPersonIdByIdentificationNumber(credentialNumber);
    }
}
