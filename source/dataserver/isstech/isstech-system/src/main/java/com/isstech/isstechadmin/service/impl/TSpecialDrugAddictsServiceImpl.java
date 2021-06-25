package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TSpecialDrugAddictsMapper;
import com.isstech.isstechadmin.domain.TSpecialDrugAddicts;
import com.isstech.isstechadmin.service.ITSpecialDrugAddictsService;

/**
 * 吸毒人员信息 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Service
public class TSpecialDrugAddictsServiceImpl implements ITSpecialDrugAddictsService 
{
    @Autowired
    private TSpecialDrugAddictsMapper tSpecialDrugAddictsMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询吸毒人员信息 
     * 
     * @param id 吸毒人员信息 ID
     * @return 吸毒人员信息 
     */
    @Override
    public TSpecialDrugAddicts selectTSpecialDrugAddictsById(String id)
    {
        return tSpecialDrugAddictsMapper.selectTSpecialDrugAddictsById(id);
    }

    /**
     * 查询吸毒人员信息 列表
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 吸毒人员信息 
     */
    @Override
    public List<TSpecialDrugAddicts> selectTSpecialDrugAddictsList(TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        return tSpecialDrugAddictsMapper.selectTSpecialDrugAddictsList(tSpecialDrugAddicts);
    }

    /**
     * 新增吸毒人员信息 
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 结果
     */
    @Override
    public int insertTSpecialDrugAddicts(TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        tSpecialDrugAddicts.setId(tSpecialDrugAddictsMapper.selectUuid());
        tSpecialDrugAddicts.setCreateBy(SecurityUtils.getUsername());
        tSpecialDrugAddicts.setCreateTime(DateUtils.getNowDate());
        tSpecialDrugAddicts.setDelFlag("0");
        int row = tSpecialDrugAddictsMapper.insertTSpecialDrugAddicts(tSpecialDrugAddicts);
        dataSynchronizationUtils.dataParsing(tSpecialDrugAddicts,"insertTSpecialDrugAddicts");
        return row;
    }

    /**
     * 修改吸毒人员信息 
     * 
     * @param tSpecialDrugAddicts 吸毒人员信息 
     * @return 结果
     */
    @Override
    public int updateTSpecialDrugAddicts(TSpecialDrugAddicts tSpecialDrugAddicts)
    {
        tSpecialDrugAddicts.setUpdateBy(SecurityUtils.getUsername());
        tSpecialDrugAddicts.setUpdateTime(DateUtils.getNowDate());
        int row = tSpecialDrugAddictsMapper.updateTSpecialDrugAddicts(tSpecialDrugAddicts);
        dataSynchronizationUtils.dataParsing(tSpecialDrugAddicts,"updateTSpecialDrugAddicts");
        return row;
    }

    /**
     * 批量删除吸毒人员信息 
     * 
     * @param ids 需要删除的吸毒人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialDrugAddictsByIds(String[] ids)
    {
        int row = tSpecialDrugAddictsMapper.deleteTSpecialDrugAddictsByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteTSpecialDrugAddictsByIds");
        return row;
    }

    /**
     * 批量软删除吸毒人员信息 
     *
     * @param ids 需要删除的吸毒人员信息 ID
     * @return 结果
     */
    @Override
    public int updateTSpecialDrugAddictsByIds(String[] ids)
    {
        int row = tSpecialDrugAddictsMapper.updateTSpecialDrugAddictsByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateTSpecialDrugAddictsByIds");
        return row;
    }

    /**
     * 删除吸毒人员信息 信息
     * 
     * @param id 吸毒人员信息 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialDrugAddictsById(String id)
    {
        int row = tSpecialDrugAddictsMapper.deleteTSpecialDrugAddictsById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteTSpecialDrugAddictsById");
        return row;
    }
}
