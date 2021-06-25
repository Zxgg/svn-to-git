package com.isstech.isstechadmin.service;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.TTaskVehicleRelation;

/**
 * 车辆对应关系任务 Service接口
 * 
 * @author yzp
 * @date 2020-12-24
 */
public interface ITTaskVehicleRelationService 
{
    /**
     * 查询车辆对应关系任务 
     * 
     * @param plateId 车辆对应关系任务 ID
     * @return 车辆对应关系任务 
     */
    public TTaskVehicleRelation selectTTaskVehicleRelationById(String plateId);

    /**
     * 查询车辆对应关系任务 列表
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 车辆对应关系任务 集合
     */
    public List<TTaskVehicleRelation> selectTTaskVehicleRelationList(TTaskVehicleRelation tTaskVehicleRelation);

    /**
     * 新增车辆对应关系任务 
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 结果
     */
    public int insertTTaskVehicleRelation(TTaskVehicleRelation tTaskVehicleRelation);

    /**
     * 修改车辆对应关系任务 
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 结果
     */
    public int updateTTaskVehicleRelation(TTaskVehicleRelation tTaskVehicleRelation);

    /**
     * 批量删除车辆对应关系任务 
     * 
     * @param plateIds 需要删除的车辆对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskVehicleRelationByIds(String[] plateIds);

    /**
     * 删除车辆对应关系任务 信息
     * 
     * @param plateId 车辆对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskVehicleRelationById(String plateId);

    public Map<String, String> queryVehicleByPlate(String plate, String suspectId);

    public Map<String, String> queryVehicleByPlateId(String blacklistId, String suspectId);
}
