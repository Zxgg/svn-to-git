package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePropertyManagementFeeIncome;

/**
 * 物业费用收入记录 Service接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface IBasePropertyManagementFeeIncomeService 
{
    /**
     * 查询物业费用收入记录 
     * 
     * @param id 物业费用收入记录 ID
     * @return 物业费用收入记录 
     */
    public BasePropertyManagementFeeIncome selectBasePropertyManagementFeeIncomeById(String id);

    /**
     * 查询物业费用收入记录 列表
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 物业费用收入记录 集合
     */
    public List<BasePropertyManagementFeeIncome> selectBasePropertyManagementFeeIncomeList(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome);

    /**
     * 新增物业费用收入记录 
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 结果
     */
    public int insertBasePropertyManagementFeeIncome(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome);

    /**
     * 修改物业费用收入记录 
     * 
     * @param basePropertyManagementFeeIncome 物业费用收入记录 
     * @return 结果
     */
    public int updateBasePropertyManagementFeeIncome(BasePropertyManagementFeeIncome basePropertyManagementFeeIncome);

    /**
     * 批量删除物业费用收入记录 
     * 
     * @param ids 需要删除的物业费用收入记录 ID
     * @return 结果
     */
    public int deleteBasePropertyManagementFeeIncomeByIds(String[] ids);

    /**
     * 删除物业费用收入记录 信息
     * 
     * @param id 物业费用收入记录 ID
     * @return 结果
     */
    public int deleteBasePropertyManagementFeeIncomeById(String id);

    /**
     * 批量软删除物业费用收入记录 
     *
     * @param ids 需要删除的物业费用收入记录 ID
     * @return 结果
     */
    public int updateBasePropertyManagementFeeIncomeByIds(String[] ids);
}
