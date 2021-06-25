package com.isstech.isstechadmin.service.impl;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseOrgBasic;
import com.isstech.isstechadmin.domain.vo.CommunityOrgBasicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.mapper.BaseOrgBasicMapper;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;
import com.isstech.isstechadmin.service.IBaseOrgBasicService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 综治机构管理Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseOrgBasicServiceImpl implements IBaseOrgBasicService 
{
    @Autowired
    private BaseOrgBasicMapper baseOrgBasicMapper;
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private IBaseCommunityOrganizationService BaseCommunityOrganizationService;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    /**
     * 查询综治机构管理
     * 
     * @param id 综治机构管理ID
     * @return 综治机构管理
     */
    @Override
    public CommunityOrgBasicVo selectBaseOrgBasicById(String id)
    {
        return baseOrgBasicMapper.selectBaseOrgBasicById(id);
    }

    /**
     * 查询综治机构管理列表
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 综治机构管理
     */
    @Override
    public List<CommunityOrgBasicVo> selectBaseOrgBasicList(CommunityOrgBasicVo baseOrgBasic)
    {
        baseOrgBasic.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseOrgBasic.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return baseOrgBasicMapper.selectBaseOrgBasicList(baseOrgBasic);
    }

    /**
     * 新增综治机构管理
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 结果
     */
    @Override
    public int insertBaseOrgBasic(BaseOrgBasic baseOrgBasic, QueryVo vo)
    {
        baseOrgBasic.setCreateTime(DateUtils.getNowDate());
        baseOrgBasic.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityOrganizationMapper.selectUuid();
        baseOrgBasic.setId(uuid);
        int row = baseOrgBasicMapper.insertBaseOrgBasic(baseOrgBasic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,uuid,"02");
        return row;
    }

    /**
     * 修改综治机构管理
     * 
     * @param baseOrgBasic 综治机构管理
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasic(BaseOrgBasic baseOrgBasic, QueryVo vo)
    {
        baseOrgBasic.setUpdateTime(DateUtils.getNowDate());
        baseOrgBasic.setUpdateBy(SecurityUtils.getUsername());
        int row = baseOrgBasicMapper.updateBaseOrgBasic(baseOrgBasic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,baseOrgBasic.getId(),"02");
        return row;
    }

    /**
     * 批量删除综治机构管理
     * 
     * @param ids 需要删除的综治机构管理ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationByIds(ids);
        return baseOrgBasicMapper.deleteBaseOrgBasicByIds(ids);
    }

    /**
     * 批量软删除综治机构管理
     *
     * @param ids 需要删除的综治机构管理ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseOrgBasicMapper.updateBaseOrgBasicByIds(ids);
    }

    /**
     * 删除综治机构管理信息
     * 
     * @param id 综治机构管理ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicById(String id)
    {
        return baseOrgBasicMapper.deleteBaseOrgBasicById(id);
    }
}
