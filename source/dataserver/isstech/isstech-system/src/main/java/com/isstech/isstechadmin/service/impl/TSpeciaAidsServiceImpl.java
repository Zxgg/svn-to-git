package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.TSpeciaAids;
import com.isstech.isstechadmin.mapper.TSpeciaAidsMapper;
import com.isstech.isstechadmin.service.ITSpeciaAidsService;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 艾滋病人 Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-08
 */
@Service
public class TSpeciaAidsServiceImpl implements ITSpeciaAidsService
{
    @Autowired
    private TSpeciaAidsMapper tSpeciaAidsMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询艾滋病人 
     * 
     * @param id 艾滋病人 ID
     * @return 艾滋病人 
     */
    @Override
    public TSpeciaAids selectTSpeciaAidsById(String id)
    {
        return tSpeciaAidsMapper.selectTSpeciaAidsById(id);
    }

    /**
     * 查询艾滋病人 列表
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 艾滋病人 
     */
    @Override
    public List<TSpeciaAids> selectTSpeciaAidsList(TSpeciaAids tSpeciaAids)
    {
        return tSpeciaAidsMapper.selectTSpeciaAidsList(tSpeciaAids);
    }

    /**
     * 新增艾滋病人 
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 结果
     */
    @Override
    public int insertTSpeciaAids(TSpeciaAids tSpeciaAids)
    {
        tSpeciaAids.setId(tSpeciaAidsMapper.selectUuid());
        tSpeciaAids.setCreateBy(SecurityUtils.getUsername());
        tSpeciaAids.setCreateTime(DateUtils.getNowDate());
        tSpeciaAids.setDelFlag("0");
        int row = tSpeciaAidsMapper.insertTSpeciaAids(tSpeciaAids);
        dataSynchronizationUtils.dataParsing(tSpeciaAids,"insertTSpeciaAids");
        return row;
    }

    /**
     * 修改艾滋病人 
     * 
     * @param tSpeciaAids 艾滋病人 
     * @return 结果
     */
    @Override
    public int updateTSpeciaAids(TSpeciaAids tSpeciaAids)
    {
        tSpeciaAids.setUpdateBy(SecurityUtils.getUsername());
        tSpeciaAids.setUpdateTime(DateUtils.getNowDate());
        int row = tSpeciaAidsMapper.updateTSpeciaAids(tSpeciaAids);
        dataSynchronizationUtils.dataParsing(tSpeciaAids,"updateTSpeciaAids");
        return row;
    }

    /**
     * 批量删除艾滋病人 
     * 
     * @param ids 需要删除的艾滋病人 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaAidsByIds(String[] ids)
    {
        int row = tSpeciaAidsMapper.deleteTSpeciaAidsByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteTSpeciaAidsByIds");
        return row;
    }
    /**
     * 批量软删除艾滋病人
     *
     * @param ids 需要删除的艾滋病人 ID
     * @return 结果
     */
    @Override
    public int updateTSpeciaAidsByIds(String[] ids)
    {
        int row = tSpeciaAidsMapper.updateTSpeciaAidsByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateTSpeciaAidsByIds");
        return row;
    }

    /**
     * 删除艾滋病人 信息
     * 
     * @param id 艾滋病人 ID
     * @return 结果
     */
    @Override
    public int deleteTSpeciaAidsById(String id)
    {
        int row = tSpeciaAidsMapper.deleteTSpeciaAidsById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteTSpeciaAidsById");
        return row;
    }
}
