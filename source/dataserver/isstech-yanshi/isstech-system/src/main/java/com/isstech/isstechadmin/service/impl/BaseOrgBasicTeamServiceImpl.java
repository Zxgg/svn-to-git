package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseOrgBasicOrgBasicTeam;
import com.isstech.isstechadmin.domain.vo.BaseOrgBasicTeamVo;
import com.isstech.isstechadmin.mapper.BaseOrgBasicOrgBasicTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseOrgBasicTeamMapper;
import com.isstech.isstechadmin.domain.BaseOrgBasicTeam;
import com.isstech.isstechadmin.service.IBaseOrgBasicTeamService;

/**
 * 综治队伍Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-29
 */
@Service
public class BaseOrgBasicTeamServiceImpl implements IBaseOrgBasicTeamService 
{
    @Autowired
    private BaseOrgBasicTeamMapper baseOrgBasicTeamMapper;
    @Autowired
    private BaseOrgBasicOrgBasicTeamMapper baseOrgBasicOrgBasicTeamMapper;

    /**
     * 查询综治队伍
     * 
     * @param id 综治队伍ID
     * @return 综治队伍
     */
    @Override
    public BaseOrgBasicTeamVo selectBaseOrgBasicTeamById(String id)
    {
        return baseOrgBasicTeamMapper.selectBaseOrgBasicTeamById(id);
    }

    /**
     * 查询综治队伍列表
     * 
     * @param baseOrgBasicTeamVo 综治队伍(综合查询)
     * @return 综治队伍
     */
    @Override
    public List<BaseOrgBasicTeamVo> selectBaseOrgBasicTeamVoList(BaseOrgBasicTeamVo baseOrgBasicTeamVo)
    {
        return baseOrgBasicTeamMapper.selectBaseOrgBasicTeamVoList(baseOrgBasicTeamVo);
    }

    /**
     * 新增综治队伍
     * 
     * @param baseOrgBasicTeamVo 综治队伍
     * @return 结果
     */
    @Override
    public int insertBaseOrgBasicTeam(BaseOrgBasicTeamVo baseOrgBasicTeamVo)
    {
        String id = baseOrgBasicTeamMapper.selectUuid();
        BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam = new BaseOrgBasicOrgBasicTeam();
        baseOrgBasicOrgBasicTeam.setBasicId(baseOrgBasicTeamVo.getOrgId());
        baseOrgBasicOrgBasicTeam.setTeamId(id);
        baseOrgBasicOrgBasicTeamMapper.insertBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam);
        baseOrgBasicTeamVo.setId(id);
        baseOrgBasicTeamVo.setCreateBy(SecurityUtils.getUsername());
        baseOrgBasicTeamVo.setCreateTime(DateUtils.getNowDate());
        baseOrgBasicTeamVo.setDelFlag("0");
        return baseOrgBasicTeamMapper.insertBaseOrgBasicTeam(baseOrgBasicTeamVo);
    }

    /**
     * 修改综治队伍
     * @param baseOrgBasicTeamVo 综治队伍
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasicTeam(BaseOrgBasicTeamVo baseOrgBasicTeamVo)
    {
        String basicId = baseOrgBasicTeamVo.getOrgId();
        String id = baseOrgBasicTeamVo.getId();
        String resTeamId = baseOrgBasicOrgBasicTeamMapper.queryByTeamId(id);
        if(StringUtils.isNotEmpty(resTeamId)){
            baseOrgBasicOrgBasicTeamMapper.deleteBaseOrgBasicOrgBasicTeamById(resTeamId);
        }

        BaseOrgBasicOrgBasicTeam baseOrgBasicOrgBasicTeam = new BaseOrgBasicOrgBasicTeam();
        baseOrgBasicOrgBasicTeam.setBasicId(basicId);
        baseOrgBasicOrgBasicTeam.setTeamId(id);
        baseOrgBasicOrgBasicTeamMapper.insertBaseOrgBasicOrgBasicTeam(baseOrgBasicOrgBasicTeam);

        baseOrgBasicTeamVo.setUpdateBy(SecurityUtils.getUsername());
        baseOrgBasicTeamVo.setUpdateTime(DateUtils.getNowDate());
        return baseOrgBasicTeamMapper.updateBaseOrgBasicTeam(baseOrgBasicTeamVo);
    }

    /**
     * 批量删除综治队伍
     * 
     * @param ids 需要删除的综治队伍ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicTeamByIds(String[] ids)
    {
        baseOrgBasicOrgBasicTeamMapper.deleteBaseOrgBasicOrgBasicTeamByTeamIds(ids);
        return baseOrgBasicTeamMapper.deleteBaseOrgBasicTeamByIds(ids);
    }

    /**
     * 批量软删除综治队伍
     *
     * @param ids 需要删除的综治队伍ID
     * @return 结果
     */
    @Override
    public int updateBaseOrgBasicTeamByIds(String[] ids)
    {
        baseOrgBasicOrgBasicTeamMapper.deleteBaseOrgBasicOrgBasicTeamByTeamIds(ids);
        return baseOrgBasicTeamMapper.updateBaseOrgBasicTeamByIds(ids);
    }

    /**
     * 删除综治队伍信息
     * 
     * @param id 综治队伍ID
     * @return 结果
     */
    @Override
    public int deleteBaseOrgBasicTeamById(String id)
    {
        return baseOrgBasicTeamMapper.deleteBaseOrgBasicTeamById(id);
    }
}
