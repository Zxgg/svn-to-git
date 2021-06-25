package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseOrgPublic;
import com.isstech.isstechadmin.domain.vo.CommunityOrgPublicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.mapper.BaseOrgPublicMapper;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;
import com.isstech.isstechadmin.service.IBaseOrgPublicService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 群防群治组织Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-27
 */
@Service
public class BaseOrgPublicServiceImpl implements IBaseOrgPublicService
{
    @Autowired
    private BaseOrgPublicMapper baseOrgPublicMapper;
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private IBaseCommunityOrganizationService BaseCommunityOrganizationService;

    /**
     * 查询群防群治组织
     * 
     * @param id 群防群治组织ID
     * @return 群防群治组织
     */
    @Override
    public CommunityOrgPublicVo selectBaseOrgPublicById(String id)
    {
        return baseOrgPublicMapper.selectBaseOrgPublicById(id);
    }

    /**
     * 查询群防群治组织列表
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 群防群治组织
     */
    @Override
    public List<CommunityOrgPublicVo> selectBaseOrgPublicList(CommunityOrgPublicVo baseOrgPublic)
    {
        return baseOrgPublicMapper.selectBaseOrgPublicList(baseOrgPublic);
    }

    /**
     * 新增群防群治组织
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 结果
     */
    @Override
    public int insertBaseOrgPublic(BaseOrgPublic baseOrgPublic, QueryVo vo)
    {
        baseOrgPublic.setCreateTime(DateUtils.getNowDate());
        baseOrgPublic.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityOrganizationMapper.selectUuid();
        baseOrgPublic.setId(uuid);
        int row = baseOrgPublicMapper.insertBaseOrgPublic(baseOrgPublic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,uuid,"03");
        return row;
    }

    /**
     * 修改群防群治组织
     * 
     * @param baseOrgPublic 群防群治组织
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublic(BaseOrgPublic baseOrgPublic, QueryVo vo)
    {
        baseOrgPublic.setUpdateTime(DateUtils.getNowDate());
        baseOrgPublic.setUpdateBy(SecurityUtils.getUsername());
        int row = baseOrgPublicMapper.updateBaseOrgPublic(baseOrgPublic);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,baseOrgPublic.getId(),"03");
        return row;
    }

    /**
     * 批量删除群防群治组织
     * 
     * @param ids 需要删除的群防群治组织ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.deleteBaseCommunityOrganizationByIds(ids);
        return baseOrgPublicMapper.deleteBaseOrgPublicByIds(ids);
    }
    /**
     * 批量软删除群防群治组织
     *
     * @param ids 需要删除的群防群治组织ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublicByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseOrgPublicMapper.updateBaseOrgPublicByIds(ids);
    }

    /**
     * 删除群防群治组织信息
     * 
     * @param id 群防群治组织ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicById(String id)
    {
        return baseOrgPublicMapper.deleteBaseOrgPublicById(id);
    }
}
