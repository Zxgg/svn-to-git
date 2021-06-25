package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BasePropertyCompanyEvaluationMapper;
import com.isstech.isstechadmin.domain.BasePropertyCompanyEvaluation;
import com.isstech.isstechadmin.service.IBasePropertyCompanyEvaluationService;

/**
 * 物业评价 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BasePropertyCompanyEvaluationServiceImpl implements IBasePropertyCompanyEvaluationService 
{
    @Autowired
    private BasePropertyCompanyEvaluationMapper basePropertyCompanyEvaluationMapper;

    /**
     * 查询物业评价 
     * 
     * @param id 物业评价 ID
     * @return 物业评价 
     */
    @Override
    public BasePropertyCompanyEvaluation selectBasePropertyCompanyEvaluationById(String id)
    {
        return basePropertyCompanyEvaluationMapper.selectBasePropertyCompanyEvaluationById(id);
    }

    /**
     * 查询物业评价 列表
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 物业评价 
     */
    @Override
    public List<BasePropertyCompanyEvaluation> selectBasePropertyCompanyEvaluationList(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        return basePropertyCompanyEvaluationMapper.selectBasePropertyCompanyEvaluationList(basePropertyCompanyEvaluation);
    }

    /**
     * 新增物业评价 
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 结果
     */
    @Override
    public int insertBasePropertyCompanyEvaluation(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        basePropertyCompanyEvaluation.setId(basePropertyCompanyEvaluationMapper.selectUuid());
        basePropertyCompanyEvaluation.setCreateBy(SecurityUtils.getUsername());
        basePropertyCompanyEvaluation.setCreateTime(DateUtils.getNowDate());
        basePropertyCompanyEvaluation.setDelFlag("0");
        return basePropertyCompanyEvaluationMapper.insertBasePropertyCompanyEvaluation(basePropertyCompanyEvaluation);
    }

    /**
     * 修改物业评价 
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 结果
     */
    @Override
    public int updateBasePropertyCompanyEvaluation(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation)
    {
        basePropertyCompanyEvaluation.setUpdateBy(SecurityUtils.getUsername());
        basePropertyCompanyEvaluation.setUpdateTime(DateUtils.getNowDate());
        return basePropertyCompanyEvaluationMapper.updateBasePropertyCompanyEvaluation(basePropertyCompanyEvaluation);
    }

    /**
     * 批量删除物业评价 
     * 
     * @param ids 需要删除的物业评价 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyCompanyEvaluationByIds(String[] ids)
    {
        return basePropertyCompanyEvaluationMapper.deleteBasePropertyCompanyEvaluationByIds(ids);
    }

    /**
     * 批量软删除物业评价 
     *
     * @param ids 需要删除的物业评价 ID
     * @return 结果
     */
    @Override
    public int updateBasePropertyCompanyEvaluationByIds(String[] ids)
    {
        return basePropertyCompanyEvaluationMapper.updateBasePropertyCompanyEvaluationByIds(ids);
    }

    /**
     * 删除物业评价 信息
     * 
     * @param id 物业评价 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyCompanyEvaluationById(String id)
    {
        return basePropertyCompanyEvaluationMapper.deleteBasePropertyCompanyEvaluationById(id);
    }
}
