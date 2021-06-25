package com.isstech.isstechadmin.mapper;

import java.util.List;
import com.isstech.isstechadmin.domain.BaseCommunityOrganization;

/**
 * 社区与组织关联Mapper接口
 * 
 * @author yzp
 * @date 2020-12-29
 */
public interface BaseCommunityOrganizationMapper 
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
     * 删除社区与组织关联
     * 
     * @param id 社区与组织关联ID
     * @return 结果
     */
    public int deleteBaseCommunityOrganizationById(String id);

    /**
     * 批量删除社区与组织关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBaseCommunityOrganizationByIds(String[] ids);

    /**
     * 批量软删除社区与组织关联
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int updateBaseCommunityOrganizationByIds(String[] ids);

    public String selectUuid();
}
