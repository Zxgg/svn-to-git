package com.isstech.isstechadmin.mapper;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.TTaskPersonRelation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 人脸和人员对应关系任务 Mapper接口
 * 
 * @author yzp
 * @date 2020-12-17
 */
public interface TTaskPersonRelationMapper 
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
     * 删除人脸和人员对应关系任务 
     * 
     * @param peopleId 人脸和人员对应关系任务 ID
     * @return 结果
     */
    public int deleteTTaskPersonRelationById(String peopleId);

    /**
     * 批量删除人脸和人员对应关系任务 
     * 
     * @param peopleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTTaskPersonRelationByIds(String[] peopleIds);

    /**
     * 批量软删除人脸和人员对应关系任务 
     *
     * @param peopleIds 需要删除的数据ID
     * @return 结果
     */
    public int updateTTaskPersonRelationByIds(String[] peopleIds);

    @Select("select person_id personId, roster_type rosterType,source_type sourceType,community_child_id communityChildId ,community_id communityId from t_task_person_relation t where people_id = #{peopleId} and suspect_id =#{suspectId} and del_flag ='0'")
    public Map<String, String> queryPersonId(@Param("peopleId") String peopleId, @Param("suspectId") String suspectId);

    /**
     * 根据
     * @param peopleId 人员名单id
     * @param personId 人员id
     * @param repositorId 布控ID
     */
//    public int delectByIds(String peopleId, String personId, String repositorId);
}
