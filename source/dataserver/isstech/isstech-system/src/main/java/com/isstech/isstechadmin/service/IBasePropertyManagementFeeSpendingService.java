package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePropertyManagementFeeSpending;

/**
 * 物业费用支出记录 Service接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface IBasePropertyManagementFeeSpendingService 
{
    /**
     * 查询物业费用支出记录 
     * 
     * @param id 物业费用支出记录 ID
     * @return 物业费用支出记录 
     */
    public BasePropertyManagementFeeSpending selectBasePropertyManagementFeeSpendingById(String id);

    /**
     * 查询物业费用支出记录 列表
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 物业费用支出记录 集合
     */
    public List<BasePropertyManagementFeeSpending> selectBasePropertyManagementFeeSpendingList(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending);

    /**
     * 新增物业费用支出记录 
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 结果
     */
    public int insertBasePropertyManagementFeeSpending(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending);

    /**
     * 修改物业费用支出记录 
     * 
     * @param basePropertyManagementFeeSpending 物业费用支出记录 
     * @return 结果
     */
    public int updateBasePropertyManagementFeeSpending(BasePropertyManagementFeeSpending basePropertyManagementFeeSpending);

    /**
     * 批量删除物业费用支出记录 
     * 
     * @param ids 需要删除的物业费用支出记录 ID
     * @return 结果
     */
    public int deleteBasePropertyManagementFeeSpendingByIds(String[] ids);

    /**
     * 删除物业费用支出记录 信息
     * 
     * @param id 物业费用支出记录 ID
     * @return 结果
     */
    public int deleteBasePropertyManagementFeeSpendingById(String id);

    /**
     * 批量软删除物业费用支出记录 
     *
     * @param ids 需要删除的物业费用支出记录 ID
     * @return 结果
     */
    public int updateBasePropertyManagementFeeSpendingByIds(String[] ids);
}
