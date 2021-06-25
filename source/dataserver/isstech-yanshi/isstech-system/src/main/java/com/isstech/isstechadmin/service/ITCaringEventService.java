package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.TCaringEvent;

/**
 * 关爱人员事件Service接口
 * 
 * @author qsw
 * @date 2020-12-12
 */
public interface ITCaringEventService 
{
    /**
     * 查询关爱人员事件
     * 
     * @param id 关爱人员事件ID
     * @return 关爱人员事件
     */
    public TCaringEvent selectTCaringEventById(String id);

    /**
     * 查询关爱人员事件列表
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 关爱人员事件集合
     */
    public List<TCaringEvent> selectTCaringEventList(TCaringEvent tCaringEvent);

    /**
     * 新增关爱人员事件
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 结果
     */
    public int insertTCaringEvent(TCaringEvent tCaringEvent);

    /**
     * 修改关爱人员事件
     * 
     * @param tCaringEvent 关爱人员事件
     * @return 结果
     */
    public int updateTCaringEvent(TCaringEvent tCaringEvent);

    /**
     * 批量删除关爱人员事件
     * 
     * @param ids 需要删除的关爱人员事件ID
     * @return 结果
     */
    public int deleteTCaringEventByIds(String[] ids);
    /**
     * 批量软删除关爱人员事件
     *
     * @param ids 需要删除的关爱人员事件ID
     * @return 结果
     */
    public int updateTCaringEventByIds(String[] ids);

    /**
     * 删除关爱人员事件信息
     * 
     * @param id 关爱人员事件ID
     * @return 结果
     */
    public int deleteTCaringEventById(String id);
}
