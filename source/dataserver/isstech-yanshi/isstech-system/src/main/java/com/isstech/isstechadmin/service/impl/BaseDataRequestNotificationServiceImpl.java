package com.isstech.isstechadmin.service.impl;

import java.util.List;
import com.isstech.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseDataRequestNotificationMapper;
import com.isstech.isstechadmin.domain.BaseDataRequestNotification;
import com.isstech.isstechadmin.service.IBaseDataRequestNotificationService;

/**
 * 资料领取通知Service业务层处理
 * 
 * @author yzp
 * @date 2021-02-19
 */
@Service
public class BaseDataRequestNotificationServiceImpl implements IBaseDataRequestNotificationService 
{
    @Autowired
    private BaseDataRequestNotificationMapper baseDataRequestNotificationMapper;

    /**
     * 查询资料领取通知
     * 
     * @param id 资料领取通知ID
     * @return 资料领取通知
     */
    @Override
    public BaseDataRequestNotification selectBaseDataRequestNotificationById(String id)
    {
        return baseDataRequestNotificationMapper.selectBaseDataRequestNotificationById(id);
    }

    /**
     * 查询资料领取通知列表
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 资料领取通知
     */
    @Override
    public List<BaseDataRequestNotification> selectBaseDataRequestNotificationList(BaseDataRequestNotification baseDataRequestNotification)
    {
        return baseDataRequestNotificationMapper.selectBaseDataRequestNotificationList(baseDataRequestNotification);
    }

    /**
     * 新增资料领取通知
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 结果
     */
    @Override
    public int insertBaseDataRequestNotification(BaseDataRequestNotification baseDataRequestNotification)
    {
        baseDataRequestNotification.setId(baseDataRequestNotificationMapper.selectUuid());
        baseDataRequestNotification.setCreateBy(SecurityUtils.getUsername());
        baseDataRequestNotification.setCreateTime(DateUtils.getNowDate());
        baseDataRequestNotification.setDelFlag("0");
        return baseDataRequestNotificationMapper.insertBaseDataRequestNotification(baseDataRequestNotification);
    }

    /**
     * 修改资料领取通知
     * 
     * @param baseDataRequestNotification 资料领取通知
     * @return 结果
     */
    @Override
    public int updateBaseDataRequestNotification(BaseDataRequestNotification baseDataRequestNotification)
    {
        baseDataRequestNotification.setUpdateBy(SecurityUtils.getUsername());
        baseDataRequestNotification.setUpdateTime(DateUtils.getNowDate());
        return baseDataRequestNotificationMapper.updateBaseDataRequestNotification(baseDataRequestNotification);
    }

    /**
     * 批量删除资料领取通知
     * 
     * @param ids 需要删除的资料领取通知ID
     * @return 结果
     */
    @Override
    public int deleteBaseDataRequestNotificationByIds(String[] ids)
    {
        return baseDataRequestNotificationMapper.deleteBaseDataRequestNotificationByIds(ids);
    }

    /**
     * 批量软删除资料领取通知
     *
     * @param ids 需要删除的资料领取通知ID
     * @return 结果
     */
    @Override
    public int updateBaseDataRequestNotificationByIds(String[] ids)
    {
        return baseDataRequestNotificationMapper.updateBaseDataRequestNotificationByIds(ids);
    }

    /**
     * 删除资料领取通知信息
     * 
     * @param id 资料领取通知ID
     * @return 结果
     */
    @Override
    public int deleteBaseDataRequestNotificationById(String id)
    {
        return baseDataRequestNotificationMapper.deleteBaseDataRequestNotificationById(id);
    }
}
