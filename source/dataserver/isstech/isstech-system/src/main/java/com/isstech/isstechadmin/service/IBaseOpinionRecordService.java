package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.BaseOpinionRecord;

import java.util.List;

/**
 * 投诉意见记录Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseOpinionRecordService 
{
    /**
     * 查询投诉意见记录
     * 
     * @param id 投诉意见记录ID
     * @return 投诉意见记录
     */
    public BaseOpinionRecord selectBaseOpinionRecordById(String id);

    /**
     * 查询投诉意见记录列表
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 投诉意见记录集合
     */
    public List<BaseOpinionRecord> selectBaseOpinionRecordList(BaseOpinionRecord baseOpinionRecord);

    /**
     * 新增投诉意见记录
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 结果
     */
    public int insertBaseOpinionRecord(BaseOpinionRecord baseOpinionRecord);

    /**
     * 修改投诉意见记录
     * 
     * @param baseOpinionRecord 投诉意见记录
     * @return 结果
     */
    public int updateBaseOpinionRecord(BaseOpinionRecord baseOpinionRecord);

    /**
     * 批量删除投诉意见记录
     * 
     * @param ids 需要删除的投诉意见记录ID
     * @return 结果
     */
    public int deleteBaseOpinionRecordByIds(String[] ids);
    /**
     * 批量软删除投诉意见记录
     *
     * @param ids 需要删除的投诉意见记录ID
     * @return 结果
     */
    public int updateBaseOpinionRecordByIds(String[] ids);

    /**
     * 删除投诉意见记录信息
     * 
     * @param id 投诉意见记录ID
     * @return 结果
     */
    public int deleteBaseOpinionRecordById(String id);
}
