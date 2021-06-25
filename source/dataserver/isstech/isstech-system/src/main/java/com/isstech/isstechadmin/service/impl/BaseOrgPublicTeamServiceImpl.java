package com.isstech.isstechadmin.service.impl;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseOrgPublicOrgPublicTeam;
import com.isstech.isstechadmin.domain.BaseOrgPublicTeam;
import com.isstech.isstechadmin.domain.vo.BaseOrgPublicTeamVo;
import com.isstech.isstechadmin.mapper.BaseOrgPublicOrgPublicTeamMapper;
import com.isstech.isstechadmin.mapper.BaseOrgPublicTeamMapper;
import com.isstech.isstechadmin.service.IBaseOrgPublicTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 群防群治队伍Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Service
public class BaseOrgPublicTeamServiceImpl implements IBaseOrgPublicTeamService 
{
    @Autowired
    private BaseOrgPublicTeamMapper baseOrgPublicTeamMapper;
    @Autowired
    private BaseOrgPublicOrgPublicTeamMapper baseOrgPublicOrgPublicTeamMapper;

    /**
     * 查询群防群治队伍
     * 
     * @param id 群防群治队伍ID
     * @return 群防群治队伍
     */
    @Override
    public BaseOrgPublicTeam selectBaseOrgPublicTeamById(String id)
    {
        return baseOrgPublicTeamMapper.selectBaseOrgPublicTeamById(id);
    }

    /**
     * 查询群防群治队伍列表
     * 
     * @param baseOrgPublicTeam 群防群治队伍
     * @return 群防群治队伍
     */
    @Override
    public List<BaseOrgPublicTeam> selectBaseOrgPublicTeamList(BaseOrgPublicTeam baseOrgPublicTeam)
    {
        return baseOrgPublicTeamMapper.selectBaseOrgPublicTeamList(baseOrgPublicTeam);
    }

    /**
     * 新增群防群治队伍
     * 
     * @param baseOrgPublicTeamVo 群防群治队伍
     * @return 结果
     */
    @Override
    public int insertBaseOrgPublicTeam(BaseOrgPublicTeamVo baseOrgPublicTeamVo)
    {
        String id = baseOrgPublicTeamMapper.selectUuid();
        BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam = new BaseOrgPublicOrgPublicTeam();
        baseOrgPublicOrgPublicTeam.setPublicId(baseOrgPublicTeamVo.getOrgId());
        baseOrgPublicOrgPublicTeam.setPublicteamId(id);
        baseOrgPublicOrgPublicTeamMapper.insertBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam);
        baseOrgPublicTeamVo.setId(id);
        baseOrgPublicTeamVo.setCreateBy(SecurityUtils.getUsername());
        baseOrgPublicTeamVo.setCreateTime(DateUtils.getNowDate());
        baseOrgPublicTeamVo.setDelFlag("0");
        return baseOrgPublicTeamMapper.insertBaseOrgPublicTeam(baseOrgPublicTeamVo);
    }

    /**
     * 修改群防群治队伍
     * 
     * @param baseOrgPublicTeamVo 群防群治队伍
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublicTeam(BaseOrgPublicTeamVo baseOrgPublicTeamVo)
    {
        String publicOrgId = baseOrgPublicTeamVo.getOrgId();
        String id = baseOrgPublicTeamVo.getId();
        String resId = baseOrgPublicOrgPublicTeamMapper.queryByTeamId(id);
        if(StringUtils.isNotEmpty(resId)){
            baseOrgPublicOrgPublicTeamMapper.deleteBaseOrgPublicOrgPublicTeamById(resId);
        }

        BaseOrgPublicOrgPublicTeam baseOrgPublicOrgPublicTeam = new BaseOrgPublicOrgPublicTeam();
        baseOrgPublicOrgPublicTeam.setPublicId(publicOrgId);
        baseOrgPublicOrgPublicTeam.setPublicteamId(id);
        baseOrgPublicOrgPublicTeamMapper.insertBaseOrgPublicOrgPublicTeam(baseOrgPublicOrgPublicTeam);

        baseOrgPublicTeamVo.setUpdateTime(DateUtils.getNowDate());
        baseOrgPublicTeamVo.setUpdateBy(SecurityUtils.getUsername());
        return baseOrgPublicTeamMapper.updateBaseOrgPublicTeam(baseOrgPublicTeamVo);
    }

    /**
     * 批量删除群防群治队伍
     * 
     * @param ids 需要删除的群防群治队伍ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicTeamByIds(String[] ids)
    {
        baseOrgPublicOrgPublicTeamMapper.deleteBaseOrgPublicOrgPublicTeamByTeamIds(ids);
        return baseOrgPublicTeamMapper.deleteBaseOrgPublicTeamByIds(ids);
    }

    /**
     * 批量软删除群防群治队伍
     *
     * @param ids 需要删除的群防群治队伍ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgPublicTeamByIds(String[] ids)
    {
        baseOrgPublicOrgPublicTeamMapper.deleteBaseOrgPublicOrgPublicTeamByTeamIds(ids);
        return baseOrgPublicTeamMapper.updateBaseOrgPublicTeamByIds(ids);
    }

    /**
     * 删除群防群治队伍信息
     * 
     * @param id 群防群治队伍ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgPublicTeamById(String id)
    {
        return baseOrgPublicTeamMapper.deleteBaseOrgPublicTeamById(id);
    }
}
