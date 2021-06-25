package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BasePassStratygeMapper;
import com.isstech.isstechadmin.domain.BasePassStratyge;
import com.isstech.isstechadmin.service.IBasePassStratygeService;

/**
 * 通行策略 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-01
 */
@Service
public class BasePassStratygeServiceImpl implements IBasePassStratygeService 
{
    @Autowired
    private BasePassStratygeMapper basePassStratygeMapper;

    /**
     * 查询通行策略 
     * 
     * @param id 通行策略 ID
     * @return 通行策略 
     */
    @Override
    public BasePassStratyge selectBasePassStratygeById(String id)
    {
        return basePassStratygeMapper.selectBasePassStratygeById(id);
    }

    /**
     * 查询通行策略 列表
     * 
     * @param basePassStratyge 通行策略 
     * @return 通行策略 
     */
    @Override
    public List<BasePassStratyge> selectBasePassStratygeList(BasePassStratyge basePassStratyge)
    {
        return basePassStratygeMapper.selectBasePassStratygeList(basePassStratyge);
    }

    /**
     * 新增通行策略 
     * 
     * @param basePassStratyge 通行策略 
     * @return 结果
     */
    @Override
    public int insertBasePassStratyge(BasePassStratyge basePassStratyge)
    {
        basePassStratyge.setId(basePassStratygeMapper.selectUuid());
        basePassStratyge.setCreateBy(SecurityUtils.getUsername());
        basePassStratyge.setCreateTime(DateUtils.getNowDate());
        basePassStratyge.setDelFlag("0");
        return basePassStratygeMapper.insertBasePassStratyge(basePassStratyge);
    }

    /**
     * 修改通行策略 
     * 
     * @param basePassStratyge 通行策略 
     * @return 结果
     */
    @Override
    public int updateBasePassStratyge(BasePassStratyge basePassStratyge)
    {
        basePassStratyge.setUpdateBy(SecurityUtils.getUsername());
        basePassStratyge.setUpdateTime(DateUtils.getNowDate());
        return basePassStratygeMapper.updateBasePassStratyge(basePassStratyge);
    }

    /**
     * 批量删除通行策略 
     * 
     * @param ids 需要删除的通行策略 ID
     * @return 结果
     */
    @Override
    public int deleteBasePassStratygeByIds(String[] ids)
    {
        return basePassStratygeMapper.deleteBasePassStratygeByIds(ids);
    }

    /**
     * 批量软删除通行策略 
     *
     * @param ids 需要删除的通行策略 ID
     * @return 结果
     */
    @Override
    public int updateBasePassStratygeByIds(String[] ids)
    {
        return basePassStratygeMapper.updateBasePassStratygeByIds(ids);
    }

    /**
     * 删除通行策略 信息
     * 
     * @param id 通行策略 ID
     * @return 结果
     */
    @Override
    public int deleteBasePassStratygeById(String id)
    {
        return basePassStratygeMapper.deleteBasePassStratygeById(id);
    }
}
