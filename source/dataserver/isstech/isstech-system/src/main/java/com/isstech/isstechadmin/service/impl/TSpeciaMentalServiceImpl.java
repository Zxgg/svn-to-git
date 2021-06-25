package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TSpeciaMentalMapper;
import com.isstech.isstechadmin.domain.TSpeciaMental;
import com.isstech.isstechadmin.service.ITSpeciaMentalService;

/**
 * 易肇事肇祸精神病人 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Service
public class TSpeciaMentalServiceImpl implements ITSpeciaMentalService 
{
    @Autowired
    private TSpeciaMentalMapper tSpeciaMentalMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询易肇事肇祸精神病人 
     * 
     * @param id 易肇事肇祸精神病人 ID
     * @return 易肇事肇祸精神病人 
     */
    @Override
    public TSpeciaMental selectTSpeciaMentalById(String id)
    {
        return tSpeciaMentalMapper.selectTSpeciaMentalById(id);
    }

    /**
     * 查询易肇事肇祸精神病人 列表
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 易肇事肇祸精神病人 
     */
    @Override
    public List<TSpeciaMental> selectTSpeciaMentalList(TSpeciaMental tSpeciaMental)
    {
        return tSpeciaMentalMapper.selectTSpeciaMentalList(tSpeciaMental);
    }

    /**
     * 新增易肇事肇祸精神病人 
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 结果
     */
    @Override
    public int insertTSpeciaMental(TSpeciaMental tSpeciaMental)
    {
        tSpeciaMental.setId(tSpeciaMentalMapper.selectUuid());
        tSpeciaMental.setCreateBy(SecurityUtils.getUsername());
        tSpeciaMental.setCreateTime(DateUtils.getNowDate());
        tSpeciaMental.setDelFlag("0");
        int row = tSpeciaMentalMapper.insertTSpeciaMental(tSpeciaMental);
        dataSynchronizationUtils.dataParsing(tSpeciaMental,"insertTSpeciaMental");
        return row;
    }

    /**
     * 修改易肇事肇祸精神病人 
     * 
     * @param tSpeciaMental 易肇事肇祸精神病人 
     * @return 结果
     */
    @Override
    public int updateTSpeciaMental(TSpeciaMental tSpeciaMental)
    {
        tSpeciaMental.setUpdateBy(SecurityUtils.getUsername());
        tSpeciaMental.setUpdateTime(DateUtils.getNowDate());
        int row = tSpeciaMentalMapper.updateTSpeciaMental(tSpeciaMental);
        dataSynchronizationUtils.dataParsing(tSpeciaMental,"updateTSpeciaMental");
        return row;
    }

    /**
     * 批量删除易肇事肇祸精神病人 
     * 
     * @param ids 需要删除的易肇事肇祸精神病人 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaMentalByIds(String[] ids)
    {
        int row = tSpeciaMentalMapper.deleteTSpeciaMentalByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteTSpeciaMentalByIds");
        return row;
    }

    /**
     * 批量软删除易肇事肇祸精神病人 
     *
     * @param ids 需要删除的易肇事肇祸精神病人 ID
     * @return 结果
     */
    @Override
    public int updateTSpeciaMentalByIds(String[] ids)
    {
        int row = tSpeciaMentalMapper.updateTSpeciaMentalByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaMentalByIds");
        return row;
    }

    /**
     * 删除易肇事肇祸精神病人 信息
     * 
     * @param id 易肇事肇祸精神病人 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaMentalById(String id)
    {
        int row = tSpeciaMentalMapper.deleteTSpeciaMentalById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteTSpeciaMentalById");
        return row;
    }
}
