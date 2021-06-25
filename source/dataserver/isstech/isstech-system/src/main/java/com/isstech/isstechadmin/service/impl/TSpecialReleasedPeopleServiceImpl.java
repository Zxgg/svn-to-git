package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TSpecialReleasedPeopleMapper;
import com.isstech.isstechadmin.domain.TSpecialReleasedPeople;
import com.isstech.isstechadmin.service.ITSpecialReleasedPeopleService;

/**
 * 刑满释放人员 Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-22
 */
@Service
public class TSpecialReleasedPeopleServiceImpl implements ITSpecialReleasedPeopleService 
{
    @Autowired
    private TSpecialReleasedPeopleMapper tSpecialReleasedPeopleMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询刑满释放人员 
     * 
     * @param id 刑满释放人员 ID
     * @return 刑满释放人员 
     */
    @Override
    public TSpecialReleasedPeople selectTSpecialReleasedPeopleById(String id)
    {
        return tSpecialReleasedPeopleMapper.selectTSpecialReleasedPeopleById(id);
    }

    /**
     * 查询刑满释放人员 列表
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 刑满释放人员 
     */
    @Override
    public List<TSpecialReleasedPeople> selectTSpecialReleasedPeopleList(TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        return tSpecialReleasedPeopleMapper.selectTSpecialReleasedPeopleList(tSpecialReleasedPeople);
    }

    /**
     * 新增刑满释放人员 
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 结果
     */
    @Override
    public int insertTSpecialReleasedPeople(TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        tSpecialReleasedPeople.setId(tSpecialReleasedPeopleMapper.selectUuid());
        tSpecialReleasedPeople.setCreateBy(SecurityUtils.getUsername());
        tSpecialReleasedPeople.setCreateTime(DateUtils.getNowDate());
        tSpecialReleasedPeople.setDelFlag("0");
        int row = tSpecialReleasedPeopleMapper.insertTSpecialReleasedPeople(tSpecialReleasedPeople);
        dataSynchronizationUtils.dataParsing(tSpecialReleasedPeople,"insertTSpecialReleasedPeople");
        return row;
    }

    /**
     * 修改刑满释放人员 
     * 
     * @param tSpecialReleasedPeople 刑满释放人员 
     * @return 结果
     */
    @Override
    public int updateTSpecialReleasedPeople(TSpecialReleasedPeople tSpecialReleasedPeople)
    {
        tSpecialReleasedPeople.setUpdateBy(SecurityUtils.getUsername());
        tSpecialReleasedPeople.setUpdateTime(DateUtils.getNowDate());
        int row = tSpecialReleasedPeopleMapper.updateTSpecialReleasedPeople(tSpecialReleasedPeople);
        dataSynchronizationUtils.dataParsing(tSpecialReleasedPeople,"updateTSpecialReleasedPeople");
        return row;
    }

    /**
     * 批量删除刑满释放人员 
     * 
     * @param ids 需要删除的刑满释放人员 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialReleasedPeopleByIds(String[] ids)
    {
        int row = tSpecialReleasedPeopleMapper.deleteTSpecialReleasedPeopleByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteTSpecialReleasedPeopleByIds");
        return row;
    }

    /**
     * 批量软删除刑满释放人员 
     *
     * @param ids 需要删除的刑满释放人员 ID
     * @return 结果
     */
    @Override
    public int updateTSpecialReleasedPeopleByIds(String[] ids)
    {
        int row = tSpecialReleasedPeopleMapper.updateTSpecialReleasedPeopleByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateTSpecialReleasedPeopleByIds");
        return row;
    }

    /**
     * 删除刑满释放人员 信息
     * 
     * @param id 刑满释放人员 ID
     * @return 结果
     */
    @Override
    public int deleteTSpecialReleasedPeopleById(String id)
    {
        int row = tSpecialReleasedPeopleMapper.deleteTSpecialReleasedPeopleById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteTSpecialReleasedPeopleById");
        return row;
    }
}
