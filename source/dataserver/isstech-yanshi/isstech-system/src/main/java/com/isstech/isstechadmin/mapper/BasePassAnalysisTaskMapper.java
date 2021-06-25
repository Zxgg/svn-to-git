package com.isstech.isstechadmin.mapper;

import java.util.List;
import java.util.Map;

import com.isstech.isstechadmin.domain.BasePassAnalysisTask;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的Mapper接口
 * 
 * @author yzp
 * @date 2020-12-18
 */
public interface BasePassAnalysisTaskMapper 
{
    /**
     * 查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param id 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    public BasePassAnalysisTask selectBasePassAnalysisTaskById(String id);

    /**
     * 查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的集合
     */
    public List<BasePassAnalysisTask> selectBasePassAnalysisTaskList(BasePassAnalysisTask basePassAnalysisTask);

    /**
     * 新增社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 结果
     */
    public int insertBasePassAnalysisTask(BasePassAnalysisTask basePassAnalysisTask);

    /**
     * 修改社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 结果
     */
    public int updateBasePassAnalysisTask(BasePassAnalysisTask basePassAnalysisTask);

    /**
     * 删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param id 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    public int deleteBasePassAnalysisTaskById(String id);

    /**
     * 批量删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasePassAnalysisTaskByIds(String[] ids);

    /**
     * 批量软删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBasePassAnalysisTaskByIds(String[] ids);

    public int deleteBySuspectId(String[] suspectId);

    /**
     *  获取 布控 suspectId
     * @return list
     */
    @Select(" select bpat.suspect_id from base_pass_analysis_task bpat where del_flag = '0' ")
    public List<String> querySuspectIds();
    /**
     *  获取 布控人员黑名单 suspectId
     * @return list
     */
    @Select("select bpat.suspect_id from base_pass_analysis_task bpat where del_flag = '0' and bpat.type = '2' and bpat.hit_type = '1' ")
    public List<String> querySuspectIdsByRoster01();

    /**
     *  获取 布控人员白名单 suspectId
     * @return list
     */
    @Select("select bpat.suspect_id from base_pass_analysis_task bpat where del_flag = '0' and bpat.type = '3' and bpat.hit_type = '1' ")
    public List<String> querySuspectIdsByRoster02();
    /**
     *  获取 布控人员白名单非命中 == 陌生人 suspectId
     * @return list
     */
    @Select("select bpat.suspect_id from base_pass_analysis_task bpat where del_flag = '0' and bpat.type = '3' and bpat.hit_type = '2' ")
    public List<String> querySuspectIdsByRoster04();
    /**
     *  获取 布控车辆黑名单 suspectId
     * @return list
     */
    @Select("select suspect_id suspectId,select_groups  groupIds from base_pass_analysis_task  where del_flag = '0' and type = '1' and hit_type = '1' ")
    public List<Map<String , String>> querySuspectIdsByRoster03();

    /**
     * 根据布控id查询任务列表
     * @param suspectId
     * @return
     */
    @Select("select * from base_pass_analysis_task bpat where del_flag = '0' and  bpat.suspect_id = #{suspectId} ")
    public BasePassAnalysisTask queryEntityBySuspectId(String suspectId);

    @Select("update base_pass_analysis_task bpat set enable = #{enable} where suspect_id = #{susid} ")
    public void updateEnableBySuspectId(@Param("susid") String susid, @Param("enable") String enable);
}
