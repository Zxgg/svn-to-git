package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BasePropertyManagementFeeIncomeMapper;
import com.isstech.isstechadmin.domain.BasePropertyManagementFeeIncome;
import com.isstech.isstechadmin.service.IBasePropertyManagementFeeIncomeService;

/**
 * 物业费用收入记录 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BasePropertyManagementFeeIncomeServiceImpl implements IBasePropertyManagementFeeIncomeService 
{
    @Autowired
    private BasePropertyManagementFeeIncomeMapper basePropertyManagementFeeIncomeMapper;

    /**
     * 查询物业费用收入记录 
     * 
     * @param id 物业费用收入记录 ID
     * @return 物业费用收入记录 
     */
    @Override
    public BasePropertyManagementFeeIncome selectBasePropertyManagementFeeIncomeById(String id)
    {
        return basePropertyManagementFeeIncomeMapper.selectBasePropertyManagementFeeIncomeById(id);
    }

    /**
     * 查询物业费用收入记录 列表
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 物业费用收入记录 
     */
    @Override
    public List<BasePropertyManagementFeeIncome> selectBasePropertyManagementFeeIncomeList(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome)
    {
        return basePropertyManagementFeeIncomeMapper.selectBasePropertyManagementFeeIncomeList(basePropertyManagementFeeIncome);
    }

    /**
     * 新增物业费用收入记录 
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 结果
     */
    @Override
    public int insertBasePropertyManagementFeeIncome(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome)
    {
        basePropertyManagementFeeIncome.setId(basePropertyManagementFeeIncomeMapper.selectUuid());
        basePropertyManagementFeeIncome.setCreateBy(SecurityUtils.getUsername());
        basePropertyManagementFeeIncome.setCreateTime(DateUtils.getNowDate());
        basePropertyManagementFeeIncome.setDelFlag("0");
        return basePropertyManagementFeeIncomeMapper.insertBasePropertyManagementFeeIncome(basePropertyManagementFeeIncome);
    }

    /**
     * 修改物业费用收入记录 
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 结果
     */
    @Override
    public int updateBasePropertyManagementFeeIncome(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome)
    {
        basePropertyManagementFeeIncome.setUpdateBy(SecurityUtils.getUsername());
        basePropertyManagementFeeIncome.setUpdateTime(DateUtils.getNowDate());
        return basePropertyManagementFeeIncomeMapper.updateBasePropertyManagementFeeIncome(basePropertyManagementFeeIncome);
    }

    /**
     * 批量删除物业费用收入记录 
     * 
     * @param ids 需要删除的物业费用收入记录 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyManagementFeeIncomeByIds(String[] ids)
    {
        return basePropertyManagementFeeIncomeMapper.deleteBasePropertyManagementFeeIncomeByIds(ids);
    }

    /**
     * 批量软删除物业费用收入记录 
     *
     * @param ids 需要删除的物业费用收入记录 ID
     * @return 结果
     */
    @Override
    public int updateBasePropertyManagementFeeIncomeByIds(String[] ids)
    {
        return basePropertyManagementFeeIncomeMapper.updateBasePropertyManagementFeeIncomeByIds(ids);
    }

    /**
     * 删除物业费用收入记录 信息
     * 
     * @param id 物业费用收入记录 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyManagementFeeIncomeById(String id)
    {
        return basePropertyManagementFeeIncomeMapper.deleteBasePropertyManagementFeeIncomeById(id);
    }
}
