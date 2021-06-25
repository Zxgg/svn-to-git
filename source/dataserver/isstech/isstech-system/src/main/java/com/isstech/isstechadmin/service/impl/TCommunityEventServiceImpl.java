package com.isstech.isstechadmin.service.impl;

import java.util.List;

import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.TCommunityEventMapper;
import com.isstech.isstechadmin.domain.TCommunityEvent;
import com.isstech.isstechadmin.service.ITCommunityEventService;

/**
 * 社区事件 Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-12
 */
@Service
public class TCommunityEventServiceImpl implements ITCommunityEventService 
{
    @Autowired
    private TCommunityEventMapper tCommunityEventMapper;

    /**
     * 查询社区事件 
     * 
     * @param id 社区事件 ID
     * @return 社区事件 
     */
    @Override
    public TCommunityEvent selectTCommunityEventById(String id)
    {
        return tCommunityEventMapper.selectTCommunityEventById(id);
    }

    /**
     * 查询社区事件 列表
     * 
     * @param tCommunityEvent 社区事件 
     * @return 社区事件 
     */
    @Override
    public List<TCommunityEvent> selectTCommunityEventList(TCommunityEvent tCommunityEvent)
    {
        return tCommunityEventMapper.selectTCommunityEventList(tCommunityEvent);
    }

    /**
     * 新增社区事件 
     * 
     * @param tCommunityEvent 社区事件 
     * @return 结果
     */
    @Override
    public int insertTCommunityEvent(TCommunityEvent tCommunityEvent)
    {
        tCommunityEvent.setCreateTime(DateUtils.getNowDate());
        return tCommunityEventMapper.insertTCommunityEvent(tCommunityEvent);
    }

    /**
     * 修改社区事件 
     * 
     * @param tCommunityEvent 社区事件 
     * @return 结果
     */
    @Override
    public int updateTCommunityEvent(TCommunityEvent tCommunityEvent)
    {
        tCommunityEvent.setUpdateTime(DateUtils.getNowDate());
        return tCommunityEventMapper.updateTCommunityEvent(tCommunityEvent);
    }

    /**
     * 批量删除社区事件 
     * 
     * @param ids 需要删除的社区事件 ID
     * @return 结果
     */
    @Override
    public int deleteTCommunityEventByIds(String[] ids)
    {
        return tCommunityEventMapper.deleteTCommunityEventByIds(ids);
    }
    /**
     * 批量软删除社区事件
     *
     * @param ids 需要删除的社区事件 ID
     * @return 结果
     */
    @Override
    public int updateTCommunityEventByIds(String[] ids)
    {
        return tCommunityEventMapper.updateTCommunityEventByIds(ids);
    }

    /**
     * 删除社区事件 信息
     * 
     * @param id 社区事件 ID
     * @return 结果
     */
    @Override
    public int deleteTCommunityEventById(String id)
    {
        return tCommunityEventMapper.deleteTCommunityEventById(id);
    }
}
