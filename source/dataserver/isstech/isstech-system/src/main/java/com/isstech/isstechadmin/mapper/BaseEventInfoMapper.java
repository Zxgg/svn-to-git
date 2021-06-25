package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseEventInfo;
import com.isstech.isstechadmin.domain.vo.GroupByVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 事件详情Mapper接口
 *
 * @author yzp
 * @date 2021-03-30
 */
public interface BaseEventInfoMapper {
    /**
     * 获取uuid
     */
    public String selectUuid();

    /**
     * 查询事件详情
     *
     * @param id 事件详情ID
     * @return 事件详情
     */
    public BaseEventInfo selectBaseEventInfoById(String id);

    /**
     * 查询不同告警状态的统计数值
     */
    public List<Map<String, Object>> selectListStatusCount();

    /**
     * 查询事件详情列表
     *
     * @param baseEventInfo 事件详情
     * @return 事件详情集合
     */
    public List<BaseEventInfo> selectBaseEventInfoList(BaseEventInfo baseEventInfo);

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
     * 删除事件详情
     *
     * @param id 事件详情ID
     * @return 结果
     */
    public int deleteBaseEventInfoById(String id);

    /**
     * 批量删除事件详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseEventInfoByIds(String[] ids);

    /**
     * 批量软删除事件详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseEventInfoByIds(String[] ids);

    /**
     * 查询事件信息
     * "and deal_with_type = #{dealWithType} " +
     *
     * @param baseEventInfo
     * @return
     */
    @Select(value = "select * from base_event_info " +
            "where event_status != #{eventStatus} " +
            "and event_status != '-1'" +
            "and community_id = #{communityId}")
    public List<BaseEventInfo> queryEventsInfo(BaseEventInfo baseEventInfo);

    @Select("select event_status name,count(event_status) num,sum(TO_DAYS(update_time) = TO_DAYS(NOW())) as today from base_event_info GROUP BY event_status ")
    public List<GroupByVo> countEvent();
}
