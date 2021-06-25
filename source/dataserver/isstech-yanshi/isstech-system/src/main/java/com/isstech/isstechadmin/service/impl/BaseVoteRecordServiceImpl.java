package com.isstech.isstechadmin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseVoteRecordMapper;
import com.isstech.isstechadmin.domain.BaseVoteRecord;
import com.isstech.isstechadmin.service.IBaseVoteRecordService;

/**
 * 投票记录 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseVoteRecordServiceImpl implements IBaseVoteRecordService 
{
    @Autowired
    private BaseVoteRecordMapper baseVoteRecordMapper;

    /**
     * 查询投票记录 
     * 
     * @param id 投票记录 ID
     * @return 投票记录 
     */
    @Override
    public BaseVoteRecord selectBaseVoteRecordById(String id)
    {
        return baseVoteRecordMapper.selectBaseVoteRecordById(id);
    }

    /**
     * 查询投票记录 列表
     * 
     * @param baseVoteRecord 投票记录 
     * @return 投票记录 
     */
    @Override
    public List<BaseVoteRecord> selectBaseVoteRecordList(BaseVoteRecord baseVoteRecord)
    {
        return baseVoteRecordMapper.selectBaseVoteRecordList(baseVoteRecord);
    }

    /**
     * 新增投票记录 
     * 
     * @param baseVoteRecord 投票记录 
     * @return 结果
     */
    @Override
    public int insertBaseVoteRecord(BaseVoteRecord baseVoteRecord)
    {
        baseVoteRecord.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isEmpty(baseVoteRecord.getCreateBy())){
            baseVoteRecord.setCreateBy(SecurityUtils.getUsername());
        }
        return baseVoteRecordMapper.insertBaseVoteRecord(baseVoteRecord);
    }

    /**
     * 修改投票记录 
     * 
     * @param baseVoteRecord 投票记录 
     * @return 结果
     */
    @Override
    public int updateBaseVoteRecord(BaseVoteRecord baseVoteRecord)
    {
        baseVoteRecord.setUpdateTime(DateUtils.getNowDate());
        baseVoteRecord.setUpdateBy(SecurityUtils.getUsername());
        return baseVoteRecordMapper.updateBaseVoteRecord(baseVoteRecord);
    }

    /**
     * 批量删除投票记录 
     * 
     * @param ids 需要删除的投票记录 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteRecordByIds(String[] ids)
    {
        return baseVoteRecordMapper.deleteBaseVoteRecordByIds(ids);
    }
    /**
     * 批量软删除投票记录
     *
     * @param ids 需要删除的投票记录 ID
     * @return 结果
     */
    @Override
    public int updateBaseVoteRecordByIds(String[] ids)
    {
        return baseVoteRecordMapper.updateBaseVoteRecordByIds(ids);
    }

    /**
     * 删除投票记录 信息
     * 
     * @param id 投票记录 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVoteRecordById(String id)
    {
        return baseVoteRecordMapper.deleteBaseVoteRecordById(id);
    }

    /**
     * 投票记录按照投票选项进行分组，返回每个选项的投票个数
     * @param queryVo
     * @return
     */
    @Override
    public Map countVoteOption() {
        List<GroupByVo> list = baseVoteRecordMapper.countVoteOption();
        Map map = new HashMap();
        list.stream().forEach(vo ->{
            String name = vo.getName();
            int num = vo.getNum();
            String[] split = name.split(",");
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                if(map.containsKey(s)) num += Integer.parseInt(map.get(s).toString());
                map.put(s,num);
            }


        });


        return map;
    }
}
