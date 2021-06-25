package com.isstech.isstechadmin.utils.mapper;

import com.isstech.isstechadmin.utils.domain.SysDataSynchronization;

import java.util.List;
import java.util.Map;

/**
 * 数据同步Mapper接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface SysDataSynchronizationMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询数据同步
     * 
     * @param id 数据同步ID
     * @return 数据同步
     */
    public SysDataSynchronization selectSysDataSynchronizationById(String id);

    /**
     * 查询数据同步列表
     * 
     * @param sysDataSynchronization 数据同步
     * @return 数据同步集合
     */
    public List<SysDataSynchronization> selectSysDataSynchronizationList(SysDataSynchronization sysDataSynchronization);

    /**
     * 新增数据同步
     * 
     * @param sysDataSynchronization 数据同步
     * @return 结果
     */
    public int insertSysDataSynchronization(SysDataSynchronization sysDataSynchronization);

    /**
     * 修改数据同步
     * 
     * @param sysDataSynchronization 数据同步
     * @return 结果
     */
    public int updateSysDataSynchronization(SysDataSynchronization sysDataSynchronization);

    /**
     * 删除数据同步
     * 
     * @param id 数据同步ID
     * @return 结果
     */
    public int deleteSysDataSynchronizationById(String id);

    /**
     * 批量删除数据同步
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDataSynchronizationByIds(String[] ids);

    /**
     * 批量软删除数据同步
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateSysDataSynchronizationByIds(String[] ids);

    /**
     * 直接执行查询语句
     */
    public List<Map<String,String>> executeSelect(String synData);

    /**
     * 直接执行更新语句
     */
    public int executeUpdate(String synData);

    /**
     * 直接执行新增语句
     */
    public int executeInsert(String synData);

    /**
     * 直接执行删除语句
     */
    public int executeDelete(String synData);
}
