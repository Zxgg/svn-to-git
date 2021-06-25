package com.isstech.isstechadmin.mapper;


import com.isstech.isstechadmin.domain.SysConfig;

import java.util.List;

/**
 * @Description 参数配置 数据层
 * @Author yzp
 * @Date 2020/12/21 13:46
 */
public interface SysConfigMapper
{
    /**
     * 查询参数配置信息
     * 
     * @param config 参数配置信息
     * @return 参数配置信息
     */
    public SysConfig selectConfig(SysConfig config);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
    public List<SysConfig> selectConfigList(SysConfig config);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数配置信息
     */
    public SysConfig checkConfigKeyUnique(String configKey);

    /**
     * 新增参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int insertConfig(SysConfig config);

    /**
     * 修改参数配置
     * 
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfig(SysConfig config);

    /**
     * 修改参数配置
     *
     * @param config 参数配置信息
     * @return 结果
     */
    public int updateConfigByKey(SysConfig config);

    /**
     * 删除参数配置
     * 
     * @param id 参数ID
     * @return 结果
     */
    public int deleteConfigById(String id);

    /**
     * 批量删除参数信息
     * 
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    public int deleteConfigByIds(String[] ids);

    /**
     * 批量软删除参数信息
     *
     * @param ids 需要删除的参数ID
     * @return 结果
     */
    public int updateConfigByIds(String[] ids);
}
