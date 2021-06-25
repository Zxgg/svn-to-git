package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.service.IBasePassAnalysisTaskService;
import com.isstech.isstechadmin.utils.DataAccessUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BasePassAnalysisTaskMapper;
import com.isstech.isstechadmin.domain.BasePassAnalysisTask;

/**
 * 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的Service业务层处理
 * 
 * @author yzp
 * @date 2020-12-18
 */
@Service
public class BasePassAnalysisTaskServiceImpl implements IBasePassAnalysisTaskService
{
    @Autowired
    private BasePassAnalysisTaskMapper basePassAnalysisTaskMapper;


    /**
     * 查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param id 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @Override
    public BasePassAnalysisTask selectBasePassAnalysisTaskById(String id)
    {
        return basePassAnalysisTaskMapper.selectBasePassAnalysisTaskById(id);
    }

    /**
     * 查询社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的列表
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     */
    @Override
    public List<BasePassAnalysisTask> selectBasePassAnalysisTaskList(BasePassAnalysisTask basePassAnalysisTask)
    {
        return basePassAnalysisTaskMapper.selectBasePassAnalysisTaskList(basePassAnalysisTask);
    }

    /**
     * 新增社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 结果
     */
    @Override
    public int insertBasePassAnalysisTask(BasePassAnalysisTask basePassAnalysisTask)
    {
        basePassAnalysisTask.setCreateBy(SecurityUtils.getUsername());
        basePassAnalysisTask.setCreateTime(DateUtils.getNowDate());
        return basePassAnalysisTaskMapper.insertBasePassAnalysisTask(basePassAnalysisTask);
    }

    /**
     * 修改社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param basePassAnalysisTask 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * @return 结果
     */
    @Override
    public int updateBasePassAnalysisTask(BasePassAnalysisTask basePassAnalysisTask)
    {
        basePassAnalysisTask.setUpdateBy(SecurityUtils.getUsername());
        basePassAnalysisTask.setUpdateTime(DateUtils.getNowDate());
        return basePassAnalysisTaskMapper.updateBasePassAnalysisTask(basePassAnalysisTask);
    }

    /**
     * 批量删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     * 
     * @param ids 需要删除的社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    @Override
    public int deleteBasePassAnalysisTaskByIds(String[] ids)
    {
        return basePassAnalysisTaskMapper.deleteBasePassAnalysisTaskByIds(ids);
    }

    /**
     * 批量软删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的
     *
     * @param ids 需要删除的社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    @Override
    public int updateBasePassAnalysisTaskByIds(String[] ids)
    {
        return basePassAnalysisTaskMapper.updateBasePassAnalysisTaskByIds(ids);
    }

    /**
     * 根据布控ID查询布控任务数据
     *
     * @param suspectId
     * @return
     */
    @Override
    public BasePassAnalysisTask queryEntityBySuspectId(String suspectId) {
        return basePassAnalysisTaskMapper.queryEntityBySuspectId(suspectId);
    }

    @Override
    public int deleteBySuspectId(String[] suspectIds) {
        return basePassAnalysisTaskMapper.deleteBySuspectId(suspectIds);
    }

    @Override
    public void updateEnableBySuspectId(String susid, String enable) {
        basePassAnalysisTaskMapper.updateEnableBySuspectId(susid,enable);
    }

    /**
     * 删除社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的信息
     * 
     * @param id 社区通行类任务，后期接收人员通行通知时，以此来区分收到的白名单命中通知时属于哪个社区/小区的ID
     * @return 结果
     */
    @Override
    public int deleteBasePassAnalysisTaskById(String id)
    {
        return basePassAnalysisTaskMapper.deleteBasePassAnalysisTaskById(id);
    }
}
