package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePropertyCompanyEvaluation;

/**
 * 物业评价 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface BasePropertyCompanyEvaluationMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询物业评价 
     * 
     * @param id 物业评价 ID
     * @return 物业评价 
     */
    public BasePropertyCompanyEvaluation selectBasePropertyCompanyEvaluationById(String id);

    /**
     * 查询物业评价 列表
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 物业评价 集合
     */
    public List<BasePropertyCompanyEvaluation> selectBasePropertyCompanyEvaluationList(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation);

    /**
     * 新增物业评价 
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 结果
     */
    public int insertBasePropertyCompanyEvaluation(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation);

    /**
     * 修改物业评价 
     * 
     * @param basePropertyCompanyEvaluation 物业评价 
     * @return 结果
     */
    public int updateBasePropertyCompanyEvaluation(BasePropertyCompanyEvaluation basePropertyCompanyEvaluation);

    /**
     * 删除物业评价 
     * 
     * @param id 物业评价 ID
     * @return 结果
     */
    public int deleteBasePropertyCompanyEvaluationById(String id);

    /**
     * 批量删除物业评价 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePropertyCompanyEvaluationByIds(String[] ids);

    /**
     * 批量软删除物业评价 
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePropertyCompanyEvaluationByIds(String[] ids);
}
