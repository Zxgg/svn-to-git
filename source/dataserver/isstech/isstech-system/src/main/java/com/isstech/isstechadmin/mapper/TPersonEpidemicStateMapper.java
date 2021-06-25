package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.TPersonEpidemicState;

/**
 * 疫情信息收集Mapper接口
 * 
 * @author yzp
 * @date 2021-01-15
 */
public interface TPersonEpidemicStateMapper 
{

    /**
     *  获取uuid
     * */
    public String selectUuid();

    /**
     * 查询疫情信息收集
     * 
     * @param id 疫情信息收集ID
     * @return 疫情信息收集
     */
    public TPersonEpidemicState selectTPersonEpidemicStateById(String id);

    /**
     * 查询疫情信息收集列表
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 疫情信息收集集合
     */
    public List<TPersonEpidemicState> selectTPersonEpidemicStateList(TPersonEpidemicState tPersonEpidemicState);

    /**
     * 新增疫情信息收集
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 结果
     */
    public int insertTPersonEpidemicState(TPersonEpidemicState tPersonEpidemicState);

    /**
     * 修改疫情信息收集
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 结果
     */
    public int updateTPersonEpidemicState(TPersonEpidemicState tPersonEpidemicState);

    /**
     * 删除疫情信息收集
     * 
     * @param id 疫情信息收集ID
     * @return 结果
     */
    public int deleteTPersonEpidemicStateById(String id);

    /**
     * 批量删除疫情信息收集
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTPersonEpidemicStateByIds(String[] ids);

    /**
     * 批量软删除疫情信息收集
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateTPersonEpidemicStateByIds(String[] ids);
}
