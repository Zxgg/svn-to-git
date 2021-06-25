package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVoteOption;

/**
 * 投票项的选项 Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseVoteOptionService 
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
     * 批量删除投票项的选项 
     * 
     * @param ids 需要删除的投票项的选项 ID
     * @return 结果
     */
    public int deleteBaseVoteOptionByIds(String[] ids);
    /**
     * 批量软删除投票项的选项
     *
     * @param ids 需要删除的投票项的选项 ID
     * @return 结果
     */
    public int updateBaseVoteOptionByIds(String[] ids);

    /**
     * 删除投票项的选项 信息
     * 
     * @param id 投票项的选项 ID
     * @return 结果
     */
    public int deleteBaseVoteOptionById(String id);
}
