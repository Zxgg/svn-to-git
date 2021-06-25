package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TEpidemicIsolationFamily;

/**
 * 隔离家庭 Service接口
 * 
 * @author yzp
 * @date 2021-01-20
 */
public interface ITEpidemicIsolationFamilyService 
{
    /**
     * 查询隔离家庭 
     * 
     * @param id 隔离家庭 ID
     * @return 隔离家庭 
     */
    public TEpidemicIsolationFamily selectTEpidemicIsolationFamilyById(String id);

    /**
     * 查询隔离家庭 列表
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 隔离家庭 集合
     */
    public List<TEpidemicIsolationFamily> selectTEpidemicIsolationFamilyList(TEpidemicIsolationFamily tEpidemicIsolationFamily);

    /**
     * 新增隔离家庭 
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 结果
     */
    public int insertTEpidemicIsolationFamily(TEpidemicIsolationFamily tEpidemicIsolationFamily);

    /**
     * 修改隔离家庭 
     * 
     * @param tEpidemicIsolationFamily 隔离家庭 
     * @return 结果
     */
    public int updateTEpidemicIsolationFamily(TEpidemicIsolationFamily tEpidemicIsolationFamily);

    /**
     * 批量删除隔离家庭 
     * 
     * @param ids 需要删除的隔离家庭 ID
     * @return 结果
     */
    public int deleteTEpidemicIsolationFamilyByIds(String[] ids);

    /**
     * 删除隔离家庭 信息
     * 
     * @param id 隔离家庭 ID
     * @return 结果
     */
    public int deleteTEpidemicIsolationFamilyById(String id);

    /**
     * 批量软删除隔离家庭 
     *
     * @param ids 需要删除的隔离家庭 ID
     * @return 结果
     */
    public int updateTEpidemicIsolationFamilyByIds(String[] ids);

    public List<TEpidemicIsolationFamily> queryTEpidemicIsolationFamilyList(TEpidemicIsolationFamily tEpidemicIsolationFamily);
}
