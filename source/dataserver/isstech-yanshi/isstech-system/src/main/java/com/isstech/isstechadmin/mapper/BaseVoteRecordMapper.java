package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseVoteRecord;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import org.apache.ibatis.annotations.Select;

/**
 * 投票记录 Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseVoteRecordMapper 
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
     * 删除投票记录 
     * 
     * @param id 投票记录 ID
     * @return 结果
     */
    public int deleteBaseVoteRecordById(String id);

    /**
     * 批量删除投票记录 
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseVoteRecordByIds(String[] ids);
    /**
     * 批量软删除投票记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseVoteRecordByIds(String[] ids);

    @Select("select vote_project_option_id name,count(vote_project_option_id) num from base_vote_record group by vote_project_option_id ")
    public List<GroupByVo> countVoteOption();
}
