package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseVoteProjectMapper;
import com.isstech.isstechadmin.domain.BaseVoteProject;
import com.isstech.isstechadmin.service.IBaseVoteProjectService;

/**
 * 投票项目信息 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseVoteProjectServiceImpl implements IBaseVoteProjectService 
{
    @Autowired
    private BaseVoteProjectMapper baseVoteProjectMapper;

    /**
     * 查询投票项目信息 
     * 
     * @param id 投票项目信息 ID
     * @return 投票项目信息 
     */
    @Override
    public BaseVoteProject selectBaseVoteProjectById(String id)
    {
        return baseVoteProjectMapper.selectBaseVoteProjectById(id);
    }

    /**
     * 查询投票项目信息 列表
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 投票项目信息 
     */
    @Override
    public List<BaseVoteProject> selectBaseVoteProjectList(BaseVoteProject baseVoteProject)
    {
        return baseVoteProjectMapper.selectBaseVoteProjectList(baseVoteProject);
    }

    /**
     * 新增投票项目信息 
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 结果
     */
    @Override
    public int insertBaseVoteProject(BaseVoteProject baseVoteProject)
    {
        baseVoteProject.setCreateTime(DateUtils.getNowDate());
        baseVoteProject.setCreateBy(SecurityUtils.getUsername());
        return baseVoteProjectMapper.insertBaseVoteProject(baseVoteProject);
    }

    /**
     * 修改投票项目信息 
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 结果
     */
    @Override
    public int updateBaseVoteProject(BaseVoteProject baseVoteProject)
    {
        baseVoteProject.setUpdateTime(DateUtils.getNowDate());
        baseVoteProject.setUpdateBy(SecurityUtils.getUsername());
        return baseVoteProjectMapper.updateBaseVoteProject(baseVoteProject);
    }

    /**
     * 批量删除投票项目信息 
     * 
     * @param ids 需要删除的投票项目信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteProjectByIds(String[] ids)
    {
        return baseVoteProjectMapper.deleteBaseVoteProjectByIds(ids);
    }
    /**
     * 批量软删除投票项目信息
     *
     * @param ids 需要删除的投票项目信息 ID
     * @return 结果
     */
    @Override
    public int updateBaseVoteProjectByIds(String[] ids)
    {
        return baseVoteProjectMapper.updateBaseVoteProjectByIds(ids);
    }

    /**
     * 删除投票项目信息 信息
     * 
     * @param id 投票项目信息 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteProjectById(String id)
    {
        return baseVoteProjectMapper.deleteBaseVoteProjectById(id);
    }
}
