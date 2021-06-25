package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.domain.TSpeciaStray;
import com.isstech.isstechadmin.mapper.TSpeciaStrayMapper;
import com.isstech.isstechadmin.service.ITSpeciaStrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 闲散流浪人员信息 Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-08
 */
@Service
public class TSpeciaStrayServiceImpl implements ITSpeciaStrayService
{
    @Autowired
    private TSpeciaStrayMapper tSpeciaStrayMapper;

    /**
     * 查询闲散流浪人员信息 
     * 
     * @param id 闲散流浪人员信息 ID
     * @return 闲散流浪人员信息 
     */
    @Override
    public TSpeciaStray selectTSpeciaStrayById(String id)
    {
        return tSpeciaStrayMapper.selectTSpeciaStrayById(id);
    }

    /**
     * 查询闲散流浪人员信息 列表
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 闲散流浪人员信息 
     */
    @Override
    public List<TSpeciaStray> selectTSpeciaStrayList(TSpeciaStray tSpeciaStray)
    {
        return tSpeciaStrayMapper.selectTSpeciaStrayList(tSpeciaStray);
    }

    /**
     * 新增闲散流浪人员信息 
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 结果
     */
    @Override
    public int insertTSpeciaStray(TSpeciaStray tSpeciaStray)
    {
        tSpeciaStray.setCreateTime(DateUtils.getNowDate());
        return tSpeciaStrayMapper.insertTSpeciaStray(tSpeciaStray);
    }

    /**
     * 修改闲散流浪人员信息 
     * 
     * @param tSpeciaStray 闲散流浪人员信息 
     * @return 结果
     */
    @Override
    public int updateTSpeciaStray(TSpeciaStray tSpeciaStray)
    {
        tSpeciaStray.setUpdateTime(DateUtils.getNowDate());
        return tSpeciaStrayMapper.updateTSpeciaStray(tSpeciaStray);
    }

    /**
     * 批量删除闲散流浪人员信息 
     * 
     * @param ids 需要删除的闲散流浪人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaStrayByIds(String[] ids)
    {
        return tSpeciaStrayMapper.deleteTSpeciaStrayByIds(ids);
    }
    /**
     * 批量软删除闲散流浪人员信息
     *
     * @param ids 需要删除的闲散流浪人员信息 ID
     * @return 结果
     */
    @Override
    public int updateTSpeciaStrayByIds(String[] ids)
    {
        return tSpeciaStrayMapper.updateTSpeciaStrayByIds(ids);
    }

    /**
     * 删除闲散流浪人员信息 信息
     * 
     * @param id 闲散流浪人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaStrayById(String id)
    {
        return tSpeciaStrayMapper.deleteTSpeciaStrayById(id);
    }
}
