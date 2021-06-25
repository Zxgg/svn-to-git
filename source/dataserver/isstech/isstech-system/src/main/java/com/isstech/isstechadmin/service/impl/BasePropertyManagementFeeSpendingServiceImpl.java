package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BasePropertyManagementFeeSpendingMapper;
import com.isstech.isstechadmin.domain.BasePropertyManagementFeeSpending;
import com.isstech.isstechadmin.service.IBasePropertyManagementFeeSpendingService;

/**
 * 物业费用支出记录 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BasePropertyManagementFeeSpendingServiceImpl implements IBasePropertyManagementFeeSpendingService 
{
    @Autowired
    private BasePropertyManagementFeeSpendingMapper basePropertyManagementFeeSpendingMapper;

    /**
     * 查询物业费用支出记录 
     * 
     * @param id 物业费用支出记录 ID
     * @return 物业费用支出记录 
     */
    @Override
    public BasePropertyManagementFeeSpending selectBasePropertyManagementFeeSpendingById(String id)
    {
        return basePropertyManagementFeeSpendingMapper.selectBasePropertyManagementFeeSpendingById(id);
    }

    /**
     * 查询物业费用支出记录 列表
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 物业费用支出记录 
     */
    @Override
    public List<BasePropertyManagementFeeSpending> selectBasePropertyManagementFeeSpendingList(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        return basePropertyManagementFeeSpendingMapper.selectBasePropertyManagementFeeSpendingList(basePropertyManagementFeeSpending);
    }

    /**
     * 新增物业费用支出记录 
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 结果
     */
    @Override
    public int insertBasePropertyManagementFeeSpending(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        basePropertyManagementFeeSpending.setId(basePropertyManagementFeeSpendingMapper.selectUuid());
        basePropertyManagementFeeSpending.setCreateBy(SecurityUtils.getUsername());
        basePropertyManagementFeeSpending.setCreateTime(DateUtils.getNowDate());
        basePropertyManagementFeeSpending.setDelFlag("0");
        return basePropertyManagementFeeSpendingMapper.insertBasePropertyManagementFeeSpending(basePropertyManagementFeeSpending);
    }

    /**
     * 修改物业费用支出记录 
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 结果
     */
    @Override
    public int updateBasePropertyManagementFeeSpending(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending)
    {
        basePropertyManagementFeeSpending.setUpdateBy(SecurityUtils.getUsername());
        basePropertyManagementFeeSpending.setUpdateTime(DateUtils.getNowDate());
        return basePropertyManagementFeeSpendingMapper.updateBasePropertyManagementFeeSpending(basePropertyManagementFeeSpending);
    }

    /**
     * 批量删除物业费用支出记录 
     * 
     * @param ids 需要删除的物业费用支出记录 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyManagementFeeSpendingByIds(String[] ids)
    {
        return basePropertyManagementFeeSpendingMapper.deleteBasePropertyManagementFeeSpendingByIds(ids);
    }

    /**
     * 批量软删除物业费用支出记录 
     *
     * @param ids 需要删除的物业费用支出记录 ID
     * @return 结果
     */
    @Override
    public int updateBasePropertyManagementFeeSpendingByIds(String[] ids)
    {
        return basePropertyManagementFeeSpendingMapper.updateBasePropertyManagementFeeSpendingByIds(ids);
    }

    /**
     * 删除物业费用支出记录 信息
     * 
     * @param id 物业费用支出记录 ID
     * @return 结果
     */
    @Override
    public int deleteBasePropertyManagementFeeSpendingById(String id)
    {
        return basePropertyManagementFeeSpendingMapper.deleteBasePropertyManagementFeeSpendingById(id);
    }
}
