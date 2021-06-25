package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.SysOperLog;

import java.util.List;

/**
 * 操作日志记录Mapper接口
 * 
 * @author yzp
 * @date 2020-11-11
 */
public interface SysOperLogMapper 
{
    /**
     * 查询操作日志记录
     * 
     * @param id 操作日志记录ID
     * @return 操作日志记录
     */
    public SysOperLog selectSysOperLogById(String id);

    /**
     * 查询操作日志记录列表
     * 
     * @param sysOperLog 操作日志记录
     * @return 操作日志记录集合
     */
    public List<SysOperLog> selectSysOperLogList(SysOperLog sysOperLog);

    /**
     * 新增操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    public int insertSysOperLog(SysOperLog sysOperLog);

    /**
     * 修改操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    public int updateSysOperLog(SysOperLog sysOperLog);

    /**
     * 删除操作日志记录
     * 
     * @param id 操作日志记录ID
     * @return 结果
     */
    public int deleteSysOperLogById(String id);

    /**
     * 批量删除操作日志记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysOperLogByIds(String[] ids);
}
