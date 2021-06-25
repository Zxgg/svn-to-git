package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseOrgBasicOrgBasicTeamMapper;
import com.isstech.isstechadmin.domain.BaseOrgBasicOrgBasicTeam;
import com.isstech.isstechadmin.service.IBaseOrgBasicOrgBasicTeamService;

/**
 * 综治机构和综治队伍关联Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Service
public class BaseOrgBasicOrgBasicTeamServiceImpl implements IBaseOrgBasicOrgBasicTeamService 
{
    @Autowired
    private BaseOrgBasicOrgBasicTeamMapper baseOrgBasicOrgBasicTeamMapper;

    /**
     * 查询综治机构和综治队伍关联
     * 
     * @param id 综治机构和综治队伍关联ID
     * @return 综治机构和综治队伍关联
     */
    @Override
    public BaseOrgBasicOrgBasicTeam selectBaseOrgBasicOrgBasicTeamById(String id)
    {
        return baseOrgBasicOrgBasicTeamMapper.selectBaseOrgBasicOrgBasicTeamById(id);
    }

    /**
     * 查询综治机构和综治队伍关联列表
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 综治机构和综治队伍关联
     */
    @Override
    public List<BaseOrgBasicOrgBasicTeam> selectBaseOrgBasicOrgBasicTeamList(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        return baseOrgBasicOrgBasicTeamMapper.selectBaseOrgBasicOrgBasicTeamList(baseOrgBasicOrgBasicTeam);
    }

    /**
     * 新增综治机构和综治队伍关联
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 结果
     */
    @Override
    public int insertBaseOrgBasicOrgBasicTeam(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        baseOrgBasicOrgBasicTeam.setCreateTime(DateUtils.getNowDate());
        baseOrgBasicOrgBasicTeam.setDelFlag("0");
        baseOrgBasicOrgBasicTeam.setCreateBy(SecurityUtils.getUsername());
        return baseOrgBasicOrgBasicTeamMapper.insertBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam);
    }

    /**
     * 修改综治机构和综治队伍关联
     * 
     * @param baseOrgBasicOrgBasicTeam 综治机构和综治队伍关联
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasicOrgBasicTeam(BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam)
    {
        baseOrgBasicOrgBasicTeam.setUpdateBy(SecurityUtils.getUsername());
        baseOrgBasicOrgBasicTeam.setUpdateTime(DateUtils.getNowDate());
        return baseOrgBasicOrgBasicTeamMapper.updateBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam);
    }

    /**
     * 批量删除综治机构和综治队伍关联
     * 
     * @param ids 需要删除的综治机构和综治队伍关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicOrgBasicTeamByIds(String[] ids)
    {
        return baseOrgBasicOrgBasicTeamMapper.deleteBaseOrgBasicOrgBasicTeamByIds(ids);
    }

    /**
     * 批量软删除综治机构和综治队伍关联
     *
     * @param ids 需要删除的综治机构和综治队伍关联ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasicOrgBasicTeamByIds(String[] ids)
    {
        return baseOrgBasicOrgBasicTeamMapper.updateBaseOrgBasicOrgBasicTeamByIds(ids);
    }

    /**
     * 删除综治机构和综治队伍关联信息
     * 
     * @param id 综治机构和综治队伍关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicOrgBasicTeamById(String id)
    {
        return baseOrgBasicOrgBasicTeamMapper.deleteBaseOrgBasicOrgBasicTeamById(id);
    }
}
