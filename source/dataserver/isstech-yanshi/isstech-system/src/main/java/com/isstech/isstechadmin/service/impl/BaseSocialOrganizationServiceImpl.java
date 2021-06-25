package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.CommunitySocialOrganizationVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseSocialOrganizationMapper;
import com.isstech.isstechadmin.domain.BaseSocialOrganization;
import com.isstech.isstechadmin.service.IBaseSocialOrganizationService;

/**
 * 社会组织信息Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseSocialOrganizationServiceImpl implements IBaseSocialOrganizationService 
{
    @Autowired
    private BaseSocialOrganizationMapper baseSocialOrganizationMapper;
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private IBaseCommunityOrganizationService BaseCommunityOrganizationService;

    /**
     * 查询社会组织信息
     * 
     * @param id 社会组织信息ID
     * @return 社会组织信息
     */
    @Override
    public CommunitySocialOrganizationVo selectBaseSocialOrganizationById(String id)
    {
        return baseSocialOrganizationMapper.selectBaseSocialOrganizationById(id);
    }

    /**
     * 查询社会组织信息列表
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 社会组织信息
     */
    @Override
    public List<CommunitySocialOrganizationVo> selectBaseSocialOrganizationList(CommunitySocialOrganizationVo baseSocialOrganization)
    {
        return baseSocialOrganizationMapper.selectBaseSocialOrganizationList(baseSocialOrganization);
    }

    /**
     * 新增社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    @Override
    public int insertBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization, QueryVo vo)
    {
        baseSocialOrganization.setCreateTime(DateUtils.getNowDate());
        baseSocialOrganization.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityOrganizationMapper.selectUuid();
        baseSocialOrganization.setId(uuid);
        int row = baseSocialOrganizationMapper.insertBaseSocialOrganization(baseSocialOrganization);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,uuid,"04");
        return row;
    }

    /**
     * 修改社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    @Override
    public int updateBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization, QueryVo vo)
    {
        baseSocialOrganization.setUpdateTime(DateUtils.getNowDate());
        baseSocialOrganization.setUpdateBy(SecurityUtils.getUsername());
        int row = baseSocialOrganizationMapper.updateBaseSocialOrganization(baseSocialOrganization);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,baseSocialOrganization.getId(),"04");
        return row;
    }

    /**
     * 批量删除社会组织信息
     * 
     * @param ids 需要删除的社会组织信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseSocialOrganizationByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationByIds(ids);
        return baseSocialOrganizationMapper.deleteBaseSocialOrganizationByIds(ids);
    }
    /**
     * 批量软删除社会组织信息
     *
     * @param ids 需要删除的社会组织信息ID
     * @return 结果
     */
    @Override
    public int updateBaseSocialOrganizationByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseSocialOrganizationMapper.updateBaseSocialOrganizationByIds(ids);
    }

    /**
     * 删除社会组织信息信息
     * 
     * @param id 社会组织信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseSocialOrganizationById(String id)
    {
        return baseSocialOrganizationMapper.deleteBaseSocialOrganizationById(id);
    }
}
