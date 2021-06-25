package com.isstech.isstechadmin.service;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.BaseVoteRecord;
import com.isstech.isstechadmin.domain.vo.GroupByVo;

/**
 * 投票记录 Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseVoteRecordService 
{
    /**
     * 查询投票记录 
     * 
     * @param id 投票记录 ID
     * @return 投票记录 
     */
    public BaseVoteRecord selectBaseVoteRecordById(String id);

    /**
     * 查询投票记录 列表
     * 
     * @param baseVoteRecord 投票记录 
     * @return 投票记录 集合
     */
    public List<BaseVoteRecord> selectBaseVoteRecordList(BaseVoteRecord baseVoteRecord);

    /**
     * 新增投票记录 
     * 
     * @param baseVoteRecord 投票记录 
     * @return 结果
     */
    public int insertBaseVoteRecord(BaseVoteRecord baseVoteRecord);

    /**
     * 修改投票记录 
     * 
     * @param baseVoteRecord 投票记录 
     * @return 结果
     */
    public int updateBaseVoteRecord(BaseVoteRecord baseVoteRecord);

    /**
     * 批量删除投票记录 
     * 
     * @param ids 需要删除的投票记录 ID
     * @return 结果
     */
    public int deleteBaseVoteRecordByIds(String[] ids);
    /**
     * 批量软删除投票记录
     *
     * @param ids 需要删除的投票记录 ID
     * @return 结果
     */
    public int updateBaseVoteRecordByIds(String[] ids);

    /**
     * 删除投票记录 信息
     * 
     * @param id 投票记录 ID
     * @return 结果
     */
    public int deleteBaseVoteRecordById(String id);

    /**
     * 投票记录按照投票选项进行分组，返回每个选项的投票个数
     * @param queryVo
     * @return
     */
    public Map countVoteOption();
}
