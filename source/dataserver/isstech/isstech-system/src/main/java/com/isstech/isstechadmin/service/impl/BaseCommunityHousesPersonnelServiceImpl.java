package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseCommunityHousesPersonnel;
import com.isstech.isstechadmin.mapper.BaseCommunityHousesPersonnelMapper;
import com.isstech.isstechadmin.service.IBaseCommunityHousesPersonnelService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 社区房屋和人员关联Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Service
public class BaseCommunityHousesPersonnelServiceImpl implements IBaseCommunityHousesPersonnelService
{
    @Autowired
    private BaseCommunityHousesPersonnelMapper baseCommunityHousesPersonnelMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询社区房屋和人员关联
     * 
     * @param id 社区房屋和人员关联ID
     * @return 社区房屋和人员关联
     */
    @Override
    public BaseCommunityHousesPersonnel selectBaseCommunityHousesPersonnelById(String id)
    {
        return baseCommunityHousesPersonnelMapper.selectBaseCommunityHousesPersonnelById(id);
    }

    /**
     * 查询社区房屋和人员关联列表
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 社区房屋和人员关联
     */
    @Override
    public List<BaseCommunityHousesPersonnel> selectBaseCommunityHousesPersonnelList(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        return baseCommunityHousesPersonnelMapper.selectBaseCommunityHousesPersonnelList(baseCommunityHousesPersonnel);
    }

    /**
     * 新增社区房屋和人员关联
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 结果
     */
    @Override
    public int insertBaseCommunityHousesPersonnel(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        BaseCommunityHousesPersonnel baseCommunityHousesPersonnel1 = baseCommunityHousesPersonnelMapper.queryByHousesIdAndPerId(baseCommunityHousesPersonnel);
        baseCommunityHousesPersonnel.setCreateTime(DateUtils.getNowDate());
        baseCommunityHousesPersonnel.setCreateBy(SecurityUtils.getUsername());
        if(baseCommunityHousesPersonnel1 == null || StringUtils.isNull(baseCommunityHousesPersonnel1)){
            baseCommunityHousesPersonnel.setId(baseCommunityHousesPersonnelMapper.selectUuid());
            int row = baseCommunityHousesPersonnelMapper.insertBaseCommunityHousesPersonnel(baseCommunityHousesPersonnel);
            dataSynchronizationUtils.dataParsing(baseCommunityHousesPersonnel,"insertBaseCommunityHousesPersonnel");
            return row;
        }
        baseCommunityHousesPersonnel.setId(baseCommunityHousesPersonnel1.getId());
        return updateBaseCommunityHousesPersonnel(baseCommunityHousesPersonnel);
    }

    /**
     * 修改社区房屋和人员关联
     * 
     * @param baseCommunityHousesPersonnel 社区房屋和人员关联
     * @return 结果
     */
    @Override
    public int updateBaseCommunityHousesPersonnel(BaseCommunityHousesPersonnel baseCommunityHousesPersonnel)
    {
        baseCommunityHousesPersonnel.setUpdateTime(DateUtils.getNowDate());
        baseCommunityHousesPersonnel.setUpdateBy(SecurityUtils.getUsername());
        int row = baseCommunityHousesPersonnelMapper.updateBaseCommunityHousesPersonnel(baseCommunityHousesPersonnel);
        dataSynchronizationUtils.dataParsing(baseCommunityHousesPersonnel,"updateBaseCommunityHousesPersonnel");
        return row;
    }

    /**
     * 批量删除社区房屋和人员关联
     * 
     * @param ids 需要删除的社区房屋和人员关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityHousesPersonnelByIds(String[] ids)
    {
        int row = baseCommunityHousesPersonnelMapper.deleteBaseCommunityHousesPersonnelByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBaseCommunityHousesPersonnelByIds");
        return row;
    }

    /**
     * 批量软删除社区房屋和人员关联
     *
     * @param ids 需要删除的社区房屋和人员关联ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityHousesPersonnelByIds(String[] ids)
    {
        int row = baseCommunityHousesPersonnelMapper.updateBaseCommunityHousesPersonnelByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBaseCommunityHousesPersonnelByIds");
        return row;
    }

    /**
     * 删除社区房屋和人员关联信息
     * 
     * @param id 社区房屋和人员关联ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityHousesPersonnelById(String id)
    {
        int row = baseCommunityHousesPersonnelMapper.deleteBaseCommunityHousesPersonnelById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBaseCommunityHousesPersonnelById");
        return row;
    }
}
