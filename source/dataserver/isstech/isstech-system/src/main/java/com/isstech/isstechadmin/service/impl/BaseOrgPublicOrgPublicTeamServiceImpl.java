package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseOrgPublicOrgPublicTeamMapper;
import com.isstech.isstechadmin.domain.BaseOrgPublicOrgPublicTeam;
import com.isstech.isstechadmin.service.IBaseOrgPublicOrgPublicTeamService;

/**
 * 群防群治组织和队伍关联Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Service
public class BaseOrgPublicOrgPublicTeamServiceImpl implements IBaseOrgPublicOrgPublicTeamService 
{
    @Autowired
    private BaseOrgPublicOrgPublicTeamMapper baseOrgPublicOrgPublicTeamMapper;

    /**
     * 查询群防群治组织和队伍关联
     * 
     * @param id 群防群治组织和队伍关联ID
     * @return 群防群治组织和队伍关联
     */
    @Override
    public BaseOrgPublicOrgPublicTeam selectBaseOrgPublicOrgPublicTeamById(String id)
    {
        return baseOrgPublicOrgPublicTeamMapper.selectBaseOrgPublicOrgPublicTeamById(id);
    }

    /**
     * 查询群防群治组织和队伍关联列表
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 群防群治组织和队伍关联
     */
    @Override
    public List<BaseOrgPublicOrgPublicTeam> selectBaseOrgPublicOrgPublicTeamList(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        return baseOrgPublicOrgPublicTeamMapper.selectBaseOrgPublicOrgPublicTeamList(baseOrgPublicOrgPublicTeam);
    }

    /**
     * 新增群防群治组织和队伍关联
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 结果
     */
    @Override
    public int insertBaseOrgPublicOrgPublicTeam(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        baseOrgPublicOrgPublicTeam.setCreateTime(DateUtils.getNowDate());
        baseOrgPublicOrgPublicTeam.setDelFlag("0");
        baseOrgPublicOrgPublicTeam.setCreateBy(SecurityUtils.getUsername());
        return baseOrgPublicOrgPublicTeamMapper.insertBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam);
    }

    /**
     * 修改群防群治组织和队伍关联
     * 
     * @param baseOrgPublicOrgPublicTeam 群防群治组织和队伍关联
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublicOrgPublicTeam(BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam)
    {
        baseOrgPublicOrgPublicTeam.setUpdateBy(SecurityUtils.getUsername());
        baseOrgPublicOrgPublicTeam.setUpdateTime(DateUtils.getNowDate());
        return baseOrgPublicOrgPublicTeamMapper.updateBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam);
    }

    /**
     * 批量删除群防群治组织和队伍关联
     * 
     * @param ids 需要删除的群防群治组织和队伍关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicOrgPublicTeamByIds(String[] ids)
    {
        return baseOrgPublicOrgPublicTeamMapper.deleteBaseOrgPublicOrgPublicTeamByIds(ids);
    }

    /**
     * 批量软删除群防群治组织和队伍关联
     *
     * @param ids 需要删除的群防群治组织和队伍关联ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublicOrgPublicTeamByIds(String[] ids)
    {
        return baseOrgPublicOrgPublicTeamMapper.updateBaseOrgPublicOrgPublicTeamByIds(ids);
    }

    /**
     * 删除群防群治组织和队伍关联信息
     * 
     * @param id 群防群治组织和队伍关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicOrgPublicTeamById(String id)
    {
        return baseOrgPublicOrgPublicTeamMapper.deleteBaseOrgPublicOrgPublicTeamById(id);
    }
}
