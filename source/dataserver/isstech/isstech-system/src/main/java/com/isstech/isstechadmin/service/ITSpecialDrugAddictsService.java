package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpecialDrugAddicts;

/**
 * 吸毒人员信息 Service接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface ITSpecialDrugAddictsService 
{
    /**
     * 查询吸毒人员信息 
     * 
     * @param id 吸毒人员信息 ID
     * @return 吸毒人员信息 
     */
    public TSpecialDrugAddicts selectTSpecialDrugAddictsById(String id);

    /**
     * 查询吸毒人员信息 列表
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 吸毒人员信息 集合
     */
    public List<TSpecialDrugAddicts> selectTSpecialDrugAddictsList(TSpecialDrugAddicts tSpecialDrugAddicts);

    /**
     * 新增吸毒人员信息 
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 结果
     */
    public int insertTSpecialDrugAddicts(TSpecialDrugAddicts tSpecialDrugAddicts);

    /**
     * 修改吸毒人员信息 
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 结果
     */
    public int updateTSpecialDrugAddicts(TSpecialDrugAddicts tSpecialDrugAddicts);

    /**
     * 批量删除吸毒人员信息 
     * 
     * @param ids 需要删除的吸毒人员信息 ID
     * @return 结果
     */
    public int deleteTSpecialDrugAddictsByIds(String[] ids);

    /**
     * 删除吸毒人员信息 信息
     * 
     * @param id 吸毒人员信息 ID
     * @return 结果
     */
    public int deleteTSpecialDrugAddictsById(String id);

    /**
     * 批量软删除吸毒人员信息 
     *
     * @param ids 需要删除的吸毒人员信息 ID
     * @return 结果
     */
    public int updateTSpecialDrugAddictsByIds(String[] ids);
}
