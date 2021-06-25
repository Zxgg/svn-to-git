package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseOpinionRecordMapper;
import com.isstech.isstechadmin.domain.BaseOpinionRecord;
import com.isstech.isstechadmin.service.IBaseOpinionRecordService;

/**
 * 投诉意见记录Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseOpinionRecordServiceImpl implements IBaseOpinionRecordService 
{
    @Autowired
    private BaseOpinionRecordMapper baseOpinionRecordMapper;

    /**
     * 查询投诉意见记录
     * 
     * @param id 投诉意见记录ID
     * @return 投诉意见记录
     */
    @Override
    public BaseOpinionRecord selectBaseOpinionRecordById(String id)
    {
        return baseOpinionRecordMapper.selectBaseOpinionRecordById(id);
    }

    /**
     * 查询投诉意见记录列表
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 投诉意见记录
     */
    @Override
    public List<BaseOpinionRecord> selectBaseOpinionRecordList(BaseOpinionRecord baseOpinionRecord)
    {
        return baseOpinionRecordMapper.selectBaseOpinionRecordList(baseOpinionRecord);
    }

    /**
     * 新增投诉意见记录
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 结果
     */
    @Override
    public int insertBaseOpinionRecord(BaseOpinionRecord baseOpinionRecord)
    {
        baseOpinionRecord.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isEmpty(baseOpinionRecord.getCreateBy())){
            baseOpinionRecord.setCreateBy(SecurityUtils.getUsername());
        }
        return baseOpinionRecordMapper.insertBaseOpinionRecord(baseOpinionRecord);
    }

    /**
     * 修改投诉意见记录
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 结果
     */
    @Override
    public int updateBaseOpinionRecord(BaseOpinionRecord baseOpinionRecord)
    {
        baseOpinionRecord.setUpdateTime(DateUtils.getNowDate());
        baseOpinionRecord.setUpdateBy(SecurityUtils.getUsername());
        return baseOpinionRecordMapper.updateBaseOpinionRecord(baseOpinionRecord);
    }

    /**
     * 批量删除投诉意见记录
     * 
     * @param ids 需要删除的投诉意见记录ID
     * @return 结果
     */
    @Override
    public int deleteBaseOpinionRecordByIds(String[] ids)
    {
        return baseOpinionRecordMapper.deleteBaseOpinionRecordByIds(ids);
    }

    /**
     * 批量软删除投诉意见记录
     *
     * @param ids 需要删除的投诉意见记录ID
     * @return 结果
     */
    @Override
    public int updateBaseOpinionRecordByIds(String[] ids)
    {
        return baseOpinionRecordMapper.updateBaseOpinionRecordByIds(ids);
    }

    /**
     * 删除投诉意见记录信息
     * 
     * @param id 投诉意见记录ID
     * @return 结果
     */
    @Override
    public int deleteBaseOpinionRecordById(String id)
    {
        return baseOpinionRecordMapper.deleteBaseOpinionRecordById(id);
    }
}
