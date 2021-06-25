package com.isstech.isstechadmin.service.impl;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.utils.DateUtils;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.common.utils.StringUtils;
import com.isstech.isstechadmin.domain.BaseVehicleInformation;
import com.isstech.isstechadmin.domain.query.QueryVehicleInformation;
import com.isstech.isstechadmin.domain.vo.VehicleInformationVo;
import com.isstech.isstechadmin.domain.vo.VehiclePersonHouseVo;
import com.isstech.isstechadmin.mapper.BaseVehicleInformationMapper;
import com.isstech.isstechadmin.service.IBaseVehicleInformationService;
import com.isstech.isstechadmin.utils.DataSynchronizationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 车辆信息Service业务层处理
 * 
 * @author isstech
 * @date 2020-11-23
 */
@Service
public class BaseVehicleInformationServiceImpl implements IBaseVehicleInformationService
{
    @Autowired
    private BaseVehicleInformationMapper baseVehicleInformationMapper;
    @Autowired
    private DataSynchronizationUtils dataSynchronizationUtils;

    /**
     * 查询车辆信息
     * 
     * @param id 车辆信息ID
     * @return 车辆信息
     */
    @Override
    public BaseVehicleInformation selectBaseVehicleInformationById(String id)
    {
        return baseVehicleInformationMapper.selectBaseVehicleInformationById(id);
    }

    /**
     * 查询车辆信息列表
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 车辆信息
     */
    @Override
    public List<BaseVehicleInformation> selectBaseVehicleInformationList(BaseVehicleInformation baseVehicleInformation)
    {
        return baseVehicleInformationMapper.selectBaseVehicleInformationList(baseVehicleInformation);
    }

    /**
     * 根据（人员ID、社区房屋ID、车牌号码、是否历史）查询所有关联的车辆信息
     *
     * @param vehicleInformationVo 车辆信息补充
     * @return 车辆信息补充集合
     */
    @Override
    public List<VehicleInformationVo> selectVehicleInformationVoList(VehicleInformationVo vehicleInformationVo)
    {
        return baseVehicleInformationMapper.selectVehicleInformationVoList(vehicleInformationVo);
    }

    /**
     * 新增车辆信息
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 结果
     */
    @Override
    public AjaxResult insertBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation)
    {
        BaseVehicleInformation information = baseVehicleInformationMapper.selectVehicleByPlate(baseVehicleInformation.getPlate());
        if(information != null){
            return AjaxResult.error("该车牌号已存在!!");
        }
        baseVehicleInformation.setCreateTime(DateUtils.getNowDate());
        baseVehicleInformation.setCreateBy(SecurityUtils.getUsername());
        String uuid = baseVehicleInformationMapper.selectUuid();
        baseVehicleInformation.setId(uuid);
        baseVehicleInformationMapper.insertBaseVehicleInformation(baseVehicleInformation);
        dataSynchronizationUtils.dataParsing(baseVehicleInformation,"insertBaseVehicleInformation");
        return AjaxResult.success("操作成功");
    }

    /**
     * 修改车辆信息
     * 
     * @param baseVehicleInformation 车辆信息
     * @return 结果
     */
    @Override
    public AjaxResult updateBaseVehicleInformation(BaseVehicleInformation baseVehicleInformation)
    {
        String id = baseVehicleInformation.getId();
        BaseVehicleInformation oldInformation = baseVehicleInformationMapper.selectBaseVehicleInformationById(id);
        String oldPlate = oldInformation.getPlate();
        if(!StringUtils.equals(oldPlate,baseVehicleInformation.getPlate())){
            BaseVehicleInformation information = baseVehicleInformationMapper.selectVehicleByPlate(baseVehicleInformation.getPlate());
            if(information != null){
                return AjaxResult.error("该车牌号已存在!!");
            }
        }

        baseVehicleInformation.setUpdateTime(DateUtils.getNowDate());
        baseVehicleInformation.setUpdateBy(SecurityUtils.getUsername());
        baseVehicleInformationMapper.updateBaseVehicleInformation(baseVehicleInformation);
        dataSynchronizationUtils.dataParsing(baseVehicleInformation,"updateBaseVehicleInformation");
        return AjaxResult.success("操作成功");
    }

    /**
     * 批量删除车辆信息
     * 
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVehicleInformationByIds(String[] ids)
    {
        int row = baseVehicleInformationMapper.deleteBaseVehicleInformationByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"deleteBaseVehicleInformationByIds");
        return row;
    }
    /**
     * 批量软删除车辆信息
     *
     * @param ids 需要删除的车辆信息ID
     * @return 结果
     */
    @Override
    public int updateBaseVehicleInformationByIds(String[] ids)
    {
        int row = baseVehicleInformationMapper.updateBaseVehicleInformationByIds(ids);
        dataSynchronizationUtils.dataParsing(ids,"updateBaseVehicleInformationByIds");
        return row;
    }

    /**
     * 删除车辆信息信息
     * 
     * @param id 车辆信息ID
     * @return 结果
     */
    @Override
    public int deleteBaseVehicleInformationById(String id)
    {
        int row = baseVehicleInformationMapper.deleteBaseVehicleInformationById(id);
        dataSynchronizationUtils.dataParsing(id,"deleteBaseVehicleInformationById");
        return row;
    }

    /**
     * 根据车牌号查询车辆信息
     *
     * @param lpn
     * @return
     */
    @Override
    public BaseVehicleInformation selectVehicleByPlate(String lpn) {
        return baseVehicleInformationMapper.selectVehicleByPlate(lpn);
    }

    /**
     *  参数：车牌、车主姓名、车主身份证号码
     *  返回值：车辆基本信息+车主基本信息
     *  selectVehicleAndPerson
     * */
    @Override
    public List<Map<String,Object>> selectVehicleAndPerson(QueryVehicleInformation queryVehicleInformation){
        return baseVehicleInformationMapper.selectVehicleAndPerson(queryVehicleInformation);
    }

    /**
     *  查询车人房联合信息
     *  selectVehicleAndPerson
     * */
    public List<VehiclePersonHouseVo> getVehiclePersonHouses(QueryVehicleInformation queryVehicleInformation){
        return baseVehicleInformationMapper.getVehiclePersonHouses(queryVehicleInformation);
    }
}
