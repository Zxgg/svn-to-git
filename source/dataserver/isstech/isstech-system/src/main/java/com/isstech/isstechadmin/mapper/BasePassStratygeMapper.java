package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePassStratyge;

/**
 * 通行策略 Mapper接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface BasePassStratygeMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询通行策略 
     * 
     * @param id 通行策略 ID
     * @return 通行策略 
     */
    public BasePassStratyge selectBasePassStratygeById(String id);

    /**
     * 查询通行策略 列表
     * 
     * @param basePassStratyge 通行策略 
     * @return 通行策略 集合
     */
    public List<BasePassStratyge> selectBasePassStratygeList(BasePassStratyge basePassStratyge);

    /**
     * 新增通行策略 
     * 
     * @param basePassStratyge 通行策略 
     * @return 结果
     */
    public int insertBasePassStratyge(BasePassStratyge basePassStratyge);

    /**
     * 修改通行策略 
     * 
     * @param basePassStratyge 通行策略 
     * @return 结果
     */
    public int updateBasePassStratyge(BasePassStratyge basePassStratyge);

    /**
     * 删除通行策略 
     * 
     * @param id 通行策略 ID
     * @return 结果
     */
    public int deleteBasePassStratygeById(String id);

    /**
     * 批量删除通行策略 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePassStratygeByIds(String[] ids);

    /**
     * 批量软删除通行策略 
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePassStratygeByIds(String[] ids);
}
