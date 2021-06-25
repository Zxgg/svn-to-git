package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.CommunityGroupEconomicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseGroupEconomicMapper;
import com.isstech.isstechadmin.domain.BaseGroupEconomic;
import com.isstech.isstechadmin.service.IBaseGroupEconomicService;

/**
 * 非公经济组织信息Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseGroupEconomicServiceImpl implements IBaseGroupEconomicService 
{
    @Autowired
    private BaseGroupEconomicMapper baseGroupEconomicMapper;
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private IBaseCommunityOrganizationService BaseCommunityOrganizationService;

    /**
     * 查询非公经济组织信息
     * 
     * @param id 非公经济组织信息ID
     * @return 非公经济组织信息
     */
    @Override
    public CommunityGroupEconomicVo selectBaseGroupEconomicById(String id)
    {
        return baseGroupEconomicMapper.selectBaseGroupEconomicById(id);
    }

    /**
     * 查询非公经济组织信息列表
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 非公经济组织信息
     */
    @Override
    public List<CommunityGroupEconomicVo> selectBaseGroupEconomicList(CommunityGroupEconomicVo baseGroupEconomic)
    {
        return baseGroupEconomicMapper.selectBaseGroupEconomicList(baseGroupEconomic);
    }

    /**
     * 新增非公经济组织信息
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 结果
     */
    @Override
    public int insertBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic, QueryVo vo)
    {
        baseGroupEconomic.setCreateTime(DateUtils.getNowDate());
        baseGroupEconomic.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityOrganizationMapper.selectUuid();
        baseGroupEconomic.setId(uuid);
        int row = baseGroupEconomicMapper.insertBaseGroupEconomic(baseGroupEconomic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,uuid,"01");
        return row;
    }

    /**
     * 修改非公经济组织信息
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 结果
     */
    @Override
    public int updateBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic, QueryVo vo)
    {
        baseGroupEconomic.setUpdateTime(DateUtils.getNowDate());
        baseGroupEconomic.setUpdateBy(SecurityUtils.getUsername());
        int row = baseGroupEconomicMapper.updateBaseGroupEconomic(baseGroupEconomic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,baseGroupEconomic.getId(),"01");
        return row;
    }

    /**
     * 批量删除非公经济组织信息
     * 
     * @param ids 需要删除的非公经济组织信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseGroupEconomicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationByIds(ids);
        return baseGroupEconomicMapper.deleteBaseGroupEconomicByIds(ids);
    }

    /**
     * 批量软删除非公经济组织信息
     *
     * @param ids 需要删除的非公经济组织信息ID
     * @return 结果
     */
    @Override
    public int updateBaseGroupEconomicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseGroupEconomicMapper.updateBaseGroupEconomicByIds(ids);
    }

    /**
     * 删除非公经济组织信息信息
     * 
     * @param id 非公经济组织信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseGroupEconomicById(String id)
    {
        return baseGroupEconomicMapper.deleteBaseGroupEconomicById(id);
    }
}
