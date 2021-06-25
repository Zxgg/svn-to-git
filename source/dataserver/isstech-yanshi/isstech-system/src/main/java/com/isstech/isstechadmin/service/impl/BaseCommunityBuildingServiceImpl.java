package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseCommunityBuilding;
import com.isstech.isstechadmin.mapper.BaseCommunityBuildingMapper;
import com.isstech.isstechadmin.service.IBaseCommunityBuildingService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 社区楼栋Service业务层处理
 * @Author yzp
 * @Date 2020/11/18 14:33
 */
@Service
public class BaseCommunityBuildingServiceImpl implements IBaseCommunityBuildingService
{
    @Autowired
    private BaseCommunityBuildingMapper baseCommunityBuildingMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询社区楼栋
     * 
     * @param id 社区楼栋ID
     * @return 社区楼栋
     */
    @Override
    public BaseCommunityBuilding selectBaseCommunityBuildingById(String id)
    {
        return baseCommunityBuildingMapper.selectBaseCommunityBuildingById(id);
    }

    /**
     * 查询社区楼栋列表
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 社区楼栋
     */
    @Override
    public List<BaseCommunityBuilding> selectBaseCommunityBuildingList(BaseCommunityBuilding baseCommunityBuilding)
    {
        return baseCommunityBuildingMapper.selectBaseCommunityBuildingList(baseCommunityBuilding);
    }

    /**
     * 新增社区楼栋
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 结果
     */
    @Override
    public int insertBaseCommunityBuilding(BaseCommunityBuilding baseCommunityBuilding)
    {
        baseCommunityBuilding.setCreateTime(DateUtils.getNowDate());
        baseCommunityBuilding.setCreateBy(SecurityUtils.getUsername());
        baseCommunityBuilding.setId(baseCommunityBuildingMapper.selectUuid());
        int row = baseCommunityBuildingMapper.insertBaseCommunityBuilding(baseCommunityBuilding);
        dataSynchronizationUtils.dataParsing(baseCommunityBuilding,"insertBaseCommunityBuilding");
        return row;
    }

    /**
     * 修改社区楼栋
     * 
     * @param baseCommunityBuilding 社区楼栋
     * @return 结果
     */
    @Override
    public int updateBaseCommunityBuilding(BaseCommunityBuilding baseCommunityBuilding)
    {
        baseCommunityBuilding.setUpdateTime(DateUtils.getNowDate());
        baseCommunityBuilding.setUpdateBy(SecurityUtils.getUsername());
        int row = baseCommunityBuildingMapper.updateBaseCommunityBuilding(baseCommunityBuilding);
        dataSynchronizationUtils.dataParsing(baseCommunityBuilding,"updateBaseCommunityBuilding");
        return row;
    }

    /**
     * 批量删除社区楼栋
     * 
     * @param ids 需要删除的社区楼栋ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityBuildingByIds(String[] ids)
    {
        int row = baseCommunityBuildingMapper.deleteBaseCommunityBuildingByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBaseCommunityBuildingByIds");
        return row;
    }
    /**
     * 批量软删除社区楼栋
     *
     * @param ids 需要删除的社区楼栋ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityBuildingByIds(String[] ids) {
        int row = baseCommunityBuildingMapper.updateBaseCommunityBuildingByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBaseCommunityBuildingByIds");
        return row;
    }

    /**
     * 删除社区楼栋信息
     * 
     * @param id 社区楼栋ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityBuildingById(String id)
    {
        int row = baseCommunityBuildingMapper.deleteBaseCommunityBuildingById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBaseCommunityBuildingById");
        return row;
    }
}
