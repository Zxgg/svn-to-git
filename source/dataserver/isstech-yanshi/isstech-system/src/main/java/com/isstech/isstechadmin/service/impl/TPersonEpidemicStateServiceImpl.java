package com.isstech.isstechadmin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TPersonEpidemicStateMapper;
import com.isstech.isstechadmin.domain.TPersonEpidemicState;
import com.isstech.isstechadmin.service.ITPersonEpidemicStateService;

/**
 * 疫情信息收集Service业务层处理
 * 
 * @author yzp
 * @date 2021-01-15
 */
@Service
public class TPersonEpidemicStateServiceImpl implements ITPersonEpidemicStateService 
{
    @Autowired
    private TPersonEpidemicStateMapper tPersonEpidemicStateMapper;

    /**
     * 查询疫情信息收集
     * 
     * @param id 疫情信息收集ID
     * @return 疫情信息收集
     */
    @Override
    public TPersonEpidemicState selectTPersonEpidemicStateById(String id)
    {
        return tPersonEpidemicStateMapper.selectTPersonEpidemicStateById(id);
    }

    /**
     * 查询疫情信息收集列表
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 疫情信息收集
     */
    @Override
    public List<TPersonEpidemicState> selectTPersonEpidemicStateList(TPersonEpidemicState tPersonEpidemicState)
    {
        return tPersonEpidemicStateMapper.selectTPersonEpidemicStateList(tPersonEpidemicState);
    }

    /**
     * 新增疫情信息收集
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 结果
     */
    @Override
    public int insertTPersonEpidemicState(TPersonEpidemicState tPersonEpidemicState)
    {
        if(StringUtils.isEmpty(tPersonEpidemicState.getCreateBy())){
            tPersonEpidemicState.setCreateBy(SecurityUtils.getUsername());
        }
        tPersonEpidemicState.setCreateTime(DateUtils.getNowDate());
        tPersonEpidemicState.setDelFlag("0");
        tPersonEpidemicState.setId(tPersonEpidemicStateMapper.selectUuid());
        return tPersonEpidemicStateMapper.insertTPersonEpidemicState(tPersonEpidemicState);
    }

    /**
     * 修改疫情信息收集
     * 
     * @param tPersonEpidemicState 疫情信息收集
     * @return 结果
     */
    @Override
    public int updateTPersonEpidemicState(TPersonEpidemicState tPersonEpidemicState)
    {
        tPersonEpidemicState.setUpdateBy(SecurityUtils.getUsername());
        tPersonEpidemicState.setUpdateTime(DateUtils.getNowDate());
        return tPersonEpidemicStateMapper.updateTPersonEpidemicState(tPersonEpidemicState);
    }

    /**
     * 批量删除疫情信息收集
     * 
     * @param ids 需要删除的疫情信息收集ID
     * @return 结果
     */
    @Override
    public int deleteTPersonEpidemicStateByIds(String[] ids)
    {
        return tPersonEpidemicStateMapper.deleteTPersonEpidemicStateByIds(ids);
    }

    /**
     * 批量软删除疫情信息收集
     *
     * @param ids 需要删除的疫情信息收集ID
     * @return 结果
     */
    @Override
    public int updateTPersonEpidemicStateByIds(String[] ids)
    {
        return tPersonEpidemicStateMapper.updateTPersonEpidemicStateByIds(ids);
    }

    /**
     * 删除疫情信息收集信息
     * 
     * @param id 疫情信息收集ID
     * @return 结果
     */
    @Override
    public int deleteTPersonEpidemicStateById(String id)
    {
        return tPersonEpidemicStateMapper.deleteTPersonEpidemicStateById(id);
    }
}
