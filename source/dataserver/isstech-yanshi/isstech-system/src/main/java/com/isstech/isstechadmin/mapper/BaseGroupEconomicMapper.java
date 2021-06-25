package com.isstech.isstechadmin.mapper;

import com.isstech.isstechadmin.domain.BaseGroupEconomic;
import com.isstech.isstechadmin.domain.vo.CommunityGroupEconomicVo;

import java.util.List;

/**
 * 非公经济组织信息Mapper接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface BaseGroupEconomicMapper 
{
    /**
     * 查询非公经济组织信息
     * 
     * @param id 非公经济组织信息ID
     * @return 非公经济组织信息
     */
    public CommunityGroupEconomicVo selectBaseGroupEconomicById(String id);

    /**
     * 查询非公经济组织信息列表
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 非公经济组织信息集合
     */
    public List<CommunityGroupEconomicVo> selectBaseGroupEconomicList(CommunityGroupEconomicVo baseGroupEconomic);

    /**
     * 新增非公经济组织信息
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 结果
     */
    public int insertBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic);

    /**
     * 修改非公经济组织信息
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 结果
     */
    public int updateBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic);

    /**
     * 删除非公经济组织信息
     * 
     * @param id 非公经济组织信息ID
     * @return 结果
     */
    public int deleteBaseGroupEconomicById(String id);

    /**
     * 批量删除非公经济组织信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseGroupEconomicByIds(String[] ids);
    /**
     * 批量软删除非公经济组织信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseGroupEconomicByIds(String[] ids);
}
