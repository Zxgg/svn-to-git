package com.isstech.isstechadmin.utils.mapper;

import com.isstech.isstechadmin.utils.domain.SysDeptIp;

import java.util.List;

/**
 * 部门同步数据ip等信息记录Mapper接口
 * 
 * @author yzp
 * @date 2021-02-01
 */
public interface SysDeptIpMapper 
{
    /**
    *  获取uuid
    * */
    public String selectUuid();

    /**
     * 查询部门同步数据ip等信息记录
     * 
     * @param id 部门同步数据ip等信息记录ID
     * @return 部门同步数据ip等信息记录
     */
    public SysDeptIp selectSysDeptIpById(String id);

    /**
     * 查询部门同步数据ip等信息记录列表
     * 
     * @param sysDeptIp 部门同步数据ip等信息记录
     * @return 部门同步数据ip等信息记录集合
     */
    public List<SysDeptIp> selectSysDeptIpList(SysDeptIp sysDeptIp);

    /**
     * 新增部门同步数据ip等信息记录
     * 
     * @param sysDeptIp 部门同步数据ip等信息记录
     * @return 结果
     */
    public int insertSysDeptIp(SysDeptIp sysDeptIp);

    /**
     * 修改部门同步数据ip等信息记录
     * 
     * @param sysDeptIp 部门同步数据ip等信息记录
     * @return 结果
     */
    public int updateSysDeptIp(SysDeptIp sysDeptIp);

    /**
     * 删除部门同步数据ip等信息记录
     * 
     * @param id 部门同步数据ip等信息记录ID
     * @return 结果
     */
    public int deleteSysDeptIpById(String id);

    /**
     * 批量删除部门同步数据ip等信息记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDeptIpByIds(String[] ids);

    /**
     * 批量软删除部门同步数据ip等信息记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateSysDeptIpByIds(String[] ids);
}
