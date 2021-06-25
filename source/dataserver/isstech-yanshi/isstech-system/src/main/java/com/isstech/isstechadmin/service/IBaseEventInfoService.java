package com.isstech.isstechadmin.service;

import com.isstech.common.core.domain.AjaxResult;
import com.isstech.common.core.domain.entity.SysUser;
import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.dto.EventCountDTO;
import com.isstech.isstechadmin.domain.vo.GroupByVo;

import java.util.List;
import java.util.Map;

/**
 * 事件详情Service接口
 *
 * @author yzp
 * @date 2021-03-30
 */
public interface IBaseEventInfoService {
    /**
     * 查询事件详情
     *
     * @param id 事件详情ID
     * @return 事件详情
     */
    public BaseEventInfo selectBaseEventInfoById(String id);

    /**
     * 查询事件详情列表
     *
     * @param baseEventInfo 事件详情
     * @return 事件详情集合
     */
    public List<BaseEventInfo> selectBaseEventInfoList(BaseEventInfo baseEventInfo);

    /**
     * 排他条件查询
     *
     * @param baseEventInfo
     * @return
     */
    public List<BaseEventInfo> queryEventsWithoutConditions(BaseEventInfo baseEventInfo);

    /**
     * 查询不同告警状态的统计数值
     */
    public Map<String, Object> selectListStatusCount();

    /**
     * 新增事件详情
     *
     * @param baseEventInfo 事件详情
     * @return 结果
     */
    public int insertBaseEventInfo(BaseEventInfo baseEventInfo);

    /**
     * 修改事件详情
     *
     * @param baseEventInfo 事件详情
     * @return 结果
     */
    public int updateBaseEventInfo(BaseEventInfo baseEventInfo);

    /**
     * 批量删除事件详情
     *
     * @param ids 需要删除的事件详情ID
     * @return 结果
     */
    public int deleteBaseEventInfoByIds(String[] ids);

    /**
     * 删除事件详情信息
     *
     * @param id 事件详情ID
     * @return 结果
     */
    public int deleteBaseEventInfoById(String id);

    /**
     * 批量软删除事件详情
     *
     * @param ids 需要删除的事件详情ID
     * @return 结果
     */
    public int updateBaseEventInfoByIds(String[] ids);

    /**
     * 修改事件状态并增加一条处置流程
     *
     * @return
     */
    public AjaxResult updateBaseEvents(BaseEventInfo baseEventInfo);

    /**
     * 事件统计
     *
     * @return
     */
    public List<GroupByVo> countEvent();

    public void saveAllData(List<BaseEventInfo> list) throws Exception;

    /**
     * 统计各类型数据
     *
     * @return
     */
    public AjaxResult getAnalysisData();

    public List<SysUser> getSysUserData(String communityId , String communityChildId);

    /**
     * 根据社区id，按网格统计每个网格的数据
     * @param baseEventInfo
     */
    public List<EventCountDTO> sumDataGroupByDivision(BaseEventInfo baseEventInfo);

    /**
     *
     * @param baseEventInfo
     * @return
     */
    public EventCountDTO getHandleAndConclude(BaseEventInfo baseEventInfo);

    /**
     * 查询1+4备份事件数据
     * @param baseEventInfo
     * @return
     */
    public List<BaseEventInfo> selectBaseEventInfoDataList(BaseEventInfo baseEventInfo);

}
