package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseStayBehind;

/**
 * 留守人员基本信息Service接口
 * 
 * @author yzp
 * @date 2021-01-15
 */
public interface IBaseStayBehindService 
{
    /**
     * 查询留守人员基本信息
     * 
     * @param id 留守人员基本信息ID
     * @return 留守人员基本信息
     */
    public BaseStayBehind selectBaseStayBehindById(String id);

    /**
     * 查询留守人员基本信息列表
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 留守人员基本信息集合
     */
    public List<BaseStayBehind> selectBaseStayBehindList(BaseStayBehind baseStayBehind);

    /**
     * 新增留守人员基本信息
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 结果
     */
    public int insertBaseStayBehind(BaseStayBehind baseStayBehind);

    /**
     * 修改留守人员基本信息
     * 
     * @param baseStayBehind 留守人员基本信息
     * @return 结果
     */
    public int updateBaseStayBehind(BaseStayBehind baseStayBehind);

    /**
     * 批量删除留守人员基本信息
     * 
     * @param ids 需要删除的留守人员基本信息ID
     * @return 结果
     */
    public int deleteBaseStayBehindByIds(String[] ids);

    /**
     * 删除留守人员基本信息信息
     * 
     * @param id 留守人员基本信息ID
     * @return 结果
     */
    public int deleteBaseStayBehindById(String id);

    /**
     * 批量软删除留守人员基本信息
     *
     * @param ids 需要删除的留守人员基本信息ID
     * @return 结果
     */
    public int updateBaseStayBehindByIds(String[] ids);


    public BaseStayBehind getByPersonId(String personId);
}
