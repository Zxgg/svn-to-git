package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseRepairRecord;

/**
 * 报事报修记录Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseRepairRecordService 
{
    /**
     * 查询报事报修记录
     * 
     * @param id 报事报修记录ID
     * @return 报事报修记录
     */
    public BaseRepairRecord selectBaseRepairRecordById(String id);

    /**
     * 查询报事报修记录列表
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 报事报修记录集合
     */
    public List<BaseRepairRecord> selectBaseRepairRecordList(BaseRepairRecord baseRepairRecord);

    /**
     * 新增报事报修记录
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 结果
     */
    public int insertBaseRepairRecord(BaseRepairRecord baseRepairRecord);

    /**
     * 修改报事报修记录
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 结果
     */
    public int updateBaseRepairRecord(BaseRepairRecord baseRepairRecord);

    /**
     * 批量删除报事报修记录
     * 
     * @param ids 需要删除的报事报修记录ID
     * @return 结果
     */
    public int deleteBaseRepairRecordByIds(String[] ids);
    /**
     * 批量软删除报事报修记录
     *
     * @param ids 需要删除的报事报修记录ID
     * @return 结果
     */
    public int updateBaseRepairRecordByIds(String[] ids);

    /**
     * 删除报事报修记录信息
     * 
     * @param id 报事报修记录ID
     * @return 结果
     */
    public int deleteBaseRepairRecordById(String id);
}
