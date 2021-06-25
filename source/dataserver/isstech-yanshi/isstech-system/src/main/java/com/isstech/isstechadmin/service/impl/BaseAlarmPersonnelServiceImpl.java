package com.isstech.isstechadmin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.isstech.common.utils.SecurityUtils;
import com.isstech.isstechadmin.mapper.BaseAlarmPersonnelMapper;
import com.isstech.isstechadmin.domain.BaseAlarmPersonnel;
import com.isstech.isstechadmin.service.IBaseAlarmPersonnelService;

/**
 * 告警人员Service业务层处理
 * 
 * @author yzp
 * @date 2021-04-26
 */
@Service
public class BaseAlarmPersonnelServiceImpl implements IBaseAlarmPersonnelService 
{
    @Autowired
    private BaseAlarmPersonnelMapper baseAlarmPersonnelMapper;

    /**
     * 查询告警人员
     * 
     * @param id 告警人员ID
     * @return 告警人员
     */
    @Override
    public BaseAlarmPersonnel selectBaseAlarmPersonnelById(String id)
    {
        return baseAlarmPersonnelMapper.selectBaseAlarmPersonnelById(id);
    }

    /**
     * 查询告警人员列表
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 告警人员
     */
    @Override
    public List<BaseAlarmPersonnel> selectBaseAlarmPersonnelList(BaseAlarmPersonnel baseAlarmPersonnel)
    {
        return baseAlarmPersonnelMapper.selectBaseAlarmPersonnelList(baseAlarmPersonnel);
    }

    /**
     * 新增告警人员
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 结果
     */
    @Override
    public int insertBaseAlarmPersonnel(BaseAlarmPersonnel baseAlarmPersonnel)
    {
        baseAlarmPersonnel.setId(baseAlarmPersonnelMapper.selectUuid());
        baseAlarmPersonnel.setDelFlag("0");
        return baseAlarmPersonnelMapper.insertBaseAlarmPersonnel(baseAlarmPersonnel);
    }

    /**
     * 修改告警人员
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 结果
     */
    @Override
    public int updateBaseAlarmPersonnel(BaseAlarmPersonnel baseAlarmPersonnel)
    {
        return baseAlarmPersonnelMapper.updateBaseAlarmPersonnel(baseAlarmPersonnel);
    }

    /**
     * 批量删除告警人员
     * 
     * @param ids 需要删除的告警人员ID
     * @return 结果
     */
    @Override
    public int deleteBaseAlarmPersonnelByIds(String[] ids)
    {
        return baseAlarmPersonnelMapper.deleteBaseAlarmPersonnelByIds(ids);
    }

    /**
     * 批量软删除告警人员
     *
     * @param ids 需要删除的告警人员ID
     * @return 结果
     */
    @Override
    public int updateBaseAlarmPersonnelByIds(String[] ids)
    {
        return baseAlarmPersonnelMapper.updateBaseAlarmPersonnelByIds(ids);
    }

    /**
     * 删除告警人员信息
     * 
     * @param id 告警人员ID
     * @return 结果
     */
    @Override
    public int deleteBaseAlarmPersonnelById(String id)
    {
        return baseAlarmPersonnelMapper.deleteBaseAlarmPersonnelById(id);
    }
}
