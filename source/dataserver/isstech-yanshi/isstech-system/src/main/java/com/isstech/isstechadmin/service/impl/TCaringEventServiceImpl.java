package com.isstech.isstechadmin.service.impl;

import java.util.List;

import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.TCaringEventMapper;
import com.isstech.isstechadmin.domain.TCaringEvent;
import com.isstech.isstechadmin.service.ITCaringEventService;

/**
 * 关爱人员事件Service业务层处理
 * 
 * @author qsw
 * @date 2020-12-12
 */
@Service
public class TCaringEventServiceImpl implements ITCaringEventService 
{
    @Autowired
    private TCaringEventMapper tCaringEventMapper;

    /**
     * 查询关爱人员事件
     * 
     * @param id 关爱人员事件ID
     * @return 关爱人员事件
     */
    @Override
    public TCaringEvent selectTCaringEventById(String id)
    {
        return tCaringEventMapper.selectTCaringEventById(id);
    }

    /**
     * 查询关爱人员事件列表
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 关爱人员事件
     */
    @Override
    public List<TCaringEvent> selectTCaringEventList(TCaringEvent tCaringEvent)
    {
        return tCaringEventMapper.selectTCaringEventList(tCaringEvent);
    }

    /**
     * 新增关爱人员事件
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 结果
     */
    @Override
    public int insertTCaringEvent(TCaringEvent tCaringEvent)
    {
        tCaringEvent.setCreateTime(DateUtils.getNowDate());
        return tCaringEventMapper.insertTCaringEvent(tCaringEvent);
    }

    /**
     * 修改关爱人员事件
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 结果
     */
    @Override
    public int updateTCaringEvent(TCaringEvent tCaringEvent)
    {
        tCaringEvent.setUpdateTime(DateUtils.getNowDate());
        return tCaringEventMapper.updateTCaringEvent(tCaringEvent);
    }

    /**
     * 批量删除关爱人员事件
     * 
     * @param ids 需要删除的关爱人员事件ID
     * @return 结果
     */
    @Override
    public int deleteTCaringEventByIds(String[] ids)
    {
        return tCaringEventMapper.deleteTCaringEventByIds(ids);
    }
    /**
     * 批量软删除关爱人员事件
     *
     * @param ids 需要删除的关爱人员事件ID
     * @return 结果
     */
    @Override
    public int updateTCaringEventByIds(String[] ids)
    {
        return tCaringEventMapper.updateTCaringEventByIds(ids);
    }

    /**
     * 删除关爱人员事件信息
     * 
     * @param id 关爱人员事件ID
     * @return 结果
     */
    @Override
    public int deleteTCaringEventById(String id)
    {
        return tCaringEventMapper.deleteTCaringEventById(id);
    }
}
