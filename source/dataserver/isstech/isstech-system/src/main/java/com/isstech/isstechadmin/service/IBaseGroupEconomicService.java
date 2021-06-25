package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseGroupEconomic;
import com.isstech.isstechadmin.domain.vo.CommunityGroupEconomicVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;

/**
 * 非公经济组织信息Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseGroupEconomicService 
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
    public int insertBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic, QueryVo vo);

    /**
     * 修改非公经济组织信息
     * 
     * @param baseGroupEconomic 非公经济组织信息
     * @return 结果
     */
    public int updateBaseGroupEconomic(BaseGroupEconomic baseGroupEconomic, QueryVo vo);

    /**
     * 批量删除非公经济组织信息
     * 
     * @param ids 需要删除的非公经济组织信息ID
     * @return 结果
     */
    public int deleteBaseGroupEconomicByIds(String[] ids);
    /**
     * 批量软删除非公经济组织信息
     *
     * @param ids 需要删除的非公经济组织信息ID
     * @return 结果
     */
    public int updateBaseGroupEconomicByIds(String[] ids);

    /**
     * 删除非公经济组织信息信息
     * 
     * @param id 非公经济组织信息ID
     * @return 结果
     */
    public int deleteBaseGroupEconomicById(String id);
}
