package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseEventRecordMapper;
import com.isstech.isstechadmin.domain.BaseEventRecord;
import com.isstech.isstechadmin.service.IBaseEventRecordService;

/**
 * 事件Service业务层处理
 * 
 * @author yzp
 * @date 2021-03-30
 */
@Service
public class BaseEventRecordServiceImpl implements IBaseEventRecordService 
{
    @Autowired
    private BaseEventRecordMapper baseEventRecordMapper;

    /**
     * 查询事件
     * 
     * @param id 事件ID
     * @return 事件
     */
    @Override
    public BaseEventRecord selectBaseEventRecordById(String id)
    {
        return baseEventRecordMapper.selectBaseEventRecordById(id);
    }

    /**
     * 查询事件列表
     * 
     * @param baseEventRecord 事件
     * @return 事件
     */
    @Override
    public List<BaseEventRecord> selectBaseEventRecordList(BaseEventRecord baseEventRecord)
    {
        return baseEventRecordMapper.selectBaseEventRecordList(baseEventRecord);
    }

    /**
     * 新增事件
     * 
     * @param baseEventRecord 事件
     * @return 结果
     */
    @Override
    public int insertBaseEventRecord(BaseEventRecord baseEventRecord)
    {
        baseEventRecord.setId(baseEventRecordMapper.selectUuid());
        baseEventRecord.setCreateTime(DateUtils.getNowDate());
        baseEventRecord.setDelFlag("0");
        return baseEventRecordMapper.insertBaseEventRecord(baseEventRecord);
    }

    /**
     * 修改事件
     * 
     * @param baseEventRecord 事件
     * @return 结果
     */
    @Override
    public int updateBaseEventRecord(BaseEventRecord baseEventRecord)
    {
        baseEventRecord.setUpdateTime(DateUtils.getNowDate());
        return baseEventRecordMapper.updateBaseEventRecord(baseEventRecord);
    }

    /**
     * 批量删除事件
     * 
     * @param ids 需要删除的事件ID
     * @return 结果
     */
    @Override
    public int deleteBaseEventRecordByIds(String[] ids)
    {
        return baseEventRecordMapper.deleteBaseEventRecordByIds(ids);
    }

    /**
     * 批量软删除事件
     *
     * @param ids 需要删除的事件ID
     * @return 结果
     */
    @Override
    public int updateBaseEventRecordByIds(String[] ids)
    {
        return baseEventRecordMapper.updateBaseEventRecordByIds(ids);
    }

    /**
     * 删除事件信息
     * 
     * @param id 事件ID
     * @return 结果
     */
    @Override
    public int deleteBaseEventRecordById(String id)
    {
        return baseEventRecordMapper.deleteBaseEventRecordById(id);
    }
}
