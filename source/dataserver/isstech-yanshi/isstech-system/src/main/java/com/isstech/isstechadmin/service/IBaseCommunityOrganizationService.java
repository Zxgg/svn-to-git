package com.isstech.isstechadmin.service;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityOrganization;
import com.isstech.isstechadmin.domain.vo.QueryVo;

/**
 * 社区与组织关联Service接口
 * 
 * @author yzp
 * @date 2020-12-29
 */
public interface IBaseCommunityOrganizationService 
{
    /**
     * 查询社区与组织关联
     * 
     * @param id 社区与组织关联ID
     * @return 社区与组织关联
     */
    public BaseCommunityOrganization selectBaseCommunityOrganizationById(String id);

    /**
     * 查询社区与组织关联列表
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 社区与组织关联集合
     */
    public List<BaseCommunityOrganization> selectBaseCommunityOrganizationList(BaseCommunityOrganization baseCommunityOrganization);

    /**
     * 新增社区与组织关联
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 结果
     */
    public int insertBaseCommunityOrganization(BaseCommunityOrganization baseCommunityOrganization);

    /**
     * 修改社区与组织关联
     * 
     * @param baseCommunityOrganization 社区与组织关联
     * @return 结果
     */
    public int updateBaseCommunityOrganization(BaseCommunityOrganization baseCommunityOrganization);

    /**
     * 批量删除社区与组织关联
     * 
     * @param ids 需要删除的社区与组织关联ID
     * @return 结果
     */
    public int deleteBaseCommunityOrganizationByIds(String[] ids);

    /**
     * 删除社区与组织关联信息
     * 
     * @param id 社区与组织关联ID
     * @return 结果
     */
    public int deleteBaseCommunityOrganizationById(String id);

    /**
     * 批量软删除社区与组织关联
     *
     * @param ids 需要删除的社区与组织关联ID
     * @return 结果
     */
    public int updateBaseCommunityOrganizationByIds(String[] ids);

    /**
     * 新增社区与组织关联
     *
     * @param vo 社区与组织关联
     * @param type 组织机构类型
     * @return 结果
     */
    public int saveBaseCommunityOrganization(QueryVo vo ,String orgId,String type);
}
