package com.isstech.isstechadmin.service;

import com.isstech.isstechadmin.domain.BaseSocialOrganization;
import com.isstech.isstechadmin.domain.vo.CommunitySocialOrganizationVo;
import com.isstech.isstechadmin.domain.vo.QueryVo;

import java.util.List;

/**
 * 社会组织信息Service接口
 * 
 * @author yzp
 * @date 2020-11-25
 */
public interface IBaseSocialOrganizationService 
{
    /**
     * 查询社会组织信息
     * 
     * @param id 社会组织信息ID
     * @return 社会组织信息
     */
    public CommunitySocialOrganizationVo selectBaseSocialOrganizationById(String id);

    /**
     * 查询社会组织信息列表
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 社会组织信息集合
     */
    public List<CommunitySocialOrganizationVo> selectBaseSocialOrganizationList(CommunitySocialOrganizationVo baseSocialOrganization);

    /**
     * 新增社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    public int insertBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization, QueryVo vo);

    /**
     * 修改社会组织信息
     * 
     * @param baseSocialOrganization 社会组织信息
     * @return 结果
     */
    public int updateBaseSocialOrganization(BaseSocialOrganization baseSocialOrganization, QueryVo vo);

    /**
     * 批量删除社会组织信息
     * 
     * @param ids 需要删除的社会组织信息ID
     * @return 结果
     */
    public int deleteBaseSocialOrganizationByIds(String[] ids);
    /**
     * 批量软删除社会组织信息
     *
     * @param ids 需要删除的社会组织信息ID
     * @return 结果
     */
    public int updateBaseSocialOrganizationByIds(String[] ids);

    /**
     * 删除社会组织信息信息
     * 
     * @param id 社会组织信息ID
     * @return 结果
     */
    public int deleteBaseSocialOrganizationById(String id);
}
