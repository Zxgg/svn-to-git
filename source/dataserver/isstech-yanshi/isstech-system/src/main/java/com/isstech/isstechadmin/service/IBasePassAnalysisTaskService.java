package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BasePassAnalysisTask;

/**
 * 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的Service接口
 * 
 * @author yzp
 * @date 2020-12-18
 */
public interface IBasePassAnalysisTaskService 
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
     * 批量删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param ids 需要删除的社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    public int deleteBasePassAnalysisTaskByIds(String[] ids);

    /**
     * 删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的信息
     * 
     * @param id 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    public int deleteBasePassAnalysisTaskById(String id);

    /**
     * 软删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的信息
     *
     * @param ids 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    public int updateBasePassAnalysisTaskByIds(String[] ids);

    /**
     * 根据布控ID查询布控任务数据
     * @param suspectId
     * @return
     */
    public BasePassAnalysisTask queryEntityBySuspectId(String suspectId);

    public int deleteBySuspectId(String[] suspectIds);

    public void updateEnableBySuspectId(String susid, String enable) throws Exception;
}
