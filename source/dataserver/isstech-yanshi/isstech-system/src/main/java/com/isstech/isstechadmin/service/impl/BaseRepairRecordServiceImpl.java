package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.isstechadmin.mapper.BaseRepairRecordMapper;
import com.isstech.isstechadmin.domain.BaseRepairRecord;
import com.isstech.isstechadmin.service.IBaseRepairRecordService;

/**
 * 报事报修记录Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-25
 */
@Service
public class BaseRepairRecordServiceImpl implements IBaseRepairRecordService 
{
    @Autowired
    private BaseRepairRecordMapper baseRepairRecordMapper;

    /**
     * 查询报事报修记录
     * 
     * @param id 报事报修记录ID
     * @return 报事报修记录
     */
    @Override
    public BaseRepairRecord selectBaseRepairRecordById(String id)
    {
        return baseRepairRecordMapper.selectBaseRepairRecordById(id);
    }

    /**
     * 查询报事报修记录列表
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 报事报修记录
     */
    @Override
    public List<BaseRepairRecord> selectBaseRepairRecordList(BaseRepairRecord baseRepairRecord)
    {
        return baseRepairRecordMapper.selectBaseRepairRecordList(baseRepairRecord);
    }

    /**
     * 新增报事报修记录
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 结果
     */
    @Override
    public int insertBaseRepairRecord(BaseRepairRecord baseRepairRecord)
    {
        baseRepairRecord.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isEmpty(baseRepairRecord.getCreateBy())){
            baseRepairRecord.setCreateBy(SecurityUtils.getUsername());
        }
        return baseRepairRecordMapper.insertBaseRepairRecord(baseRepairRecord);
    }

    /**
     * 修改报事报修记录
     * 
     * @param baseRepairRecord 报事报修记录
     * @return 结果
     */
    @Override
    public int updateBaseRepairRecord(BaseRepairRecord baseRepairRecord)
    {
        baseRepairRecord.setUpdateTime(DateUtils.getNowDate());
        baseRepairRecord.setUpdateBy(SecurityUtils.getUsername());
        return baseRepairRecordMapper.updateBaseRepairRecord(baseRepairRecord);
    }

    /**
     * 批量删除报事报修记录
     *
     * @param ids 需要删除的报事报修记录ID
     * @return 结果
     */
    @Override
    public int deleteBaseRepairRecordByIds(String[] ids)
    {
        return baseRepairRecordMapper.deleteBaseRepairRecordByIds(ids);
    }
    /**
     * 批量软删除报事报修记录
     *
     * @param ids 需要删除的报事报修记录ID
     * @return 结果
     */
    @Override
    public int updateBaseRepairRecordByIds(String[] ids)
    {
        return baseRepairRecordMapper.updateBaseRepairRecordByIds(ids);
    }

    /**
     * 删除报事报修记录信息
     *
     * @param id 报事报修记录ID
     * @return 结果
     */
    @Override
    public int deleteBaseRepairRecordById(String id)
    {
        return baseRepairRecordMapper.deleteBaseRepairRecordById(id);
    }
}
