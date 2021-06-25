package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpecialDrugAddicts;

/**
 * 吸毒人员信息 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface TSpecialDrugAddictsMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

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
     * 删除吸毒人员信息 
     * 
     * @param id 吸毒人员信息 ID
     * @return 结果
     */
    public int deleteTSpecialDrugAddictsById(String id);

    /**
     * 批量删除吸毒人员信息 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTSpecialDrugAddictsByIds(String[] ids);

    /**
     * 批量软删除吸毒人员信息 
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateTSpecialDrugAddictsByIds(String[] ids);

    /**
     * 通过personId批量软删除
     * @param ids
     * @return 结果
     */
    public int updateTSpecialDrugAddictsByPersonIds(String[] ids);
}
