package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.domain.TSpecialYoung;
import com.isstech.isstechadmin.mapper.TSpecialYoungMapper;
import com.isstech.isstechadmin.service.ITSpecialYoungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 重点青少年 Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-08
 */
@Service
public class TSpecialYoungServiceImpl implements ITSpecialYoungService
{
    @Autowired
    private TSpecialYoungMapper tSpecialYoungMapper;

    /**
     * 查询重点青少年 
     * 
     * @param id 重点青少年 ID
     * @return 重点青少年 
     */
    @Override
    public TSpecialYoung selectTSpecialYoungById(String id)
    {
        return tSpecialYoungMapper.selectTSpecialYoungById(id);
    }

    /**
     * 查询重点青少年 列表
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 重点青少年 
     */
    @Override
    public List<TSpecialYoung> selectTSpecialYoungList(TSpecialYoung tSpecialYoung)
    {
        return tSpecialYoungMapper.selectTSpecialYoungList(tSpecialYoung);
    }

    /**
     * 新增重点青少年 
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 结果
     */
    @Override
    public int insertTSpecialYoung(TSpecialYoung tSpecialYoung)
    {
        tSpecialYoung.setCreateTime(DateUtils.getNowDate());
        return tSpecialYoungMapper.insertTSpecialYoung(tSpecialYoung);
    }

    /**
     * 修改重点青少年 
     * 
     * @param tSpecialYoung 重点青少年 
     * @return 结果
     */
    @Override
    public int updateTSpecialYoung(TSpecialYoung tSpecialYoung)
    {
        tSpecialYoung.setUpdateTime(DateUtils.getNowDate());
        return tSpecialYoungMapper.updateTSpecialYoung(tSpecialYoung);
    }

    /**
     * 批量删除重点青少年 
     * 
     * @param ids 需要删除的重点青少年 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialYoungByIds(String[] ids)
    {
        return tSpecialYoungMapper.deleteTSpecialYoungByIds(ids);
    }
    /**
     * 批量软删除重点青少年
     *
     * @param ids 需要删除的重点青少年 ID
     * @return 结果
     */
    @Override
    public int updateTSpecialYoungByIds(String[] ids)
    {
        return tSpecialYoungMapper.updateTSpecialYoungByIds(ids);
    }

    /**
     * 删除重点青少年 信息
     * 
     * @param id 重点青少年 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialYoungById(String id)
    {
        return tSpecialYoungMapper.deleteTSpecialYoungById(id);
    }
}
