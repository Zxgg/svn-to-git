package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TSpeciaCorrectMapper;
import com.isstech.isstechadmin.domain.TSpeciaCorrect;
import com.isstech.isstechadmin.service.ITSpeciaCorrectService;

/**
 * 社区矫正人员信息 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Service
public class TSpeciaCorrectServiceImpl implements ITSpeciaCorrectService 
{
    @Autowired
    private TSpeciaCorrectMapper tSpeciaCorrectMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询社区矫正人员信息 
     * 
     * @param id 社区矫正人员信息 ID
     * @return 社区矫正人员信息 
     */
    @Override
    public TSpeciaCorrect selectTSpeciaCorrectById(String id)
    {
        return tSpeciaCorrectMapper.selectTSpeciaCorrectById(id);
    }

    /**
     * 查询社区矫正人员信息 列表
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 社区矫正人员信息 
     */
    @Override
    public List<TSpeciaCorrect> selectTSpeciaCorrectList(TSpeciaCorrect tSpeciaCorrect)
    {
        return tSpeciaCorrectMapper.selectTSpeciaCorrectList(tSpeciaCorrect);
    }

    /**
     * 新增社区矫正人员信息 
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 结果
     */
    @Override
    public int insertTSpeciaCorrect(TSpeciaCorrect tSpeciaCorrect)
    {
        tSpeciaCorrect.setId(tSpeciaCorrectMapper.selectUuid());
        tSpeciaCorrect.setCreateBy(SecurityUtils.getUsername());
        tSpeciaCorrect.setCreateTime(DateUtils.getNowDate());
        tSpeciaCorrect.setDelFlag("0");
        int row = tSpeciaCorrectMapper.insertTSpeciaCorrect(tSpeciaCorrect);
        dataSynchronizationUtils.dataParsing(tSpeciaCorrect,"insertTSpeciaCorrect");
        return row;
    }

    /**
     * 修改社区矫正人员信息 
     * 
     * @param tSpeciaCorrect 社区矫正人员信息 
     * @return 结果
     */
    @Override
    public int updateTSpeciaCorrect(TSpeciaCorrect tSpeciaCorrect)
    {
        tSpeciaCorrect.setUpdateBy(SecurityUtils.getUsername());
        tSpeciaCorrect.setUpdateTime(DateUtils.getNowDate());
        int row = tSpeciaCorrectMapper.updateTSpeciaCorrect(tSpeciaCorrect);
        dataSynchronizationUtils.dataParsing(tSpeciaCorrect,"updateTSpeciaCorrect");
        return row;
    }

    /**
     * 批量删除社区矫正人员信息 
     * 
     * @param ids 需要删除的社区矫正人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaCorrectByIds(String[] ids)
    {
        int row = tSpeciaCorrectMapper.deleteTSpeciaCorrectByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteTSpeciaCorrectByIds");
        return row;
    }

    /**
     * 批量软删除社区矫正人员信息 
     *
     * @param ids 需要删除的社区矫正人员信息 ID
     * @return 结果
     */
    @Override
    public int updateTSpeciaCorrectByIds(String[] ids)
    {
        int row = tSpeciaCorrectMapper.updateTSpeciaCorrectByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaCorrectByIds");
        return row;
    }

    /**
     * 删除社区矫正人员信息 信息
     * 
     * @param id 社区矫正人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaCorrectById(String id)
    {
        int row = tSpeciaCorrectMapper.deleteTSpeciaCorrectById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteTSpeciaCorrectById");
        return row;
    }
}
