package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TCommunityEvent;

/**
 * 社区事件 Service接口
 * 
 * @author yzp
 * @date 2020-12-12
 */
public interface ITCommunityEventService 
{
    /**
     * 查询社区事件 
     * 
     * @param id 社区事件 ID
     * @return 社区事件 
     */
    public TCommunityEvent selectTCommunityEventById(String id);

    /**
     * 查询社区事件 列表
     * 
     * @param tCommunityEvent 社区事件 
     * @return 社区事件 集合
     */
    public List<TCommunityEvent> selectTCommunityEventList(TCommunityEvent tCommunityEvent);

    /**
     * 新增社区事件 
     * 
     * @param tCommunityEvent 社区事件 
     * @return 结果
     */
    public int insertTCommunityEvent(TCommunityEvent tCommunityEvent);

    /**
     * 修改社区事件 
     * 
     * @param tCommunityEvent 社区事件 
     * @return 结果
     */
    public int updateTCommunityEvent(TCommunityEvent tCommunityEvent);

    /**
     * 批量删除社区事件 
     * 
     * @param ids 需要删除的社区事件 ID
     * @return 结果
     */
    public int deleteTCommunityEventByIds(String[] ids);
    /**
     * 批量软删除社区事件
     *
     * @param ids 需要删除的社区事件 ID
     * @return 结果
     */
    public int updateTCommunityEventByIds(String[] ids);

    /**
     * 删除社区事件 信息
     * 
     * @param id 社区事件 ID
     * @return 结果
     */
    public int deleteTCommunityEventById(String id);
}
