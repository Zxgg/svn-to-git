package com.isstech.isstechadmin.service.impl;

import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.domain.BaseCommunityHouses;
import com.isstech.isstechadmin.domain.query.QueryCommunityHouses;
import com.isstech.isstechadmin.domain.vo.CommunityHousesVo;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.mapper.BaseCommunityHousesMapper;
import com.isstech.isstechadmin.mapper.BaseVehicleInformationMapper;
import com.isstech.isstechadmin.service.IBaseCommunityHousesService;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description 社区房屋信息Service业务层处理
 * @Author yzp
 * @Date 2020/11/18 14:32
 */
@Service
public class BaseCommunityHousesServiceImpl implements IBaseCommunityHousesService
{
    @Autowired
    private BaseCommunityHousesMapper baseCommunityHousesMapper;

    @Autowired
    private BaseVehicleInformationMapper baseVehicleInformationMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询社区房屋信息
     * 
     * @param id 社区房屋信息ID
     * @return 社区房屋信息
     */
    @Override
    public BaseCommunityHouses selectBaseCommunityHousesById(String id)
    {
        return baseCommunityHousesMapper.selectBaseCommunityHousesById(id);
    }

    /**
     * 查询社区房屋信息列表
     * 
     * @param communityHousesVo 社区房屋信息
     * @return 社区房屋信息
     */
    @Override
    public List<BaseCommunityHouses> selectBaseCommunityHousesList(CommunityHousesVo communityHousesVo)
    {
        return baseCommunityHousesMapper.selectBaseCommunityHousesList(communityHousesVo);
    }

    /**
     * 根据（人员ID、是否历史-delFlag）查询所有关联的房屋
     *
     * @param communityHousesVo 社区房屋补充信息
     * @return 社区房屋信息集合
     */
    @Override
    public List<CommunityHousesVo> queryCommunityHousesVoList(CommunityHousesVo communityHousesVo) {
        return baseCommunityHousesMapper.queryCommunityHousesVoList(communityHousesVo);
    }

    /**
     * 新增社区房屋信息
     * 
     * @param baseCommunityHouses 社区房屋信息
     * @return 结果
     */
    @Override
    public String  insertBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses)
    {
        baseCommunityHouses.setCreateTime(DateUtils.getNowDate());
        baseCommunityHouses.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseCommunityHousesMapper.selectUuid();
        baseCommunityHouses.setId(uuid);
        baseCommunityHousesMapper.insertBaseCommunityHouses(baseCommunityHouses);
        dataSynchronizationUtils.dataParsing(baseCommunityHouses,"insertBaseCommunityHouses");
        return uuid;
    }

    /**
     * 修改社区房屋信息
     * 
     * @param baseCommunityHouses 社区房屋信息
     * @return 结果
     */
    @Override
    public int updateBaseCommunityHouses(BaseCommunityHouses baseCommunityHouses)
    {
        baseCommunityHouses.setUpdateTime(DateUtils.getNowDate());
        baseCommunityHouses.setUpdateBy(SecurityUtils.getUsername());
        int row = baseCommunityHousesMapper.updateBaseCommunityHouses(baseCommunityHouses);
        dataSynchronizationUtils.dataParsing(baseCommunityHouses,"updateBaseCommunityHouses");
        return row;
    }

    /**
     * 批量删除社区房屋信息
     * 
     * @param ids 需要删除的社区房屋信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityHousesByIds(String[] ids)
    {
        int row = baseCommunityHousesMapper.deleteBaseCommunityHousesByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBaseCommunityHousesByIds");
        return row;
    }
    /**
     * 批量软删除社区房屋信息
     *
     * @param ids 需要删除的社区房屋信息ID
     * @return 结果
     */
    @Override
    public int updateBaseCommunityHousesByIds(String[] ids)
    {
        int row = baseCommunityHousesMapper.updateBaseCommunityHousesByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBaseCommunityHousesByIds");
        return row;
    }

    /**
     * 删除社区房屋信息信息
     * 
     * @param id 社区房屋信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseCommunityHousesById(String id)
    {
        int row = baseCommunityHousesMapper.deleteBaseCommunityHousesById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBaseCommunityHousesById");
        return row;
    }

    @Override
    public List<BaseCommunityHouses> getByVehicleInfo(VehicleInformationVo information) {
        List<VehicleInformationVo> vehicleList = baseVehicleInformationMapper.selectVehicleInformationVoList(information);
        List<String> idList = vehicleList.stream().map(VehicleInformationVo::getCommunityHousesId).collect(Collectors.toList());
        return baseCommunityHousesMapper.selectByIdList(idList);
    }
    @Override
    public List<Map<String,Object>> queryHouseAndPersonList(QueryCommunityHouses queryCommunityHouses){
        return baseCommunityHousesMapper.queryHouseAndPersonList(queryCommunityHouses);
    }

}
