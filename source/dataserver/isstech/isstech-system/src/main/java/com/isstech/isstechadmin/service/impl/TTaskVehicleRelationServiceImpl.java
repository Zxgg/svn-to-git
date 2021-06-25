package com.isstech.isstechadmin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.TTaskVehicleRelationMapper;
import com.isstech.isstechadmin.domain.TTaskVehicleRelation;
import com.isstech.isstechadmin.service.ITTaskVehicleRelationService;

/**
 * 车辆对应关系任务 Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-24
 */
@Service
public class TTaskVehicleRelationServiceImpl implements ITTaskVehicleRelationService 
{
    @Autowired
    private TTaskVehicleRelationMapper tTaskVehicleRelationMapper;

    /**
     * 查询车辆对应关系任务 
     * 
     * @param plateId 车辆对应关系任务 ID
     * @return 车辆对应关系任务 
     */
    @Override
    public TTaskVehicleRelation selectTTaskVehicleRelationById(String plateId)
    {
        return tTaskVehicleRelationMapper.selectTTaskVehicleRelationById(plateId);
    }

    /**
     * 查询车辆对应关系任务 列表
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 车辆对应关系任务 
     */
    @Override
    public List<TTaskVehicleRelation> selectTTaskVehicleRelationList(TTaskVehicleRelation tTaskVehicleRelation)
    {
        return tTaskVehicleRelationMapper.selectTTaskVehicleRelationList(tTaskVehicleRelation);
    }

    /**
     * 新增车辆对应关系任务 
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 结果
     */
    @Override
    public int insertTTaskVehicleRelation(TTaskVehicleRelation tTaskVehicleRelation)
    {
        return tTaskVehicleRelationMapper.insertTTaskVehicleRelation(tTaskVehicleRelation);
    }

    /**
     * 修改车辆对应关系任务 
     * 
     * @param tTaskVehicleRelation 车辆对应关系任务 
     * @return 结果
     */
    @Override
    public int updateTTaskVehicleRelation(TTaskVehicleRelation tTaskVehicleRelation)
    {
        return tTaskVehicleRelationMapper.updateTTaskVehicleRelation(tTaskVehicleRelation);
    }

    /**
     * 批量删除车辆对应关系任务 
     * 
     * @param plateIds 需要删除的车辆对应关系任务 ID
     * @return 结果
     */
    @Override
    public int deleteTTaskVehicleRelationByIds(String[] plateIds)
    {
        return tTaskVehicleRelationMapper.deleteTTaskVehicleRelationByIds(plateIds);
    }

    /**
     * 删除车辆对应关系任务 信息
     * 
     * @param plateId 车辆对应关系任务 ID
     * @return 结果
     */
    @Override
    public int deleteTTaskVehicleRelationById(String plateId)
    {
        return tTaskVehicleRelationMapper.deleteTTaskVehicleRelationById(plateId);
    }

    @Override
    public Map<String, String> queryVehicleByPlate(String plate, String suspectId) {
        return tTaskVehicleRelationMapper.queryVehicleByPlate(plate,suspectId);
    }

    @Override
    public Map<String, String> queryVehicleByPlateId(String plateId, String suspectId) {
        return tTaskVehicleRelationMapper.queryVehicleByPlateId(plateId,suspectId);
    }
}
