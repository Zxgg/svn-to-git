package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseVoteOption;

import java.util.List;

/**
 * 投票项的选项 Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseVoteOptionMapper 
{
    /**
     * 查询投票项的选项 
     * 
     * @param id 投票项的选项 ID
     * @return 投票项的选项 
     */
    public BaseVoteOption selectBaseVoteOptionById(String id);

    /**
     * 查询投票项的选项 列表
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 投票项的选项 集合
     */
    public List<BaseVoteOption> selectBaseVoteOptionList(BaseVoteOption baseVoteOption);

    /**
     * 新增投票项的选项 
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 结果
     */
    public int insertBaseVoteOption(BaseVoteOption baseVoteOption);

    /**
     * 修改投票项的选项 
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 结果
     */
    public int updateBaseVoteOption(BaseVoteOption baseVoteOption);

    /**
     * 删除投票项的选项 
     * 
     * @param id 投票项的选项 ID
     * @return 结果
     */
    public int deleteBaseVoteOptionById(String id);

    /**
     * 批量删除投票项的选项 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVoteOptionByIds(String[] ids);

    /**
     * 批量软删除投票项的选项
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVoteOptionByIds(String[] ids);
}
