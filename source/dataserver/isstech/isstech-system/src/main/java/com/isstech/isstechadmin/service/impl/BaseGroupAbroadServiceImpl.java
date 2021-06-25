package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.vo.CommunityGroupAbroadVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;
import com.isstech.isstechadmin.mapper.BaseCommunityOrganizationMapper;
import com.isstech.isstechadmin.service.IBaseCommunityOrganizationService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseGroupAbroadMapper;
import com.isstech.isstechadmin.domain.BaseGroupAbroad;
import com.isstech.isstechadmin.service.IBaseGroupAbroadService;

/**
 * 境内外非政府组织Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseGroupAbroadServiceImpl implements IBaseGroupAbroadService 
{
    @Autowired
    private BaseGroupAbroadMapper baseGroupAbroadMapper;
    @Autowired
    private BaseCommunityOrganizationMapper baseCommunityOrganizationMapper;
    @Autowired
    private IBaseCommunityOrganizationService BaseCommunityOrganizationService;

    /**
     * 查询境内外非政府组织
     * 
     * @param id 境内外非政府组织ID
     * @return 境内外非政府组织
     */
    @Override
    public CommunityGroupAbroadVo selectBaseGroupAbroadById(String id)
    {
        return baseGroupAbroadMapper.selectBaseGroupAbroadById(id);
    }

    /**
     * 查询境内外非政府组织列表
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 境内外非政府组织
     */
    @Override
    public List<CommunityGroupAbroadVo> selectBaseGroupAbroadList(CommunityGroupAbroadVo baseGroupAbroad)
    {
        return baseGroupAbroadMapper.selectBaseGroupAbroadList(baseGroupAbroad);
    }

    /**
     * 新增境内外非政府组织
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 结果
     */
    @Override
    public int insertBaseGroupAbroad(BaseGroupAbroad baseGroupAbroad, QueryVo vo)
    {
        baseGroupAbroad.setCreateTime(DateUtils.getNowDate());
        baseGroupAbroad.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityOrganizationMapper.selectUuid();
        baseGroupAbroad.setId(uuid);
        int row = baseGroupAbroadMapper.insertBaseGroupAbroad(baseGroupAbroad);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,uuid,"05");
        return row;
    }

    /**
     * 修改境内外非政府组织
     * 
     * @param baseGroupAbroad 境内外非政府组织
     * @return 结果
     */
    @Override
    public int updateBaseGroupAbroad(BaseGroupAbroad baseGroupAbroad, QueryVo vo)
    {
        baseGroupAbroad.setUpdateTime(DateUtils.getNowDate());
        baseGroupAbroad.setUpdateBy(SecurityUtils.getUsername());
        int row = baseGroupAbroadMapper.updateBaseGroupAbroad(baseGroupAbroad);
        //判断并保存社区组织关联关系
        BaseCommunityOrganizationService.saveBaseCommunityOrganization(vo,baseGroupAbroad.getId(),"05");
        return row;
    }

    /**
     * 批量删除境内外非政府组织
     * 
     * @param ids 需要删除的境内外非政府组织ID
     * @return 结果
     */
    @Override
    public int deleteBaseGroupAbroadByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseGroupAbroadMapper.deleteBaseGroupAbroadByIds(ids);
    }

    /**
     * 批量软删除境内外非政府组织
     *
     * @param ids 需要删除的境内外非政府组织ID
     * @return 结果
     */
    @Override
    public int updateBaseGroupAbroadByIds(String[] ids)
    {
        baseCommunityOrganizationMapper.updateBaseCommunityOrganizationByIds(ids);
        return baseGroupAbroadMapper.updateBaseGroupAbroadByIds(ids);
    }

    /**
     * 删除境内外非政府组织信息
     * 
     * @param id 境内外非政府组织ID
     * @return 结果
     */
    @Override
    public int deleteBaseGroupAbroadById(String id)
    {
        return baseGroupAbroadMapper.deleteBaseGroupAbroadById(id);
    }
}
