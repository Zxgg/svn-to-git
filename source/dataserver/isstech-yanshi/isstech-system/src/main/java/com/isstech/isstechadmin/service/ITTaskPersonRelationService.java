package com.isstech.isstechadmin.service;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.TTaskPersonRelation;

/**
 * 人脸和人员对应关系任务 Service接口
 * 
 * @author yzp
 * @date 2020-12-17
 */
public interface ITTaskPersonRelationService 
{
    /**
     * 查询人脸和人员对应关系任务 
     * 
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 人脸和人员对应关系任务 
     */
    public TTaskPersonRelation selectTTaskPersonRelationById(String peopleId);

    /**
     * 查询人脸和人员对应关系任务 列表
     * 
     * @param tTaskPersonRelation 人脸和人员对应关系任务 
     * @return 人脸和人员对应关系任务 集合
     */
    public List<TTaskPersonRelation> selectTTaskPersonRelationList(TTaskPersonRelation tTaskPersonRelation);

    /**
     * 新增人脸和人员对应关系任务 
     * 
     * @param tTaskPersonRelation 人脸和人员对应关系任务 
     * @return 结果
     */
    public int insertTTaskPersonRelation(TTaskPersonRelation tTaskPersonRelation);

    /**
     * 修改人脸和人员对应关系任务 
     * 
     * @param tTaskPersonRelation 人脸和人员对应关系任务 
     * @return 结果
     */
    public int updateTTaskPersonRelation(TTaskPersonRelation tTaskPersonRelation);

    /**
     * 批量删除人脸和人员对应关系任务 
     * 
     * @param peopleIds 需要删除的人脸和人员对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskPersonRelationByIds(String[] peopleIds);

    /**
     * 删除人脸和人员对应关系任务 信息
     * 
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskPersonRelationById(String peopleId);

    /**
     * 软删除人脸和人员对应关系任务 信息
     *
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 结果
     */
    public int updateTTaskPersonRelationByIds(String[] peopleId);

    public Map<String,String> queryPersonId(String faceId, String suspectId);
}
