package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseVehicleCommunityHousesPersonnel;
import com.isstech.isstechadmin.domain.vo.VehComHousesPersonVo;
import com.isstech.isstechadmin.mapper.BaseVehicleCommunityHousesPersonnelMapper;
import com.isstech.isstechadmin.service.IBaseVehicleCommunityHousesPersonnelService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 车辆和社区房屋和人员关联 Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-30
 */
@Service
public class BaseVehicleCommunityHousesPersonnelServiceImpl implements IBaseVehicleCommunityHousesPersonnelService
{
    @Autowired
    private BaseVehicleCommunityHousesPersonnelMapper baseVehicleCommunityHousesPersonnelMapper;
    @Autowired
    private DataAccessUtils dataAccessUtils;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询车辆和社区房屋和人员关联 
     * 
     * @param id 车辆和社区房屋和人员关联 ID
     * @return 车辆和社区房屋和人员关联 
     */
    @Override
    public BaseVehicleCommunityHousesPersonnel selectBaseVehicleCommunityHousesPersonnelById(String id)
    {
        return baseVehicleCommunityHousesPersonnelMapper.selectBaseVehicleCommunityHousesPersonnelById(id);
    }

    /**
     * 查询车辆和社区房屋和人员关联 列表
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 车辆和社区房屋和人员关联 
     */
    @Override
    public List<BaseVehicleCommunityHousesPersonnel> selectBaseVehicleCommunityHousesPersonnelList(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        baseVehicleCommunityHousesPersonnel.setAllDataAccess(dataAccessUtils.isAllDataAccess());
        baseVehicleCommunityHousesPersonnel.setDataAccessList(dataAccessUtils.getCurrUserDataAccessDeptToList());
        return baseVehicleCommunityHousesPersonnelMapper.selectBaseVehicleCommunityHousesPersonnelList(baseVehicleCommunityHousesPersonnel);
    }

    /**
     * 查询车辆和社区房屋和人员关联 列表-人员名称 和 车辆信息补充
     * @param vehComHousesPersonVo  补充车辆信息
     * @return
     */
    @Override
    public List<VehComHousesPersonVo> selectVehComHousesPersonVoList(VehComHousesPersonVo vehComHousesPersonVo) {
        return baseVehicleCommunityHousesPersonnelMapper.selectVehComHousesPersonVoList(vehComHousesPersonVo);
    }

    /**
     * 新增车辆和社区房屋和人员关联 
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 结果
     */
    @Override
    public int insertBaseVehicleCommunityHousesPersonnel(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        baseVehicleCommunityHousesPersonnel.setCreateTime(DateUtils.getNowDate());
        baseVehicleCommunityHousesPersonnel.setCreateBy(SecurityUtils.getUsername());
        baseVehicleCommunityHousesPersonnel.setId(baseVehicleCommunityHousesPersonnelMapper.selectUuid());
        int row = baseVehicleCommunityHousesPersonnelMapper.insertBaseVehicleCommunityHousesPersonnel(baseVehicleCommunityHousesPersonnel);
        dataSynchronizationUtils.dataParsing(baseVehicleCommunityHousesPersonnel,"insertBaseVehicleCommunityHousesPersonnel");
        return row;
    }

    /**
     * 修改车辆和社区房屋和人员关联 
     * 
     * @param baseVehicleCommunityHousesPersonnel 车辆和社区房屋和人员关联 
     * @return 结果
     */
    @Override
    public int updateBaseVehicleCommunityHousesPersonnel(BaseVehicleCommunityHousesPersonnel baseVehicleCommunityHousesPersonnel)
    {
        baseVehicleCommunityHousesPersonnel.setUpdateTime(DateUtils.getNowDate());
        baseVehicleCommunityHousesPersonnel.setUpdateBy(SecurityUtils.getUsername());
        int row = baseVehicleCommunityHousesPersonnelMapper.updateBaseVehicleCommunityHousesPersonnel(baseVehicleCommunityHousesPersonnel);
        dataSynchronizationUtils.dataParsing(baseVehicleCommunityHousesPersonnel,"updateBaseVehicleCommunityHousesPersonnel");
        return row;
    }

    /**
     * 批量删除车辆和社区房屋和人员关联 
     * 
     * @param ids 需要删除的车辆和社区房屋和人员关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVehicleCommunityHousesPersonnelByIds(String[] ids)
    {
        int row = baseVehicleCommunityHousesPersonnelMapper.deleteBaseVehicleCommunityHousesPersonnelByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBaseVehicleCommunityHousesPersonnelByIds");
        return row;
    }
    /**
     * 批量软删除车辆和社区房屋和人员关联
     *
     * @param ids 需要删除的车辆和社区房屋和人员关联 ID
     * @return 结果
     */
    @Override
    public int updateBaseVehicleCommunityHousesPersonnelByIds(String[] ids)
    {
        int row = baseVehicleCommunityHousesPersonnelMapper.updateBaseVehicleCommunityHousesPersonnelByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBaseVehicleCommunityHousesPersonnelByIds");
        return row;
    }

    /**
     * 删除车辆和社区房屋和人员关联 信息
     * 
     * @param id 车辆和社区房屋和人员关联 ID
     * @return 结果
     */
    @Override
    public int deleteBaseVehicleCommunityHousesPersonnelById(String id)
    {
        int row = baseVehicleCommunityHousesPersonnelMapper.deleteBaseVehicleCommunityHousesPersonnelById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBaseVehicleCommunityHousesPersonnelById");
        return row;
    }
}
