package com.isstech.isstechadmin.service.impl;

import java.util.Date;
import java.util.List;

import com.isstech.common.utils.DateUtils;
import com.isstech.isstechadmin.domain.SysOperLog;
import com.isstech.isstechadmin.mapper.SysOperLogMapper;
import com.isstech.isstechadmin.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录Service业务层处理
 * 
 * @author yzp
 * @date 2020-11-11
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService
{
    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    /**
     * 查询操作日志记录
     * 
     * @param id 操作日志记录ID
     * @return 操作日志记录
     */
    @Override
    public SysOperLog selectSysOperLogById(String id)
    {
        return sysOperLogMapper.selectSysOperLogById(id);
    }

    /**
     * 查询操作日志记录列表
     * 
     * @param sysOperLog 操作日志记录
     * @return 操作日志记录
     */
    @Override
    public List<SysOperLog> selectSysOperLogList(SysOperLog sysOperLog)
    {
        return sysOperLogMapper.selectSysOperLogList(sysOperLog);
    }

    /**
     * 新增操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    @Override
    public int insertSysOperLog(SysOperLog sysOperLog)
    {
        sysOperLog.setOperTime(DateUtils.getNowDate());
        return sysOperLogMapper.insertSysOperLog(sysOperLog);
    }

    /**
     * 修改操作日志记录
     * 
     * @param sysOperLog 操作日志记录
     * @return 结果
     */
    @Override
    public int updateSysOperLog(SysOperLog sysOperLog)
    {
        return sysOperLogMapper.updateSysOperLog(sysOperLog);
    }

    /**
     * 批量删除操作日志记录
     * 
     * @param ids 需要删除的操作日志记录ID
     * @return 结果
     */
    @Override
    public int deleteSysOperLogByIds(String[] ids)
    {
        return sysOperLogMapper.deleteSysOperLogByIds(ids);
    }

    /**
     * 删除操作日志记录信息
     * 
     * @param id 操作日志记录ID
     * @return 结果
     */
    @Override
    public int deleteSysOperLogById(String id)
    {
        return sysOperLogMapper.deleteSysOperLogById(id);
    }
}
