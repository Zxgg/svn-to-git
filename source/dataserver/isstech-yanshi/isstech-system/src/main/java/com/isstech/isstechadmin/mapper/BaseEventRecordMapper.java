package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseEventRecord;

/**
 * 事件Mapper接口
 * 
 * @author yzp
 * @date 2021-03-30
 */
public interface BaseEventRecordMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询事件
     * 
     * @param id 事件ID
     * @return 事件
     */
    public BaseEventRecord selectBaseEventRecordById(String id);

    /**
     * 查询事件列表
     * 
     * @param baseEventRecord 事件
     * @return 事件集合
     */
    public List<BaseEventRecord> selectBaseEventRecordList(BaseEventRecord baseEventRecord);

    /**
     * 新增事件
     * 
     * @param baseEventRecord 事件
     * @return 结果
     */
    public int insertBaseEventRecord(BaseEventRecord baseEventRecord);

    /**
     * 修改事件
     * 
     * @param baseEventRecord 事件
     * @return 结果
     */
    public int updateBaseEventRecord(BaseEventRecord baseEventRecord);

    /**
     * 删除事件
     * 
     * @param id 事件ID
     * @return 结果
     */
    public int deleteBaseEventRecordById(String id);

    /**
     * 批量删除事件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseEventRecordByIds(String[] ids);

    /**
     * 批量软删除事件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseEventRecordByIds(String[] ids);
}
