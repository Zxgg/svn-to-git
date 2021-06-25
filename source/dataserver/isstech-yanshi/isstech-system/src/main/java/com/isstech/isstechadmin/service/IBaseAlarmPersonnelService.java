package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseAlarmPersonnel;

/**
 * 告警人员Service接口
 * 
 * @author yzp
 * @date 2021-04-26
 */
public interface IBaseAlarmPersonnelService 
{
    /**
     * 查询告警人员
     * 
     * @param id 告警人员ID
     * @return 告警人员
     */
    public BaseAlarmPersonnel selectBaseAlarmPersonnelById(String id);

    /**
     * 查询告警人员列表
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 告警人员集合
     */
    public List<BaseAlarmPersonnel> selectBaseAlarmPersonnelList(BaseAlarmPersonnel baseAlarmPersonnel);

    /**
     * 新增告警人员
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 结果
     */
    public int insertBaseAlarmPersonnel(BaseAlarmPersonnel baseAlarmPersonnel);

    /**
     * 修改告警人员
     * 
     * @param baseAlarmPersonnel 告警人员
     * @return 结果
     */
    public int updateBaseAlarmPersonnel(BaseAlarmPersonnel baseAlarmPersonnel);

    /**
     * 批量删除告警人员
     * 
     * @param ids 需要删除的告警人员ID
     * @return 结果
     */
    public int deleteBaseAlarmPersonnelByIds(String[] ids);

    /**
     * 删除告警人员信息
     * 
     * @param id 告警人员ID
     * @return 结果
     */
    public int deleteBaseAlarmPersonnelById(String id);

    /**
     * 批量软删除告警人员
     *
     * @param ids 需要删除的告警人员ID
     * @return 结果
     */
    public int updateBaseAlarmPersonnelByIds(String[] ids);
}
