package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseDataRequestNotification;

/**
 * 资料领取通知Mapper接口
 * 
 * @author yzp
 * @date 2021-02-19
 */
public interface BaseDataRequestNotificationMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询资料领取通知
     * 
     * @param id 资料领取通知ID
     * @return 资料领取通知
     */
    public BaseDataRequestNotification selectBaseDataRequestNotificationById(String id);

    /**
     * 查询资料领取通知列表
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 资料领取通知集合
     */
    public List<BaseDataRequestNotification> selectBaseDataRequestNotificationList(BaseDataRequestNotification baseDataRequestNotification);

    /**
     * 新增资料领取通知
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 结果
     */
    public int insertBaseDataRequestNotification(BaseDataRequestNotification baseDataRequestNotification);

    /**
     * 修改资料领取通知
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 结果
     */
    public int updateBaseDataRequestNotification(BaseDataRequestNotification baseDataRequestNotification);

    /**
     * 删除资料领取通知
     * 
     * @param id 资料领取通知ID
     * @return 结果
     */
    public int deleteBaseDataRequestNotificationById(String id);

    /**
     * 批量删除资料领取通知
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseDataRequestNotificationByIds(String[] ids);

    /**
     * 批量软删除资料领取通知
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseDataRequestNotificationByIds(String[] ids);
}
