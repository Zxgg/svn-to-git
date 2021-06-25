package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePassVehicle;
import com.isstech.isstechadmin.domain.vo.PassVehicleVo;

/**
 * 车辆通行Mapper接口
 * 
 * @author yzp
 * @date 2020-12-12
 */
public interface BasePassVehicleMapper 
{
    /**
     * 查询车辆通行
     * 
     * @param motorVehicleId 车辆通行ID
     * @return 车辆通行
     */
    public BasePassVehicle selectBasePassVehicleById(String motorVehicleId);

    /**
     * 查询车辆通行列表
     * 
     * @param passVehicleVo 车辆通行
     * @return 车辆通行集合
     */
    public List<BasePassVehicle> selectBasePassVehicleList(PassVehicleVo passVehicleVo);

    /**
     * 新增车辆通行
     * 
     * @param basePassVehicle 车辆通行
     * @return 结果
     */
    public int insertBasePassVehicle(BasePassVehicle basePassVehicle);

    /**
     * 修改车辆通行
     * 
     * @param basePassVehicle 车辆通行
     * @return 结果
     */
    public int updateBasePassVehicle(BasePassVehicle basePassVehicle);

    /**
     * 删除车辆通行
     * 
     * @param motorVehicleId 车辆通行ID
     * @return 结果
     */
    public int deleteBasePassVehicleById(String motorVehicleId);

    /**
     * 批量删除车辆通行
     * 
     * @param motorVehicleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePassVehicleByIds(String[] motorVehicleIds);
    /**
     * 批量软删除车辆通行
     *
     * @param motorVehicleIds 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePassVehicleByIds(String[] motorVehicleIds);
}
