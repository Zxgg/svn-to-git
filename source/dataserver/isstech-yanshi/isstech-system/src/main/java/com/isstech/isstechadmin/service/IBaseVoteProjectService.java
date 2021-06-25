package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVoteProject;

/**
 * 投票项目信息 Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseVoteProjectService 
{
    /**
     * 查询投票项目信息 
     * 
     * @param id 投票项目信息 ID
     * @return 投票项目信息 
     */
    public BaseVoteProject selectBaseVoteProjectById(String id);

    /**
     * 查询投票项目信息 列表
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 投票项目信息 集合
     */
    public List<BaseVoteProject> selectBaseVoteProjectList(BaseVoteProject baseVoteProject);

    /**
     * 新增投票项目信息 
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 结果
     */
    public int insertBaseVoteProject(BaseVoteProject baseVoteProject);

    /**
     * 修改投票项目信息 
     * 
     * @param baseVoteProject 投票项目信息 
     * @return 结果
     */
    public int updateBaseVoteProject(BaseVoteProject baseVoteProject);

    /**
     * 批量删除投票项目信息 
     * 
     * @param ids 需要删除的投票项目信息 ID
     * @return 结果
     */
    public int deleteBaseVoteProjectByIds(String[] ids);
    /**
     * 批量软删除投票项目信息
     *
     * @param ids 需要删除的投票项目信息 ID
     * @return 结果
     */
    public int updateBaseVoteProjectByIds(String[] ids);

    /**
     * 删除投票项目信息 信息
     * 
     * @param id 投票项目信息 ID
     * @return 结果
     */
    public int deleteBaseVoteProjectById(String id);
}
