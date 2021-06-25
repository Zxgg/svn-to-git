package com.isstech.isstechadmin.mapper;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.TTaskVehicleRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 车辆对应关系任务 Mapper接口
 * 
 * @author yzp
 * @date 2020-12-24
 */
public interface TTaskVehicleRelationMapper 
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
     * 删除车辆对应关系任务 
     * 
     * @param plateId 车辆对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskVehicleRelationById(String plateId);

    /**
     * 批量删除车辆对应关系任务 
     * 
     * @param plateIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTaskVehicleRelationByIds(String[] plateIds);

    @Select("select vehicle_id vehicleId,source_type sourceType,roster_type rosterType,community_child_id communityChildId ,community_id communityId from t_task_vehicle_relation ttvr where plate =#{plate} and suspect_id = #{suspectId}")
    public Map<String, String> queryVehicleByPlate(@Param("plate")String plate, @Param("suspectId")String suspectId);

    @Select("select vehicle_id vehicleId,source_type sourceType,roster_type rosterType,community_child_id communityChildId ,community_id communityId from t_task_vehicle_relation ttvr where plate_id =#{plateId} and suspect_id = #{suspectId}")
    public Map<String, String> queryVehicleByPlateId(@Param("plateId")String plateId, @Param("suspectId")String suspectId);
}
