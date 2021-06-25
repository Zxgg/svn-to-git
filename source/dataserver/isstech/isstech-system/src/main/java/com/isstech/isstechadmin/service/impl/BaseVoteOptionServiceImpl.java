package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseVoteOptionMapper;
import com.isstech.isstechadmin.domain.BaseVoteOption;
import com.isstech.isstechadmin.service.IBaseVoteOptionService;

/**
 * 投票项的选项 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseVoteOptionServiceImpl implements IBaseVoteOptionService 
{
    @Autowired
    private BaseVoteOptionMapper baseVoteOptionMapper;

    /**
     * 查询投票项的选项 
     * 
     * @param id 投票项的选项 ID
     * @return 投票项的选项 
     */
    @Override
    public BaseVoteOption selectBaseVoteOptionById(String id)
    {
        return baseVoteOptionMapper.selectBaseVoteOptionById(id);
    }

    /**
     * 查询投票项的选项 列表
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 投票项的选项 
     */
    @Override
    public List<BaseVoteOption> selectBaseVoteOptionList(BaseVoteOption baseVoteOption)
    {
        return baseVoteOptionMapper.selectBaseVoteOptionList(baseVoteOption);
    }

    /**
     * 新增投票项的选项 
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 结果
     */
    @Override
    public int insertBaseVoteOption(BaseVoteOption baseVoteOption)
    {
        baseVoteOption.setCreateTime(DateUtils.getNowDate());
        baseVoteOption.setCreateBy(SecurityUtils.getUsername());
        return baseVoteOptionMapper.insertBaseVoteOption(baseVoteOption);
    }

    /**
     * 修改投票项的选项 
     * 
     * @param baseVoteOption 投票项的选项 
     * @return 结果
     */
    @Override
    public int updateBaseVoteOption(BaseVoteOption baseVoteOption)
    {
        baseVoteOption.setUpdateTime(DateUtils.getNowDate());
        baseVoteOption.setUpdateBy(SecurityUtils.getUsername());
        return baseVoteOptionMapper.updateBaseVoteOption(baseVoteOption);
    }

    /**
     * 批量删除投票项的选项 
     * 
     * @param ids 需要删除的投票项的选项 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteOptionByIds(String[] ids)
    {
        return baseVoteOptionMapper.deleteBaseVoteOptionByIds(ids);
    }
    /**
     * 批量软删除投票项的选项
     *
     * @param ids 需要删除的投票项的选项 ID
     * @return 结果
     */
    @Override
    public int updateBaseVoteOptionByIds(String[] ids)
    {
        return baseVoteOptionMapper.updateBaseVoteOptionByIds(ids);
    }

    /**
     * 删除投票项的选项 信息
     * 
     * @param id 投票项的选项 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteOptionById(String id)
    {
        return baseVoteOptionMapper.deleteBaseVoteOptionById(id);
    }
}
