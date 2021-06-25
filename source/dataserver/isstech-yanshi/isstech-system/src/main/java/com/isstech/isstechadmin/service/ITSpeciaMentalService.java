package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TSpeciaMental;

/**
 * 易肇事肇祸精神病人 Service接口
 * 
 * @author yzp
 * @date 2021-02-22
 */
public interface ITSpeciaMentalService 
{
    /**
     * 查询易肇事肇祸精神病人 
     * 
     * @param id 易肇事肇祸精神病人 ID
     * @return 易肇事肇祸精神病人 
     */
    public TSpeciaMental selectTSpeciaMentalById(String id);

    /**
     * 查询易肇事肇祸精神病人 列表
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 易肇事肇祸精神病人 集合
     */
    public List<TSpeciaMental> selectTSpeciaMentalList(TSpeciaMental tSpeciaMental);

    /**
     * 新增易肇事肇祸精神病人 
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 结果
     */
    public int insertTSpeciaMental(TSpeciaMental tSpeciaMental);

    /**
     * 修改易肇事肇祸精神病人 
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 结果
     */
    public int updateTSpeciaMental(TSpeciaMental tSpeciaMental);

    /**
     * 批量删除易肇事肇祸精神病人 
     * 
     * @param ids 需要删除的易肇事肇祸精神病人 ID
     * @return 结果
     */
    public int deleteTSpeciaMentalByIds(String[] ids);

    /**
     * 删除易肇事肇祸精神病人 信息
     * 
     * @param id 易肇事肇祸精神病人 ID
     * @return 结果
     */
    public int deleteTSpeciaMentalById(String id);

    /**
     * 批量软删除易肇事肇祸精神病人 
     *
     * @param ids 需要删除的易肇事肇祸精神病人 ID
     * @return 结果
     */
    public int updateTSpeciaMentalByIds(String[] ids);
}
